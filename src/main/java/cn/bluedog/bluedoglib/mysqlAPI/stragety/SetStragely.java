package cn.bluedog.bluedoglib.mysqlAPI.stragety;

import java.sql.SQLException;

public class SetStragely extends AStragety{
    public SetStragely(String tableName) throws SQLException {
        super(tableName);
    }
    public void setStatement(String key,String value,String condition){
        statement="update "+tableName+" set "+key+"="+value+" where "+condition;
    }
    public void setStatement(String key,String value){
        statement="update "+tableName+" set "+key+"="+value;
    }

    public void execute() throws SQLException {
        runner.execute(statement);
    }
}
