package com.example.spr.todo.dao;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil(){
        this.modelMapper = new ModelMapper();

        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }
    public ModelMapper get(){
        return modelMapper;
    }
    /*
    * ModelMapper 설정을 변경하려면 getConfiguration()을 이용해서 private 으로 선언된 필드도 접근 가능핟로ㅗㄱ 설정을 변경하고 get()
    * 을 이용해서  ModelMappeer를 사용할 수 있도록 구성해야함
    * */
}
