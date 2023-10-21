package cn.bluedog.bluedoglib.mysqlAPI.stragety;

import java.sql.SQLException;

public class DeleteStragely extends AStragety{
    public DeleteStragely(String tableName) throws SQLException {
        super(tableName);
    }
    public void setStatement(String condition){
        statement="delete from "+tableName+" where "+condition;
    }

    public void execute() throws SQLException {
        runner.executeUpdate(statement);
    }
}
