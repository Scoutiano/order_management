package com.example.ordermanagement.controller.exception;

import com.example.ordermanagement.model.Entity;

public class NullDTOException extends BadRequestException{
    public NullDTOException(Entity entity) {
        super(" DTO is null", entity, "");
    }
}