package com.vmo.haLongPay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vmo.haLongPay.model.AccountAuthentication;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountAuthenticationDTO {
    private boolean success;
    private Error error;
    private AccountAuthentication data;

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

    public AccountAuthentication getData() {
        return data;
    }

    public void setData(AccountAuthentication data) {
        this.data = data;
    }
}
