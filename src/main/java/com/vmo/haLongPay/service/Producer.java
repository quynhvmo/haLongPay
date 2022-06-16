package com.vmo.haLongPay.service;

import com.vmo.haLongPay.dto.*;
import com.vmo.haLongPay.dto.Error;
import com.vmo.haLongPay.model.AccountAuthentication;
import com.vmo.haLongPay.model.LookUpMoneyTransfer;
import com.vmo.haLongPay.model.MoneyTransfer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Pattern;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC_USER = "users";
    private static final String TOPIC_MONEY = "money";
    private static final String TOPIC_LOOK_UP = "lookup";
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApy4f79rUdCk1G9Miw3ERJMFlCXlM40TEfyFevo+AjC+16QNZOJqhopkOvL399gMcOjdfW0q3s3LmfbxVWZIppCSrFYXoeG8nagvtIuBytO/Ci1HHXAUgz/pMqhwOQsm5w+CgdcWdXOX9GB6eEeLErcokMUOdaad9q54zU48VEZlxwUbCRAZvXFqaY435cOx6xcUswVngkaOwzNEbkzZCnImqNMlBZ6i5J3ltvLQZHCroZjxXUgpSDyJjCyaId7++5ns8n6y1aJv3Yz6f1kJGF7/585aUga0pKTjIpOkUk8qBHLieYUTQpKceyldeZem0vSKAFFZ41pEwXT1Nhp/nVQIDAQAB";
    private static final String CONTENT_REGEX = "[a-zA-Z][a-zA-Z0-9]*";
    private static final int ERROR_CODE = 1001;
    private static final String ERROR_MESSAGE = "User is not exists";

    private final KafkaTemplate<?, String> kafkaTemplate;

    public Producer(KafkaTemplate<?, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public AccountAuthenticationDTO sendMessageAccount(AccountAuthentication account) throws Exception {
        logger.info(String.format("#### -> Producing message account-> %s", account));
        AccountAuthenticationDTO dto = new AccountAuthenticationDTO();
        dto.setSuccess(true); //dump status
        if (dto.isSuccess()) {
            dto.setData(account);
            StringBuilder str = new StringBuilder();
            str.append(account.getRequest_id());
            str.append(account.getBank_no());
            str.append(account.getAccount_no());
            str.append(account.getAccount_type());
            account.setSignature(encrypt(str.toString()));
            kafkaTemplate.send(TOPIC_USER, account.toString());
        } else {
            Error error = new Error();
            error.setCode(ERROR_CODE);
            error.setMessage(ERROR_MESSAGE);
            dto.setError(error);
        }
        return dto;
    }

    public MoneyTransferDTO sendMessageMoney(MoneyTransfer moneyTransfer) throws Exception {
        logger.info(String.format("#### -> Producing message money transfer -> %s", moneyTransfer));
        MoneyTransferDTO dto = new MoneyTransferDTO();
        dto.setSuccess(true); //dump status
        if (dto.isSuccess()) {
            dto.setData(moneyTransfer);
            dto.setStatus("SUCCESS");
        if (!Pattern.compile(CONTENT_REGEX, Pattern.CASE_INSENSITIVE).matcher(moneyTransfer.getContent()).matches()) {
            throw new Exception("Vietnamese without accent or Letters and numbers only");
        }
            StringBuilder str = new StringBuilder();
            str.append(moneyTransfer.getRequest_id());
            str.append(moneyTransfer.getBank_no());
            str.append(moneyTransfer.getAccount_no());
            str.append(moneyTransfer.getAccount_type());
            moneyTransfer.setSignature(encrypt(str.toString()));
            kafkaTemplate.send(TOPIC_MONEY, moneyTransfer.toString());
        } else {
            Error error = new Error();
            error.setCode(ERROR_CODE);
            error.setMessage(ERROR_MESSAGE);
            dto.setError(error);
        }
        return dto;
    }

    public LookUpMoneyTransferDTO sendMessageLoopUpMoney(LookUpMoneyTransfer lookUpMoneyTransfer) throws Exception {
        LookUpMoneyTransferDTO dto = new LookUpMoneyTransferDTO();
        dto.setSuccess(true); //dump status
        if (dto.isSuccess()) {
            // dump data
            LookUpMoneyTransferData data = new LookUpMoneyTransferData();
            data.setRequest_id(lookUpMoneyTransfer.getRequest_id());
            data.setPartner_id(lookUpMoneyTransfer.getPartner_id());
            data.setTransaction_id(lookUpMoneyTransfer.getTransaction_id());
            data.setBank_no("ABBANK");
            data.setAccount_no("account_no");
            data.setAccount_type(1);
            data.setAccount_name("ABBANK - NH An Binh");
            data.setRequest_amount("request amount");
            data.setTransfer_amount("transfer_amount");
            data.setContent("content");
            data.setStatus("SUCCESS");

            StringBuilder str = new StringBuilder();
            str.append(lookUpMoneyTransfer.getRequest_id());
            str.append(data.getBank_no());
            str.append(data.getAccount_no());
            str.append(data.getAccount_type());
            data.setSignature(encrypt(str.toString()));
            dto.setData(data);
            lookUpMoneyTransfer.setSignature(data.getSignature());
            kafkaTemplate.send(TOPIC_LOOK_UP, lookUpMoneyTransfer.toString());
        } else {
            Error error = new Error();
            error.setCode(ERROR_CODE);
            error.setMessage(ERROR_MESSAGE);
            dto.setError(error);
        }
        return dto;
    }


    private String encrypt(String str) throws Exception {
        //base64 encoded public key
        byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA encryption
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }
}
