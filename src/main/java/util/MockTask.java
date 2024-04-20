package util;
import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MockTask {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 生成 JSON 数据
        JSONObject taskParamJson = new JSONObject();
        taskParamJson.put("startDate", "2024-04-20");
        taskParamJson.put("endDate", "2024-04-30");
        taskParamJson.put("startAge", 18);
        taskParamJson.put("professionals", "doctor");
        taskParamJson.put("endAge", 30);
        taskParamJson.put("cities", "Beijing");
        taskParamJson.put("sex", "male");
        taskParamJson.put("keywords", "火锅");
        taskParamJson.put("categoryIds", 1001);
        taskParamJson.put("targetPageFlow", "page1");

        // 将 JSON 数据转换为字符串
        String taskParam = taskParamJson.toString();
        Connection conn = null;

        // 连接数据库
        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/analysisofuserbehavior",
                "root",
                "123456");
        String sql = "UPDATE task SET taskParam = ? WHERE task_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, taskParam);
        statement.setInt(2, 1); // 设置 task_id
        statement.executeUpdate();
        System.out.println("JSON 数据成功插入数据库！");
    }
}