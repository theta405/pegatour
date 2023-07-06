package FM_Pegatour_Server.sys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.naming.spi.DirStateFactory.Result;

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
        LoadServer.date.getFMDate();
        if (LoadServer.date.years(array.getString(5)) < 18) {
            lock.unlock();
            throw new Exception("年龄不够");
        }

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

    public JSONArray getMembers() throws Exception {
        lock.lock();
        JSONArray array = new JSONArray();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM members");
        ResultSetMetaData rsmd = rs.getMetaData();

        lock.unlock();

        if (!rs.isBeforeFirst()) {
            throw new Exception("表为空");
        }

        while (rs.next()) {
            JSONArray arr = new JSONArray();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnName(i);
                arr.put(rs.getObject(columnName));
            }
            array.put(arr);
        }

        return array;
    }

    public boolean removeMember(String id) throws Exception {
        if(hasMember(id)) {
            lock.lock();
            String sql = "DELETE FROM members WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
            lock.unlock();
            return true;
        } else {
            return false;
        }
    }

    public boolean modifyMembers(String id, String pass) throws Exception {
        if(hasMember(id)) {
            lock.lock();
            String sql = "UPDATE members SET passwd = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pass);
            pstmt.setInt(2, Integer.parseInt(id));
            pstmt.executeUpdate();
            lock.unlock();
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(String id, int money) throws Exception {
        if(hasMember(id)) {
            lock.lock();
            String sql = "UPDATE members SET money = money + ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setInt(2, Integer.parseInt(id));
            pstmt.executeUpdate();
            lock.unlock();
            return true;
        } else {
            return false;
        }
    }

    public boolean book(String memberID, String routeID) throws Exception {
        if (!hasMember(memberID) || !hasRoute(routeID)) {
            return false;
        }

        Statement stmt = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT book FROM members WHERE id = '" + memberID + "'");
        ResultSet rs2 = stmt2.executeQuery("SELECT date FROM routes WHERE id = '" + routeID + "'");
        
        if (rs.next() && rs2.next()) {
            Date routeDate = new Date();
            routeDate.parseDate(rs2.getString("date"));
            LoadServer.date.getFMDate();
            if (LoadServer.date.compareTo(routeDate) > 0) {
                return false;
            }

            String book = rs.getString("book");
            JSONArray bookArray = (book != null && !book.isEmpty()) ? new JSONArray(book) : new JSONArray();
            for (int i = 0; i < bookArray.length(); i++) {
                if (bookArray.getString(i).equals(routeID)) {
                    return false;
                }
            }

            bookArray.put(routeID);
            String newBook = bookArray.toString();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE members SET book = ? WHERE id = ?");
            pstmt.setString(1, newBook);
            pstmt.setString(2, memberID);
            pstmt.executeUpdate();
        } else {
            return false;
        }
        
        return true;
    }

    public void addRoute(JSONArray array) throws Exception {
        lock.lock();

        String sql = "INSERT INTO routes (destination, date) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, array.getString(0)); // 名字
        pstmt.setString(2, array.getString(1)); // 密码

        pstmt.executeUpdate();

        lock.unlock();
    }

    public JSONObject addRoutes(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject getRoute(JSONObject input) {
        return new JSONObject();

    }

    public boolean hasRoute(String id) throws Exception {
        lock.lock();

        String sql = "SELECT id FROM routes WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        lock.unlock();

        return rs.next();
    }

    public JSONArray getRoutes() throws Exception {
        lock.lock();
        JSONArray array = new JSONArray();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM routes");
        ResultSetMetaData rsmd = rs.getMetaData();

        lock.unlock();

        if (!rs.isBeforeFirst()) {
            throw new Exception("表为空");
        }

        while (rs.next()) {
            JSONArray arr = new JSONArray();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnName(i);
                arr.put(rs.getObject(columnName));
            }
            array.put(arr);
        }

        return array;
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