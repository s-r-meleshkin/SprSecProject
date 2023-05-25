package com.example.springsecurityapplication.models;


import com.example.springsecurityapplication.util.CategoryEnum;

public class Criteria {

    private String search;
    private Integer from;
    private Integer to;
    private String price;
    private CategoryEnum contract;

    public Criteria() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CategoryEnum getContract() {
        return contract;
    }

    public void setContract(CategoryEnum contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Criteria{" +
            "search='" + search + '\'' +
            ", from=" + from +
            ", to=" + to +
            ", price='" + price + '\'' +
            ", contract='" + contract + '\'' +
            '}';
    }
}
