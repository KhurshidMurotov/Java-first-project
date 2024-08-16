package com.company.firstprojectspring.service.mapper;


import com.company.firstprojectspring.dto.UserDto;
import com.company.firstprojectspring.module.Card;
import com.company.firstprojectspring.module.Users;
import com.company.firstprojectspring.service.impl.CardServiceImpl;
import com.company.firstprojectspring.service.impl.ProdServiceImpl;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Lazy
    @Autowired
    protected   CardServiceImpl cardService;

    @Lazy
    @Autowired
    protected   ProdServiceImpl prodService;


    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "cardList", ignore = true)
    @Mapping(target = "prodList", ignore = true)
    public abstract Users toEntity(UserDto dto);

/*
    public Users toEntity(UserDto dto) {
        Users users = new Users();
        users.setFirstname(dto.getFirstname());
        users.setLastname(dto.getLastname());
        users.setEmail(dto.getEmail());
        users.setPassword(dto.getPassword());
        users.setAge(dto.getAge());
        return users;
    }
*/

    @Named(value = "toDto")
    @Mapping(target = "cardList", ignore = true)
    @Mapping(target = "prodList", ignore = true)
    public abstract UserDto toDto(Users users);

    @Named(value = "toDtoWithAllEntity")
    @Mapping(target = "cardList", expression = "java(this.cardService.getAllCardsByUserId(users.getUserId()))")
    @Mapping(target = "prodList", expression = "java(this.prodService.getAllProdsByUserId(users.getUserId()))")
    public abstract UserDto toDtoWithAllEntity(Users users);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<UserDto> toDtoList(List<Users> list) ;


    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "cardList", ignore = true)
    @Mapping(target = "prodList", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Users updateUserAllFields(@MappingTarget Users users, UserDto dto);


    public void updateHolderName(List<Card> cardList, Users users, Integer userId){
        String holderName = String.format("%s %s", users.getFirstname(), users.getLastname());
        for (Card card : cardList) {
            if (card.getUserId().equals(userId)){
                card.setHolderName(holderName);
            }
        }
    }
}



