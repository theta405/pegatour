package FM_Pegatour_Server.sys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Database {
    private static final Lock lock = new ReentrantLock(); // 数据库互斥锁，确保同时只有一个对象访问数据库
    Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FM", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
            IO.println("数据库连接失败");
        }
    }

    public void addMember(JSONArray array) throws Exception {
        lock.lock();
        int i = 0;
        String sql = "INSERT INTO members (name, passwd, id2, phone, gender, birthday, money, book) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(i + 1, array.getString(i++)); // 名字
        pstmt.setString(i + 1, array.getString(i++)); // 密码
        pstmt.setString(i + 1, array.getString(i++)); // ID2
        pstmt.setString(i + 1, array.getString(i++)); // 手机号
        pstmt.setString(i + 1, array.getString(i++)); // 性别
        pstmt.setString(i + 1, array.getString(i++)); // 出生日期
        pstmt.setInt(i + 1, array.getInt(i++)); // 余额
        pstmt.setString(i + 1, array.getJSONArray(i).toString()); // 预定线路

        pstmt.executeUpdate();

        lock.unlock();
    }

    public boolean hasMember(String id) throws Exception {
        lock.lock();

        String sql = "SELECT id FROM members WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        lock.unlock();

        return rs.next();
    }

    public JSONObject addMembers(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject getMember(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject getMembers(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject removeMembers(String id) throws Exception {
        return new JSONObject();

    }

    public JSONObject modifyMembers(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject addRoute(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject addRoutes(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject getRoute(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject getRoutes(JSONObject input) {
        return new JSONObject();

    }

    public String getTime() throws Exception {
        lock.lock();
        
        Statement stmt = conn.createStatement();
        String sql = "SELECT date FROM fmdate";
        ResultSet rs = stmt.executeQuery(sql);

        lock.unlock();
        
        if (rs.next()) {
            return rs.getString("date");
        } else {
            throw new Exception("时间获取失败");
        }
    }

    public void modifyTime(String date) throws Exception {
        lock.lock();

        String sql = "UPDATE fmdate SET date = ? WHERE id = 1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, date);
        pstmt.executeUpdate();

        lock.unlock();
    }
}