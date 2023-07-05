package FM_Pegatour_Server.sys;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LoadServer {

    // 成员方法
    public void init() {
        CommandsList commandsList = CommandsList.getInstance(); // 获取CommandsList唯一实例

        commandsList.addCommand(new Hello()) // 添加指令
            .addCommand(new Time())
            .addCommand(new Register())
            .addCommand(new Deactivate())
            .addCommand(new QueryMembers())
            .addCommand(new Modify())
            .addCommand(new AddRoute())
            .addCommand(new QueryRoutes())
            .addCommand(new BookRoute())
            .addCommand(new CommentRoute())
            .addCommand(new ImportMembers())
            .addCommand(new ImportRoutes())
            .addCommand(new ExportMembers())
            .addCommand(new ExportRoutes())
            .addCommand(new ExportSummary())
            .addCommand(new AdvanceDays());
        
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("服务器已启动");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine = in.readLine();
                JSONObject jsonObject = new JSONObject(inputLine);

                String identifier = jsonObject.getString("identifier");
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject response = new JSONObject();

                CommandsList.getInstance().getCommand(identifier).exec(data, response);
                
                // response.put("message", data.toString());
                
                out.println(response.toString());
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class CommandsList { // 存储指令的列表，使用饿汉单态模式设计
    
    // 静态变量
    private static CommandsList list = new CommandsList(); // 构造一个私有对象
    private Map<String, Command> commands; // 存储指令的散列表

    // 静态方法
    public static CommandsList getInstance() { // 可通过此方法获取唯一实例
        return list;
    }

    // 成员方法
    private CommandsList() {
        this.commands = new HashMap<String, Command>();
    }

    public CommandsList addCommand(Command command) { // 添加指令
        this.commands.put(command.identifier(), command);
        return this; // 返回自身，方便添加多条指令
    }

    public Command getCommand(String identifier) { // 执行指令
        return this.commands.get(identifier); // 从列表中获取相应指令
    }
}
