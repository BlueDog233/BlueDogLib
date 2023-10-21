package cn.bluedog.bluedoglib.mysqlAPI;





import cn.bluedog.bluedoglib.mysqlAPI.stragety.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlManager {

    private static MysqlManager mysqlManager;
    private Connection connect;

    public Connection getConnect() {
        return connect;
    }

    public Statement getStatement() {
        return statement;
    }

    private Statement statement;
    private MysqlManager() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/";
        connect= DriverManager.getConnection(url,"root","123456");
        statement=connect.createStatement();
        statement.executeUpdate("create database if not exists mcserver");
        connect=DriverManager.getConnection(url+"mcserver?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","123456");
        statement=connect.createStatement();
    }
    public static MysqlManager getInstance() throws SQLException {
        if(mysqlManager==null){


            mysqlManager=new MysqlManager();

        }
        return mysqlManager;
    }
    public AStragety getStragety(StragetlyType type, String tableName) throws SQLException {
        switch (type) {
            case SET:
                return new SetStragely(tableName);
            case INSERT:
                return new InsertStragely(tableName);
            case QUERY:
                return new QueryStragely(tableName);
            case DELETE:
               return new DeleteStragely(tableName);
            case CREATE_TABLE:
                return new CreateTableStragely(tableName);
            default:
                return null;
        }

    }

}
