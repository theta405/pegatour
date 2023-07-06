package FM_Pegatour_Server.sys;

import org.json.JSONObject;

import java.util.function.Function;

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

    public JSONObject executeDatabase(Function<JSONObject, JSONObject> method, JSONObject input) {
        lock.lock();
        try {
            return method.apply(input);
        } catch (Exception e) {
            e.printStackTrace();
            return "数据库操作失败";
        } finally {
            lock.unlock();
        }
    }

    public JSONObject addMember(JSONObject input) {

    }

    public JSONObject addMembers(JSONObject input) {

    }

    public JSONObject getMember(JSONObject input) {

    }

    public JSONObject getMembers(JSONObject input) {

    }

    public JSONObject removeMembers(JSONObject input) {

    }

    public JSONObject modifyMembers(JSONObject input) {

    }

    public JSONObject addRoute(JSONObject input) {

    }

    public JSONObject addRoutes(JSONObject input) {

    }

    public JSONObject getRoute(JSONObject input) {

    }

    public JSONObject getRoutes(JSONObject input) {

    }

    public JSONObject getTime(JSONObject input) {

    }

    public JSONObject modifyTime(JSONObject input) {

    }
}