package FM_Pegatour_Client.sys;

import org.json.JSONObject;

public abstract class Command { // 指令抽象类
    @Override // 覆写toString方法
    public abstract String toString(); // 在控制台中的名称
    public abstract String identifier(); // 指令标识符，用于对端通讯
    public abstract void exec(); // 执行
}

class Hello extends Command {
    public String toString() {
        return "测试连接";
    }

    public String identifier() {
        return "hello";
    }

    public void exec() {
        IO.println("正在连接服务器...");
        JSONObject data = new JSONObject();
        data.put("hello", "client hello");
        JSONObject response = Request.getInstance().send(identifier(), data);
        if (response.getInt("status") == 0) {
            IO.println("连接成功");
        } else {
            IO.println(response.getString("message"));
            System.exit(1);
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