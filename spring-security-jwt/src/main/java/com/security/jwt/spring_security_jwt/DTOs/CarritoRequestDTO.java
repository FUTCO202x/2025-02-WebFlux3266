package com.security.jwt.spring_security_jwt.DTOs;

import java.util.List;

public record CarritoRequestDTO(List<Integer> productoIds, double subtotal, double impuestos) {}