package cn.bluedog.bluedoglib.mysqlAPI.stragety;



import cn.bluedog.bluedoglib.mysqlAPI.MysqlManager;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AStragety {

    String tableName=null;
    String statement=null;
    Statement runner=null;
    public AStragety(String tableName) throws SQLException {
        this.tableName=tableName;
        runner= MysqlManager.getInstance().getStatement();
    }


}
