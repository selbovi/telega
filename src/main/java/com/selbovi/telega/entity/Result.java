package com.selbovi.telega.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * результат посещения и обработки товара.
 */
@Entity
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Прямая ссылка на страницу товара.
     */
    @ManyToOne
    private ProductPage productPage;

    /**
     * дата и время текущей проверки.
     */
    @Column(nullable = false)
    private ZonedDateTime checkTime;

    /**
     * Цена.
     */
    private BigDecimal price;

    /**
     * Ошибка обработки.
     */
    @Column(length = 10_000)
    private String error;

    /**
     * HTML который не удалось распарсить.
     */
    @Column(length = 100_000)
    private String errorHtml;
}
