package com.selbovi.telega.repository;

import com.selbovi.telega.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
