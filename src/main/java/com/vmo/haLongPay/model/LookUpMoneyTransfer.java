package com.vmo.haLongPay.model;

public class LookUpMoneyTransfer {
    private String request_id;
    private String partner_id;
    private String transaction_id;
    private String signature;

    public LookUpMoneyTransfer(String request_id, String partner_id, String transaction_id, String signature) {
        this.request_id = request_id;
        this.partner_id = partner_id;
        this.transaction_id = transaction_id;
        this.signature = signature;
    }

    public LookUpMoneyTransfer() {
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "LookUpMoneyTransfer{" +
                "request_id='" + request_id + '\'' +
                ", partner_id='" + partner_id + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
