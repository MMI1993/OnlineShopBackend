package com.onlineshop.be.enums;

public enum Results {

    PARAM_ERROR(1, "Parameter Error!"),
    PRODUCT_NOT_EXIST(10, "Product does not exit!"),
    PRODUCT_LIMITED_STOCK(11, "Not enough products in stock!"),
    PRODUCT_STATUS_ERROR(12, "Status is incorrect!"),
    PRODUCT_OFF_SALE(13,"Product is off sale!"),
    PRODUCT_NOT_IN_CART(14,"Product is not in the cart!"),
    CART_CHECKOUT_SUCCESS(20, "Checkout successfully! "),

    CATEGORY_NOT_FOUND(30, "Category does not exit!"),

    ORDER_NOT_FOUND(60, "OrderMain is not exit!"),
    ORDER_STATUS_ERROR(61, "Status is not correct"),


    VALID_ERROR(50, "Wrong information"),
    USER_NOT_FOUNT(40,"User is not found!")
    ;

    public Integer getCode;

    public String getMessage;

    Results(Integer code, String message) {
        this.getCode = code;
        this.getMessage = message;
    }
}
