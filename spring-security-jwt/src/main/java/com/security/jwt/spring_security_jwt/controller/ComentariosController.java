package com.security.jwt.spring_security_jwt.controller;

import com.security.jwt.spring_security_jwt.model.Comentarios;
import com.security.jwt.spring_security_jwt.repository.ComentariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ComentariosController {

    private final ComentariosRepository comentariosRepository;

    @GetMapping("/comentarios")
    public List<Comentarios> listarComentariosDespuesDe(@RequestParam String fecha) {
        Date date = Date.valueOf(fecha);
        return comentariosRepository.findByFechaAfter(date);
    }
}