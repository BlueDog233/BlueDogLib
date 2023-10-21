package cn.bluedog.bluedoglib.mysqlAPI.stragety;

import jdk.jfr.Description;

import java.sql.SQLException;

public class CreateTableStragely extends AStragety{

    public CreateTableStragely(String tableName) throws SQLException {
        super(tableName);
    }
    @Description("完整建表语句")
    public void setStatement(String s){
        statement=s;
    }

    public void execute() throws SQLException {
        runner.executeUpdate(statement);
    }
}
