package com.example.spr.todo.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
@Log4j2
public class LoginListener implements HttpSessionAttributeListener {

        @Override
        public void attributeAdded(HttpSessionBindingEvent event){  //세션이 추가되면 발생
            String name = event.getName();

            Object obj = event.getValue();

            if(name.equals("loginInfo")){
                log.info("A user logined....");
                log.info(obj);
            }
        }
}