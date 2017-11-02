package edu.learn.rest.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomResponse implements Serializable {
    private int code;
    private String message;
    private Object responsedata;
}
