package com.example.spr.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

    @Test
    public void test1(){
        int v1 = 10;
        int v2 = 10;

        Assertions.assertEquals(v1,v2);
    }

    @Test
    public void testConnection() throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");
        /*
        * JDBC 드라이버 클래스를 메모리상으로 로딩하는 역할을 한다. 이떄 문자열은 패키지
        * 명과 클래스명으 대소문자까지 정확히 일치해야한다. 만약 드라이버 파일이 없다면 예외
        * */

        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3307/webdb",  //JDBC 프로토콜 이용한다.,
                "webuser",
                "1234");
        /*
        * java.sql 패키지의 Connection 인터페이스 타입의 변수이다. Connection은
        * 데이터베이스와 네트워크 연결을 의미한다.
        *
        * DriverManager.getConneection() - 데이터베이스 내에 있는
        * 여러 정보들을 통해서 특정한 데이터베이스에 연결을 시도한다.
        * */

        Assertions.assertNotNull(connection);
        connection.close();
    }

    @Test
    public void testHikariCP() throws Exception{
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
        config.setUsername("webuser");
        config.setPassword("1234");
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();

        System.out.println(connection);

        connection.close();
    }
}
