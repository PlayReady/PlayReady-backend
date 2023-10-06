package com.playready.PlayReadyBackend.repository;

import com.playready.PlayReadyBackend.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Iterable<Product> findTop3ByFeaturedIsTrueOrderById();
    Iterable<Product> findAllByOrderById();

}
