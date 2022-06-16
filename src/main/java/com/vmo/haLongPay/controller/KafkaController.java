package com.vmo.haLongPay.controller;

import com.vmo.haLongPay.dto.AccountAuthenticationDTO;
import com.vmo.haLongPay.dto.LookUpMoneyTransferDTO;
import com.vmo.haLongPay.dto.MoneyTransferDTO;
import com.vmo.haLongPay.model.AccountAuthentication;
import com.vmo.haLongPay.model.LookUpMoneyTransfer;
import com.vmo.haLongPay.model.MoneyTransfer;
import com.vmo.haLongPay.service.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class KafkaController {
    private final Producer producer;

    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/account/verify")
    public ResponseEntity<AccountAuthenticationDTO> accountAuthentication(@RequestBody AccountAuthentication account) throws Exception {
        AccountAuthenticationDTO dto = this.producer.sendMessageAccount(account);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/transfer")
    public ResponseEntity<MoneyTransferDTO> moneyTransfer(@RequestBody MoneyTransfer moneyTransfer) throws Exception {
        MoneyTransferDTO dto = this.producer.sendMessageMoney(moneyTransfer);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/transfer/info")
    public ResponseEntity<LookUpMoneyTransferDTO> lookupMoneyTransfer(@RequestBody LookUpMoneyTransfer lookUpMoneyTransfer) throws Exception {
        LookUpMoneyTransferDTO dto = this.producer.sendMessageLoopUpMoney(lookUpMoneyTransfer);
        return ResponseEntity.ok().body(dto);
    }

}
