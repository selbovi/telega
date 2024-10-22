package com.selbovi.telega.repository;

import java.util.List;
import com.selbovi.telega.entity.ProductPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPageRepository extends JpaRepository<ProductPage, Long> {

    List<ProductPage> findProductPagesByProductName(String name);

}
