package com.example.ordermanagement.controller.exception;


import com.example.ordermanagement.model.Entity;

public class AssetNotFoundException extends BadRequestException{
    public AssetNotFoundException(Entity entity) {
        super(entity.name() + " not found", entity, "");
    }
}
