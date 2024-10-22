package com.selbovi.telega.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Прямая ссылка на страницу товара.
 */
@Entity
@Getter
@Setter
public class ProductPage {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * ссылка.
     */
    @Column(nullable = false, length = 2000)
    private String url;

    /**
     * товар.
     */
    @ManyToOne
    private Product product;
}
