package FM_Pegatour_Client.sys;

import org.json.JSONObject;
import org.json.JSONArray;
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;



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

class Deposit extends Command {
    public String toString() {
        return "账户充值";
    }

    public String identifier() {
        return "deposit";
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

class ImportMembers extends Command {
    public String toString() {
        return "批量导入会员";
    }

    public String identifier() {
        return "import_members";
    }

    public void exec() {
        // JSONObject data = new JSONObject();
        // JSONObject data2 = new JSONObject();
        // String[] Columns = {};
        // // 1、构造excel文件输入流对象  
        // String sFilePath = path;  
        // InputStream is = new FileInputStream(sFilePath);  
        // // 2、声明工作簿对象  
        // Workbook rwb = Workbook.getWorkbook(is);  
        // // 3、获得工作簿的个数,对应于一个excel中的工作表个数  
        // rwb.getNumberOfSheets();  
  
        // Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称  
        // System.out.println("工作表名称：" + oFirstSheet.getName());  
        // int rows = oFirstSheet.getRows();//获取工作表中的总行数  
        // int columns = oFirstSheet.getColumns();//获取工作表中的总列数  
        // for (int i = 1; i < rows; i++) {
        //         data2.put(data);
        //         data = null;
        //     for (int j = 0; j < columns; j++) {  
        //         Cell oCell= oFirstSheet.getCell(j,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
        //         data.put(Columns[j%columns], oCell.toString);
        //         JSONObject response = new Request().send("import_members", data2, false);
        //         if (response.getInt("status") == 0 && response.getString("message").equals("server hello")) {
        //             IO.println("服务器连接成功");
        //         } else {
        //             IO.println(response.getString("message"));
        //             System.exit(1);
        //         }
        //     }
        // }
    }
}


class ExportMembers extends Command {
    public String toString() {
        return "导出会员信息";
    }

    public String identifier() {
        return "export_members";
    }

    public void exec(JSONObject data) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("members");
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 添加表头内容
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("会员ID");
        
        headCell = hssfRow.createCell(1);
        headCell.setCellValue("手机号码");

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("余额");
  
        headCell = hssfRow.createCell(3);
        headCell.setCellValue("性别");

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("生日");

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("路线");
        for (int i = 0; i < list.length; i++) {
              hssfRow = sheet.createRow((int) i + 1);
              // 创建单元格，并设置值
              HSSFCell cell = hssfRow.createCell(0);
              cell.setCellValue(data[i].getString("ID");
              cell = hssfRow.createCell(1);
              cell.setCellValue(data[i].getString("Phone"));
              cell = hssfRow.createCell(2);
              cell.setCellValue(data[i].getString("Rest"));
              cell = hssfRow.createCell(3);
              cell.setCellValue(data[i].getString("Gender");
              cell = hssfRow.createCell(4);
              cell.setCellValue(data[i].getString("Birthday");
              cell = hssfRow.createCell(5);
              cell.setCellValue(data[i].getString("Route");
        
        }
        try {
          OutputStream outputStream = new FileOutputStream("D:/temp/member.xls");
          workbook.write(outputStream);
          outputStream.close();
        } catch (Exception e) {
          e.printStackTrace();
    }
    }
}

class ExportRoutes extends Command {
    public String toString() {
        return "导出线路信息";
    }

    public String identifier() {
        return "export_routes";
    }

    public void exec(JSONObject data) {
       // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("routes");
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 添加表头内容
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("路线");
        for (int i = 0; i < list.length; i++) {
              hssfRow = sheet.createRow((int) i + 1);
              // 创建单元格，并设置值
              HSSFCell cell = hssfRow.createCell(0);
              cell.setCellValue(data[i].getString("Route"));        
        }
        try {
          OutputStream outputStream = new FileOutputStream("D:/temp/route.xls");
          workbook.write(outputStream);
          outputStream.close();
        } catch (Exception e) {
          e.printStackTrace();
    }
    }
}



class ExportSummary extends Command {
    public String toString() {
        return "生成综述";
    }

    public String identifier() {
        return "export_summary";
    }

    public void exec(JSONObject data) {
        
        XWPFDocument document= new XWPFDocument(); 
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(
        new File("d:\\temp\\createparagraph.docx"));
        
        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run=paragraph.createRun();
        run.setText(data.get);
        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
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
        JSONObject data = new JSONObject();
        data.put("days", IO.getInt("快进多少天", 0, 1000));
        JSONObject response = new Request().send(identifier(), data, false);
        if (response.getInt("status") == 0) {
            IO.println("快进成功");
        } else {
            IO.println(response.getString("message"));
        }
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
