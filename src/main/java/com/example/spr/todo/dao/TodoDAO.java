package com.example.spr.todo.dao;

import com.example.spr.todo.domain.TodoVO;
import lombok.Cleanup;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public String getTime(){
        String now = null;
        /*
        * try - with - resources 기능을 이용해서 try() 내에서 선언된 변수들이 자동으로 close() 될 수 있는 구조로 작성됨
        * try() 내에 선언된 변수들은 모두 Auto-Closeable 이라는 인터페이스를 구현한 구현체만 가능하다. 저 3개와 statement도 가능
        * */
        try(
                Connection connection = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select now()");
                ResultSet resultSet = preparedStatement.executeQuery();
                ){
                resultSet.next();   //첫줄에 없고 다음줄부터 데이터가있음

                now = resultSet.getString(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception{       //lombok에 @Cleanup 추가된 변수는 해당 메소드가 끝날떄  close() 호출되는 것을 보장
         @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
         @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
         @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

         resultSet.next();

        String now = resultSet.getString(1);

        return now;
    }

    public void insert(TodoVO todoVO) throws Exception{
        String sql = "insert into tbl_todo(title,dueDate,finished) values (?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());

        preparedStatement.executeUpdate();
    }

    public List<TodoVO> selectAll() throws Exception{
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVO> list = new ArrayList<>();


        while(resultSet.next()){
            TodoVO vo = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();

            list.add(vo);
        }

        return list;
    }

    public TodoVO selectOne(Long tno) throws  Exception{
        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,tno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }

    public void deleteOne(Long tno) throws Exception{
        String sql = "delete from tbl_todo where tno=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1,tno);

        preparedStatement.executeUpdate();

    }

    public void updateOne(TodoVO vo) throws Exception{
        String sql = "UPDATE tbl_todo SET title=?, dueDate=?, finished=? WHERE tno=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,vo.getTitle());
        preparedStatement.setDate(2,Date.valueOf(vo.getDueDate() ));
        preparedStatement.setBoolean(3,vo.isFinished());
        preparedStatement.setLong(4,vo.getTno());

       preparedStatement.executeUpdate();
    }
}
