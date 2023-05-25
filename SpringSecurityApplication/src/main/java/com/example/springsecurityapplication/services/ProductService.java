package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.*;
import com.example.springsecurityapplication.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Данный метод позволяет получить список всех товаров
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    // Данный метод позволяет получить товар по id
    public Product getProductId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    // Данный метод позволяет сохранить товар
    @Transactional
    public void saveProduct(Product product, Category category){
        product.setCategory(category);
        productRepository.save(product);
    }

    // Данный метод позволяет обновить данные о товаре
    @Transactional
    public void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }

    // Данный метод позволяет удалить товар по id
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    public List<Product> filter(Criteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> item = query.from(Product.class);

        List<Predicate> itemPredicates = new ArrayList<>();

        if (criteria.getSearch() != null && !criteria.getSearch().isEmpty()) {
            itemPredicates.add(cb.like(item.get("title"), criteria.getSearch()));
        }

        if (criteria.getFrom() != null) {
            itemPredicates.add(cb.greaterThan(item.get("price"), criteria.getFrom()));
        }

        if (criteria.getTo() != null) {
            itemPredicates.add(cb.lessThan(item.get("price"), criteria.getTo()));
        }

        if (criteria.getContract() != null) {
            itemPredicates.add(cb.equal(item.get("category"), criteria.getContract().getValue()));
        }

        Predicate predicate = cb.and(itemPredicates.toArray(new Predicate[0]));
        if ("sorted_by_ascending_price".equals(criteria.getPrice())) {
            query.select(item).where(predicate).orderBy(cb.asc(item.get("price")));
        } else {
            query.select(item).where(predicate).orderBy(cb.desc(item.get("price")));
        }

        TypedQuery<Product> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }



}
