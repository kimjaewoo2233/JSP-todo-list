package com.example.spr.todo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class TodoVO {

        private Long tno;

        private String title;

        private LocalDate dueDate;

        private boolean finished;
}
