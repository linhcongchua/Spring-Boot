package com.learn.meme.security;

public enum UserPermission {
    REQUEST_ORDER("request_order"),
    CONFIRM_ORDER("confirm_order"),
    PRODUCT_READ("product_read"),
    PRODUCT_WRITE("product_write"),
    USER_READ("user_read"),
    USER_WRITE("user_write"),
    MANAGE_STAFF("manage_staff");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
