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
        JSONObject result;

        try {
            result = method.apply(input);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JSONObject("数据库操作失败");
        } finally {
            lock.unlock();
        }
        return result;
    }

    public JSONObject addMember(JSONObject input) {
        return new JSONObject();
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

    public JSONObject removeMembers(JSONObject input) {
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

    public JSONObject getTime(JSONObject input) {
        return new JSONObject();

    }

    public JSONObject modifyTime(JSONObject input) {
        return new JSONObject();

    }
}