package com.vmo.haLongPay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "users")
    public void consumeAccount(String account) throws IOException {
        logger.info(String.format("#### -> Account message -> %s", account));
    }

    @KafkaListener(topics = "money")
    public void consumeMoney(String money) throws IOException {
        logger.info(String.format("#### -> Money Transfer message -> %s", money));
    }

    @KafkaListener(topics = "lookup")
    public void consumeLookup(String lookup) throws IOException {
        logger.info(String.format("#### -> Lookup Money Transfer message -> %s", lookup));
    }
}
