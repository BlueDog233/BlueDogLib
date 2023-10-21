package cn.bluedog.bluedoglib.mysqlAPI;

import java.util.Collection;

public class MysqlToStringUtil {
    public static String getFields(Collection<String> fields){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("(");
        for (String field : fields) {
            stringBuilder.append(field+", ");
        }
        stringBuilder.delete(stringBuilder.length()-2,stringBuilder.length());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }



}
