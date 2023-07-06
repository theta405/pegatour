package FM_Pegatour_Client.sys;

import org.json.JSONObject;
import org.json.JSONArray;

public abstract class Command { // 指令抽象类
    @Override // 覆写toString方法
    public abstract String toString(); // 在控制台中的名称
    public abstract String identifier(); // 指令标识符，用于对端通讯
    public abstract void exec(); // 执行
}

class OtherCommands {
    private String HELLO = "hello";
    private String TIME = "time";

    public static JSONObject nullData = new JSONObject("{\"null\":\"null\"}");

    private static OtherCommands otherCommands = new OtherCommands();

    private OtherCommands() {}

    public static OtherCommands getInstance() {
        return otherCommands;
    }

    public void hello() {
        JSONObject data = new JSONObject();
        data.put("hello", "client hello");
        JSONObject response = new Request().send(HELLO, data, false);
        if (response.getInt("status") == 0 && response.getString("message").equals("server hello")) {
            IO.println("服务器连接成功");
        } else {
            IO.println(response.getString("message"));
            System.exit(1);
        }
    }

    public String time() {
        JSONObject response = new Request().send(TIME, nullData, true);
        if (response.getInt("status") == 0) {
            return response.getString("message");
        } else {
            IO.println(response.getString("message"));
            System.exit(1);
            return response.getString("message");
        }
    }
}

class Register extends Command {
    public String toString() {
        return "注册会员";
    }

    public String identifier() {
        return "register";
    }

    public void exec() {
        Member member = new Member();

        JSONObject data = new JSONObject();
        data.put("array", member.toArray());

        JSONObject response = new Request().send(identifier(), data, false);

        if (response.getInt("status") == 0) {
            IO.println("注册成功");;
        } else {
            IO.println(response.getString("message"));
        }
    }
}

class Deactivate extends Command {
    public String toString() {
        return "注销会员";
    }

    public String identifier() {
        return "deactivate";
    }

    public void exec() {

    }
}

class QueryMembers extends Command {
    public String toString() {
        return "查询会员";
    }

    public String identifier() {
        return "query_members";
    }

    public void exec() {

    }
}

class Modify extends Command {
    public String toString() {
        return "修改信息";
    }

    public String identifier() {
        return "modify";
    }

    public void exec() {

    }
}

class AddRoute extends Command {
    public String toString() {
        return "添加线路";
    }

    public String identifier() {
        return "add_route";
    }

    public void exec() {

    }
}

class QueryRoutes extends Command {
    public String toString() {
        return "查询线路";
    }

    public String identifier() {
        return "query_routes";
    }

    public void exec() {

    }
}

class BookRoute extends Command {
    public String toString() {
        return "预订线路";
    }

    public String identifier() {
        return "book_route";
    }

    public void exec() {

    }
}

class CommentRoute extends Command {
    public String toString() {
        return "评价线路";
    }

    public String identifier() {
        return "comment_route";
    }

    public void exec() {

    }
}

class ImportMembers extends Command {
    public String toString() {
        return "批量导入会员";
    }

    public String identifier() {
        return "import_members";
    }

    public void exec() {

    }
}

class ImportRoutes extends Command {
    public String toString() {
        return "批量导入线路";
    }

    public String identifier() {
        return "import_routes";
    }

    public void exec() {

    }
}

class ExportMembers extends Command {
    public String toString() {
        return "导出会员信息";
    }

    public String identifier() {
        return "export_members";
    }

    public void exec() {

    }
}

class ExportRoutes extends Command {
    public String toString() {
        return "导出线路信息";
    }

    public String identifier() {
        return "export_routes";
    }

    public void exec() {

    }
}

class ExportSummary extends Command {
    public String toString() {
        return "生成综述";
    }

    public String identifier() {
        return "export_summary";
    }

    public void exec() {

    }
}

class AdvanceDays extends Command {
    public String toString() {
        return "快进天数";
    }

    public String identifier() {
        return "advance_days";
    }

    public void exec() {

    }
}
class Exit extends Command {
    public String toString() {
        return "退出";
    }

    public String identifier() {
        return "";
    }

    public void exec() {
        System.exit(0);
    }
}