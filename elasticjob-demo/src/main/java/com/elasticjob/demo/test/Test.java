package com.elasticjob.demo.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) throws SQLException {
        List<String> select = getSelect();
        System.out.println(select);
    }

    public static Connection getCon() {

        //数据库连接名称
        String username = "quixmart";
//        username = "root";
//        username = "quixmart";

        //数据库连接密码
        String password = "qx#dp0810";
//        password = "123123";
//        password = "YourNewPassword";

        String driver = "com.mysql.jdbc.Driver";
        //其中test为数据库名称
        String url = "jdbc:mysql://rm-wz9103hxfjd22t1j2io.mysql.rds.aliyuncs.com:3306/elasticjob_log?useUnicode=true&characterEncoding=utf-8&verifyServerCertificate=false&useSSL=false&requireSSL=false";

//        url = "jdbc:mysql://127.0.0.1:3306/elasticjob_log?useUnicode=true&characterEncoding=utf-8&verifyServerCertificate=false&useSSL=false&requireSSL=false";

//        url = "jdbc:mysql://10.1.1.65:3306/elasticjob_log?useUnicode=true&characterEncoding=utf-8&verifyServerCertificate=false&useSSL=false&requireSSL=false";

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static List<String> getSelect() throws SQLException {
        // sql语句
        String sql = "select * from job_status_trace_log";
        // 获取到连接
        Connection conn = getCon();

        DatabaseMetaData dbMetaData = conn.getMetaData();

//        ResultSet test = dbMetaData.getIndexInfo(null, null, "test", false, false);

        ResultSet resultSet = dbMetaData.getIndexInfo(null, null, "job_execution_log", false, false);

        ResultSet resultSet1 = dbMetaData.getIndexInfo(null, null, "job_status_trace_log", false, false);

        PreparedStatement pst = null;
        // 定义一个list用于接受数据库查询到的内容
        List<String> list = new ArrayList<String>();
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // 将查询出的内容添加到list中
                list.add(rs.getString("job_name"));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
