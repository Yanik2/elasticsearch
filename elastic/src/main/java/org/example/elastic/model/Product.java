package org.example.elastic.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products")
public class Product {
    @Id
    private String id;
    @Field(type = FieldType.Keyword, name = "name")
    private String name;
    @Field(type = FieldType.Integer, name = "price")
    private Integer price;
    @Field(type = FieldType.Integer, name = "in_stock")
    private Integer inStock;
    @Field(type = FieldType.Date, name = "created", format = DateFormat.date_hour_minute_second)
    private LocalDateTime created;

    @Field()
    private Map<String, Object> attributes;

    public Product() {
    }

    public Product(String id, String name, Integer price, Integer inStock, LocalDateTime created,
                   Map<String, Object> attributes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.created = created;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product product)) {
            return false;
        }
        return Objects.equals(id, product.id) &&
            Objects.equals(name, product.name) &&
            Objects.equals(price, product.price) &&
            Objects.equals(inStock, product.inStock) &&
            Objects.equals(created, product.created) &&
            Objects.equals(attributes, product.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, inStock, created, attributes);
    }
}
