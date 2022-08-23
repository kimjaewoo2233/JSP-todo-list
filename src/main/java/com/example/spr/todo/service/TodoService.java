package com.example.spr.todo.service;

import com.example.spr.todo.dto.TodoDTO;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum TodoService {

    INSTANCE;


    public void register(TodoDTO todoDTO){
        System.out.println("DEBUG............."+todoDTO);
    }
    public List<TodoDTO> getList(){
        List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(i -> {  //0~9
            TodoDTO dto= new TodoDTO();
            dto.setTno((long)i);
            dto.setTitle("Todo.."+i);
            dto.setDueDate(LocalDate.now());

            return dto;
        }).collect(Collectors.toList());

        return todoDTOS;
    }

    public TodoDTO get(Long tno){
        TodoDTO dto = new TodoDTO();
        dto.setTno(tno);
        dto.setTitle("Sample Todo");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);

        return dto;

    }

}
