package com.vmo.haLongPay.model;

public class MoneyTransfer extends AccountAuthentication {
    private String account_name;
    private int amount;
    private String content;

    public MoneyTransfer(String account_name, int amount, String content) {
        super();
        this.account_name = account_name;
        this.amount = amount;
        this.content = content;
    }

    public MoneyTransfer() {
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MoneyTransfer{" + super.toString() +
                " account_name='" + account_name + '\'' +
                ", amount=" + amount +
                ", content='" + content + '\'' +
                '}';
    }
}
