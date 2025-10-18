package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.*;
import java.util.List;
import java.util.Optional;

public interface IAccountService {

    AccountResponseDTO createAccount(AccountRequestDTO request);
    List<AccountResponseDTO> getAllAccounts();
    Optional<AccountResponseDTO> getAccountById(Long id);
    String updateBalance(Long id, AccountRequestDTO request);
    void deleteAccount(Long id);
    Optional<AccountOwnerBalanceDTO> findByAccountNumber(String numeroCuenta);
}
