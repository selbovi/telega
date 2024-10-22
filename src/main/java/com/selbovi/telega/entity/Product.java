package com.selbovi.telega.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Название товара, за ценой которого планируем следить.
 */
@Entity
@Getter
@Setter
public class Product {

    /**
     * Имя товара. Напр: ebook, laptop
     */
    @Id
    private String name;
}
