package com.onlineshop.be.exceptions;

import com.onlineshop.be.enums.Results;

public class CustomizedExceptions extends RuntimeException {

    private Integer code;

    public CustomizedExceptions(Results results) {
        super(results.getMessage);

        this.code = results.getCode;
    }

    public CustomizedExceptions(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
