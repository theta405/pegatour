package FM_Pegatour_Server.sys;

public abstract class Command { // 指令抽象类
    public abstract String identifier(); // 指令标识符，用于对端通讯
    public abstract void exec(); // 执行
}

class Hello extends Command {
    public String identifier() {
        return "hello";
    }

    public void exec() {
        
    }
}

class Register extends Command {
    public String identifier() {
        return "register";
    }

    public void exec() {
        
    }
}

class Deactivate extends Command {
    public String identifier() {
        return "deactivate";
    }

    public void exec() {

    }
}

class Modify extends Command {
    public String identifier() {
        return "modify";
    }

    public void exec() {

    }
}

class AddRoute extends Command {
    public String identifier() {
        return "add_route";
    }

    public void exec() {

    }
}

class ModifyRoute extends Command {
    public String identifier() {
        return "modify_route";
    }

    public void exec() {

    }
}

class QueryRoute extends Command {
    public String identifier() {
        return "query_route";
    }

    public void exec() {

    }
}

class BookRoute extends Command {
    public String identifier() {
        return "book_route";
    }

    public void exec() {

    }
}

class CancelRoute extends Command {
    public String identifier() {
        return "cancel_route";
    }

    public void exec() {

    }
}

class CommentRoute extends Command {
    public String identifier() {
        return "comment_route";
    }

    public void exec() {

    }
}

class BatchImport extends Command {
    public String identifier() {
        return "batch_import";
    }

    public void exec() {

    }
}

class ExportQuery extends Command {
    public String identifier() {
        return "export_query";
    }

    public void exec() {

    }
}

class ExportSummary extends Command {
    public String identifier() {
        return "export_summary";
    }

    public void exec() {

    }
}

class AdvanceDays extends Command {
    public String identifier() {
        return "advance_days";
    }

    public void exec() {

    }
}