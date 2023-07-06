package FM_Pegatour_Server.sys;

import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Command { // 指令抽象类
    public abstract String identifier(); // 指令标识符，用于对端通讯
    public abstract void exec(JSONObject data, JSONObject response); // 执行
}

class Hello extends Command {
    public String identifier() {
        return "hello";
    }

    public void exec(JSONObject data, JSONObject response) {
        if (data.getString("hello").equals("client hello")) {
            response.put("status", 0);
            response.put("message", "server hello");
        } else {
            response.put("status", 1);
            response.put("message", "服务器拒绝连接");
        }
    }
}

class Time extends Command {
    public String identifier() {
        return "time";
    }

    public void exec(JSONObject data, JSONObject response) {
        try {
            LoadServer.date.getFMDate();
            response.put("status", 0);
            response.put("message", LoadServer.date.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", 1);
            response.put("message", "时间获取失败");
        }
    }
}

class Register extends Command {
    public String identifier() {
        return "register";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            db.addMember(data.getJSONArray("array"));
            response.put("status", 0);
            response.put("message", "注册成功");
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败或年龄不够18岁");
            e.printStackTrace();
        }
    }
}

class Deactivate extends Command {
    public String identifier() {
        return "deactivate";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            boolean result = db.removeMember(data.getString("id"));
            response.put("status", 0);
            if (result) {
                response.put("message", "注销成功");
            } else {
                response.put("message", "会员不存在");
            }
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败");
            e.printStackTrace();
        }
    }
}

class QueryMembers extends Command {
    public String identifier() {
        return "query_members";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            JSONArray result = db.getMembers();
            response.put("status", 0);
            response.put("message", result);
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败，或没有会员");
            e.printStackTrace();
        }
    }
}

class Modify extends Command {
    public String identifier() {
        return "modify";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            boolean result = db.modifyMembers(data.getString("id"), data.getString("pass"));
            response.put("status", 0);
            if (result) {
                response.put("message", "修改成功");
            } else {
                response.put("message", "会员不存在");
            }
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败");
            e.printStackTrace();
        }
    }
}

class Deposit extends Command {
    public String identifier() {
        return "deposit";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            int money = data.getInt("money");
            if (money >= 888) {
                money *= 1.1;
            }
            boolean result = db.deposit(data.getString("id"), money);
            response.put("status", 0);
            if (result) {
                response.put("message", "充值成功");
            } else {
                response.put("message", "会员不存在");
            }
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败");
        }
    }
}

class AddRoute extends Command {
    public String identifier() {
        return "add_route";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            db.addRoute(data.getJSONArray("array"));
            response.put("status", 0);
            response.put("message", "添加成功");
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败");
            e.printStackTrace();
        }
    }
}

class QueryRoutes extends Command {
    public String identifier() {
        return "query_routes";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            JSONArray result = db.getRoutes();
            response.put("status", 0);
            response.put("message", result);
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败，或没有线路");
            e.printStackTrace();
        }
    }
}

class BookRoute extends Command {
    public String identifier() {
        return "book_route";
    }

    public void exec(JSONObject data, JSONObject response) {
        Database db = new Database();
        try {
            boolean result = db.book(data.getString("memberID"), data.getString("routeID"));
            response.put("status", 0);
            if (result) {
                response.put("message", "预订成功");
            } else {
                response.put("message", "预订失败，可能是数据不存在、线路已预订或过期");
            }
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败");
            e.printStackTrace();
        }
    }
}

class ImportMembers extends Command {
    public String identifier() {
        return "import_members";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class ImportRoutes extends Command {
    public String identifier() {
        return "import_routes";
    }

    public void exec(JSONObject data, JSONObject response) {
        
    }
}

class ExportMembers extends Command {
    public String identifier() {
        return "export_members";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class ExportRoutes extends Command {
    public String identifier() {
        return "export_routes";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class ExportSummary extends Command {
    public String identifier() {
        return "export_summary";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class AdvanceDays extends Command {
    public String identifier() {
        return "advance_days";
    }

    public void exec(JSONObject data, JSONObject response) {
        try {
            LoadServer.date.addDays(data.getInt("days"));
            LoadServer.date.setFMDate();
            response.put("status", 0);
            response.put("message", "快进成功");
        } catch (Exception e) {
            response.put("status", 1);
            response.put("message", "数据库操作失败");
        }
    }
}
