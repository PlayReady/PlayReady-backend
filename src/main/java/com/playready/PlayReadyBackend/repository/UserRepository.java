package com.playready.PlayReadyBackend.repository;

import com.playready.PlayReadyBackend.model.Product;
import com.playready.PlayReadyBackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    Iterable<User> findOneByUsername(String username);
}
