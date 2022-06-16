package com.vmo.haLongPay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookUpMoneyTransferDTO {
    private boolean success;
    private Error error;
    private LookUpMoneyTransferData data;

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

    public LookUpMoneyTransferData getData() {
        return data;
    }

    public void setData(LookUpMoneyTransferData data) {
        this.data = data;
    }
}
