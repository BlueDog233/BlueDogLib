package cn.bluedog.bluedoglib.mysqlAPI.stragety;

import jdk.jfr.Description;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class QueryStragely extends AStragety{
    public QueryStragely(String tableName) throws SQLException {
        super(tableName);
    }
    public int getInt(String key,String condition) throws SQLException {
        statement="select "+key+" from "+ tableName+" where "+condition;
        ResultSet set=runner.executeQuery(statement);
        return set.getInt(key);
    }
    public String getString(String key,String condition) throws SQLException {
        statement="select "+key+" from "+ tableName+" where "+condition;
        ResultSet set=runner.executeQuery(statement);
        set.next();
        return set.getString(key);
    }
    public HashMap getStringValues(String key) throws SQLException {
        statement="select name,"+key+" from "+ tableName;
        ResultSet x= runner.executeQuery(statement);
        HashMap<String,String> ss=new HashMap<>();
        while(x.next()){
            ss.put("name",x.getString(key));
        }
        return ss;
    }
    public HashMap getIntValues(String key) throws SQLException {
        statement="select name,"+key+" from "+ tableName;
        ResultSet x= runner.executeQuery(statement);
        HashMap<String,Integer> ss=new HashMap<>();
        while(x.next()){
            ss.put("name",x.getInt(key));
        }
        return ss;
    }
    @Description("自由查询")
    public ResultSet executeQuery(String statement) throws SQLException {
        return runner.executeQuery(statement);
    }

}
