package com.github.yeecode.easyrpc.client.rpc;

public class Result {
    private Boolean success;
    private String message;
    private String resultType;
    private String resultValue;

    public Result(Boolean success, String message, String resultType, String resultValue) {
        this.success = success;
        this.message = message;
        this.resultType = resultType;
        this.resultValue = resultValue;
    }

    public static Result getSuccessResult(String resultType, String resultValue) {
        return new Result(true, "成功", resultType, resultValue);
    }

    public static Result getFailResult(String reason) {
        return new Result(false, reason, null, null);
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }
}
