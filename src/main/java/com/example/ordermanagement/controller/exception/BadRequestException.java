package com.example.ordermanagement.controller.exception;

import com.example.ordermanagement.model.Entity;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadRequestException extends AbstractThrowableProblem {
    private Entity entity;
    private String errorCode;
    public BadRequestException(String message, Entity entity, String errorCode) {
        super(null, message, Status.BAD_REQUEST, errorCode);
        this.errorCode = errorCode;
        this.entity = entity;
    }
}
