package com.playready.PlayReadyBackend.repository;

import com.playready.PlayReadyBackend.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {

}
