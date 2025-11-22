package com.security.jwt.spring_security_jwt.repository;

import com.security.jwt.spring_security_jwt.model.Comentarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ComentariosRepository extends CrudRepository<Comentarios, Integer> {
    List<Comentarios> findByFechaAfter(Date fecha);
}