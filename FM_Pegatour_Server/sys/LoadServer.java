package FM_Pegatour_Server.sys;

import java.util.HashMap;
import java.util.Map;

public class LoadServer {

    // 静态变量
    public static Date date = new Date();

    // 成员方法
    public void init() {
        CommandsList commandsList = CommandsList.getInstance(); // 获取CommandsList唯一实例

        commandsList.addCommand(new Hello()) // 添加指令
            .addCommand(new Time())
            .addCommand(new Register())
            .addCommand(new Deactivate())
            .addCommand(new QueryMembers())
            .addCommand(new Modify())
            .addCommand(new Deposit())
            .addCommand(new AddRoute())
            .addCommand(new QueryRoutes())
            .addCommand(new BookRoute())
            .addCommand(new ImportMembers())
            .addCommand(new ImportRoutes())
            .addCommand(new ExportMembers())
            .addCommand(new ExportRoutes())
            .addCommand(new ExportSummary())
            .addCommand(new AdvanceDays());
        
        Request.getInstance().handleRequests();
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
