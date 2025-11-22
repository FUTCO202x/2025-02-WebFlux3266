package com.security.jwt.spring_security_jwt.repository;

import com.security.jwt.spring_security_jwt.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {
    List<Producto> findByStockLessThanEqual(int stock);
}