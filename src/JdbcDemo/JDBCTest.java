package JdbcDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {

    public static void main(String[] args) {
        CRUD crud = new CRUD();
        crud.selectDemo();
    }
}
