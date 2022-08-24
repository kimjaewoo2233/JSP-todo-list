package com.example.spr.todo.controller;

import com.example.spr.todo.dto.TodoDTO;
import com.example.spr.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet(name = "todoModifyController",value = "/todo/modify")
@Log4j2
public class TodoModifyController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      try{
          Long tno = Long.parseLong(request.getParameter("tno"));
          TodoDTO dto = todoService.get(tno);
          //데이터담기
          request.setAttribute("dto",dto);
          request.getRequestDispatcher("/WEB-INF/todo/modifiy.jsp").forward(request,response);
      }catch (Exception e){
          log.error(e.getMessage());
          throw new ServletException("modify get... error");
      }
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String finishedStr = request.getParameter("finished");

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(Long.parseLong(request.getParameter("tno")))
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATEFORMATTER))
                .finished(finishedStr != null && finishedStr.equals("on"))
                .build();

        log.info("/todo/modify POST...");
        log.info(todoDTO);
        try{
            todoService.modify(todoDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/todo/list");
    }
}
