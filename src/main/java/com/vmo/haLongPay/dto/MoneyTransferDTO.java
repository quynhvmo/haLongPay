package com.vmo.haLongPay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vmo.haLongPay.model.MoneyTransfer;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoneyTransferDTO {
    private boolean success;
    private Error error;
    private MoneyTransfer data;
    private String status;
    private String created_at = Instant.now().toString();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public MoneyTransfer getData() {
        return data;
    }

    public void setData(MoneyTransfer data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
