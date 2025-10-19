package com.parcial.dos.parcialdos.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class AccountRequestDTO {

    @JsonProperty("numeroCuenta")
    private String numeroCuenta;

    @JsonProperty("dueno")
    private String dueno;

    @JsonProperty("balanceActual")
    private BigDecimal balanceActual;

    public AccountRequestDTO() {}

    // getters y setters
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getDueno() { return dueno; }
    public void setDueno(String dueno) { this.dueno = dueno; }

    public BigDecimal getBalanceActual() { return balanceActual; }
    public void setBalanceActual(BigDecimal balanceActual) { this.balanceActual = balanceActual; }
}
