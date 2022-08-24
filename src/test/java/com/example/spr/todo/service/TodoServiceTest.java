package com.example.spr.todo.service;

import com.example.spr.todo.dto.TodoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    private TodoService todoService;

    @BeforeEach
    public void ready(){
        todoService = TodoService.INSTANCE;

    }

    @Test
    public void testRegister() throws Exception{
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC TEst Title")
                .dueDate(LocalDate.now())
                .build();

        todoService.register(todoDTO);
    }
}