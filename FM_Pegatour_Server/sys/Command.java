package FM_Pegatour_Server.sys;

import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException; 
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

    public void exec(JSONObject data, JSONObject response,String path) {
        // 1、构造excel文件输入流对象  
        String sFilePath = path;  
        InputStream is = new FileInputStream(sFilePath);  
        // 2、声明工作簿对象  
        Workbook rwb = Workbook.getWorkbook(is);  
        // 3、获得工作簿的个数,对应于一个excel中的工作表个数  
        rwb.getNumberOfSheets();  
  
        Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称  
        System.out.println("工作表名称：" + oFirstSheet.getName());  
        int rows = oFirstSheet.getRows();//获取工作表中的总行数  
        int columns = oFirstSheet.getColumns();//获取工作表中的总列数  
        for (int i = 1; i < rows; i++) {  
            for (int j = 0; j < columns; j++) {  
                Cell oCell= oFirstSheet.getCell(j,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
                System.out.println(oCell.getContents()+"\r\n");
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
