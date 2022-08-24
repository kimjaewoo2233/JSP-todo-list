package com.example.spr.todo.service;

import com.example.spr.todo.dao.MapperUtil;
import com.example.spr.todo.dao.TodoDAO;
import com.example.spr.todo.domain.TodoVO;
import com.example.spr.todo.dto.TodoDTO;
import jdk.vm.ci.meta.Local;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum TodoService {

    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService(){
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO dto) throws Exception{
        TodoVO todoVo = modelMapper.map(dto,TodoVO.class);

        log.info(todoVo);

        dao.insert(todoVo);
        /*
        * Register() 는 TodoDTO를 파라미터로 받아서 TodoVO로 변환하는 과정이 필요하다.
        * 이를 확인하기 위해서 ModelMapper로 처리된 TodoVO를 로그찍어서 확인한다.
        * */
    }

    public List<TodoDTO> listAll() throws Exception{
        List<TodoVO> voList = dao.selectAll();

        log.info("voList......................");

        List<TodoDTO> dtoList = voList.stream()
                .map(m->modelMapper.map(m,TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }
    public TodoDTO get(Long tno) throws Exception{
        log.info("tno:  ",tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO,TodoDTO.class);
        return todoDTO;
    }
    public void remove(Long tno) throws Exception{
        log.info("tno: "+tno);
        dao.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception{
        log.info("todoDTO: "+todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO,TodoVO.class);
        dao.updateOne(todoVO);
    }


}
