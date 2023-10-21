package com.playready.PlayReadyBackend.service;


public interface ServiceInterface<Entity,Dto> {
    Dto convertToDto(Entity entity);
    Entity convertToEntity(Dto dto);
}
