package com.vmo.haLongPay.model;

public class AccountAuthentication {

    private String request_id;
    private String partner_id;
    private String bank_no;
    private String account_no;
    private int account_type;
    private String signature;

    public AccountAuthentication(String request_id, String partner_id, String bank_no, String account_no, int account_type, String signature) {
        this.request_id = request_id;
        this.partner_id = partner_id;
        this.bank_no = bank_no;
        this.account_no = account_no;
        this.account_type = account_type;
        this.signature = signature;
    }

    public AccountAuthentication() {
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

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public int getAccount_type() {
        return account_type;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "AccountAuthentication{" +
                "request_id='" + request_id + '\'' +
                ", partner_id='" + partner_id + '\'' +
                ", bank_no='" + bank_no + '\'' +
                ", account_no='" + account_no + '\'' +
                ", account_type=" + account_type +
                ", signature='" + signature + '\'' +
                '}';
    }
}
