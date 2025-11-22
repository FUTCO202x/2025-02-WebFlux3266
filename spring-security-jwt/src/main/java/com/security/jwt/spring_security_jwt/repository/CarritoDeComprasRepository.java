package com.security.jwt.spring_security_jwt.repository;

import com.security.jwt.spring_security_jwt.model.CarritoDeCompras;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoDeComprasRepository extends CrudRepository<CarritoDeCompras, Integer> {
    Optional<CarritoDeCompras> findByIdCarritoAndUsuario_IdUsuario(int idCarrito, int idUsuario);
}