package FM_Pegatour_Server.sys;

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
        response.put("status", 0);
        response.put("message", LoadServer.date.toString());
    }
}

class Register extends Command {
    public String identifier() {
        return "register";
    }

    public void exec(JSONObject data, JSONObject response) {
        IO.println(data.toString());
        response.put("status", 0);
    }
}

class Deactivate extends Command {
    public String identifier() {
        return "deactivate";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class QueryMembers extends Command {
    public String identifier() {
        return "query_members";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class Modify extends Command {
    public String identifier() {
        return "modify";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class AddRoute extends Command {
    public String identifier() {
        return "add_route";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class QueryRoutes extends Command {
    public String identifier() {
        return "query_routes";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class BookRoute extends Command {
    public String identifier() {
        return "book_route";
    }

    public void exec(JSONObject data, JSONObject response) {

    }
}

class CommentRoute extends Command {
    public String identifier() {
        return "comment_route";
    }

    public void exec(JSONObject data, JSONObject response) {

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

    }
}