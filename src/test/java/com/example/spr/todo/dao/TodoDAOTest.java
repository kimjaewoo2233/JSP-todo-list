package com.example.spr.todo.dao;

import com.example.spr.todo.domain.TodoVO;
import com.sun.tools.javac.comp.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoDAOTest {

    private TodoDAO todoDAO;

    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception{
        System.out.println(todoDAO.getTime());
    }

    @Test
    public void testInsert() throws Exception{
        TodoVO todoVo = TodoVO.builder()
                .title("Sample Title")
                .dueDate(LocalDate.of(2021,12,31))
                .build();

        todoDAO.insert(todoVo);
    }
    @Test
    public void testList() throws Exception{
        List<TodoVO> list = todoDAO.selectAll();

        list.forEach(System.out::println);
    }
    @Test
    public void testSelect() throws Exception{
        Long tno = 1L;
        TodoVO vo = todoDAO.selectOne(tno);
        System.out.println(vo);
    }

    @Test
    public void testUpdateOne() throws Exception{
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);
    }
}