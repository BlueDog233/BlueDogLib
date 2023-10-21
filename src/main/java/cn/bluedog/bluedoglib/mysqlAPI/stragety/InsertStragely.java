package cn.bluedog.bluedoglib.mysqlAPI.stragety;



import cn.bluedog.bluedoglib.mysqlAPI.MysqlToStringUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class InsertStragely extends AStragety{


    public InsertStragely(String tableName) throws SQLException {
        super(tableName);

    }
    public void setStatement(HashMap<String,String> keyValue){
        statement="insert into "+tableName+" "+ MysqlToStringUtil.getFields(keyValue.keySet())+" values "+MysqlToStringUtil.getFields(keyValue.values());
        System.out.println(statement);
    }
    public void setStatement(Set<String> fields){
        statement="insert into "+tableName+" values "+MysqlToStringUtil.getFields(fields);

    }


    public void execute() throws SQLException {
        runner.executeUpdate(statement);
    }
}
