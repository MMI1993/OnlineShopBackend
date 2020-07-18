package com.onlineshop.be.enums;

public enum OrderAndProductStatus implements CodeAndMessage {
    UP(0, "Available"),
    DOWN(1, "Unavailable"),
    NEW(0, "New OrderMain"),
    FINISHED(1, "Finished"),
    CANCELED(2, "Canceled");

    private Integer code;
    private String message;

    OrderAndProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getStatus(Integer code) {

        for (OrderAndProductStatus statusEnum : OrderAndProductStatus.values()) {
            if (statusEnum.getCode() == code) return statusEnum.getMessage();
        }
        return "";
    }


    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
