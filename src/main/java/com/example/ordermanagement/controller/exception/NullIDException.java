package com.example.ordermanagement.controller.exception;

import com.example.ordermanagement.model.Entity;

public class NullIDException extends BadRequestException{
    public NullIDException(Entity entity) {
        super("ID is null", entity, "");
    }
}