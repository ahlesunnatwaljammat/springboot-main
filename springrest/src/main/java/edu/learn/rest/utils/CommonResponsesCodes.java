package edu.learn.rest.utils;

public class CommonResponsesCodes {
    public static final int UNAUTHORIZED_CODE = 403;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int BAD_RESPONSE_CODE = 400;
    public static final int GOOD_RESPONSE_CODE = 200;

    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized access";
    public static final String METHOD_NOT_ALLOWED_MESSAGE = "Method not allowed";
    public static final String BAD_RESPONSE_MESSAGE = "Missing Information";
    public static final String GOOD_RESPONSE_MESSAGE = "OK";
}
