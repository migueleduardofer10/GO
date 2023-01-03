package com.example.apitransfer240931.controller;

import com.example.apitransfer240931.exception.AccountNotFoundException;
import com.example.apitransfer240931.exception.InsufficientBalanceException;
import com.example.apitransfer240931.exception.InvalidAmountException;
import com.example.apitransfer240931.exception.InvalidCurrencyException;
import com.example.apitransfer240931.model.Account;
import com.example.apitransfer240931.model.Transfer;
import com.example.apitransfer240931.repository.AccountRepository;
import com.example.apitransfer240931.repository.TransferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class TransferController {
    private final AccountRepository accountReposittory;
    private final TransferRepository transferRepository;

    public TransferController(AccountRepository accountReposittory, TransferRepository transferRepository) {
        this.accountReposittory = accountReposittory;
        this.transferRepository = transferRepository;
    }

    @PostMapping("/transfers")
    @Transactional
    public ResponseEntity<Transfer> save(@RequestParam("sourceAccountId") Long sourceAccountId,
                                         @RequestParam("destinationAccountId") Long destinationAccountId,
                                         @RequestParam("amount") BigDecimal amount,
                                         @RequestParam("currency") String curreny
                                         )throws IOException {
        Transfer transfer = new Transfer();
        transfer.setAmount(amount);
        transfer.setCurrency(curreny);
        transfer.setStatus("sucess");
        transfer.setCreatedAt(LocalDateTime.now());

        //TODO: búsqueda de categoría para establecer en el objeto del producto
        Account sourceAccount = accountReposittory.findById(sourceAccountId)
                .orElse(null);
        if( sourceAccount!=null) {
            transfer.setSourceAccount(sourceAccount);
        } else {
            throw new AccountNotFoundException("La cuenta solicitada no ha sido encontrada");
        }

        Account destinationAccount = accountReposittory.findById(destinationAccountId)
                .orElse(null);
        if( destinationAccount!=null) {
            transfer.setDestinationAccount(destinationAccount);
        } else {
            throw new AccountNotFoundException("La cuenta solicitada no ha sido encontrada");

        }
        validateTransfer(transfer);
        Transfer transferSaved=transferRepository.save(transfer);

        return new ResponseEntity<Transfer>(transferSaved, HttpStatus.CREATED);
    }

    @GetMapping("/transfers/{transferId}")
    @Transactional (readOnly = true)
    public ResponseEntity<Transfer> searchById(@PathVariable Long transferId){
        Transfer transfer=transferRepository.findById(transferId)
                .orElse(null);
        return new ResponseEntity<Transfer>(transfer,HttpStatus.OK);
    }

    private void validateTransfer(Transfer transfer) {
        //Amount mayor a 0
        if (transfer.getAmount()  == null || transfer.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("La cantidad especificada es inválida, debe ser mayor a 00 (CERO) ");
        }

        //El currency debe ser EUR, USD y GBP
        if(!transfer.getCurrency().equals("EUR") && !transfer.getCurrency().equals("USD") && !transfer.getCurrency().equals("GBP")){
            throw new InvalidCurrencyException("La divisa especificada es inválida, cuando se ingresa un valor diferente a\n" +
                    "EUR, USD y GBP");
        }

        //Validar que la cuenta origen tenga fondos suficientes
        if(transfer.getSourceAccount().getBalance().compareTo(transfer.getAmount()) < 0){
            throw new InsufficientBalanceException("El saldo de la cuenta de origen es insuficiente para realizar la\n" +
                    "transferencia");
        }

        //Restar el monto de la transferencia al saldo de la cuenta origen
        transfer.getSourceAccount().setBalance(transfer.getSourceAccount().getBalance().subtract(transfer.getAmount()));
        //Sumar el monto de la transferencia al saldo de la cuenta destino
        transfer.getDestinationAccount().setBalance(transfer.getDestinationAccount().getBalance().add(transfer.getAmount()));



    }



}
