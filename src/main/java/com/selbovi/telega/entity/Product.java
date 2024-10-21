package com.selbovi.telega.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Название товара, за ценой которого планируем следить.
 */
@Entity
@Data
public class Product {

    /**
     * Имя товара. Напр: ebook, laptop
     */
    @Id
    private String name;
}
