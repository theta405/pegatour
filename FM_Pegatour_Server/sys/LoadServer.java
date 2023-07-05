package FM_Pegatour_Server.sys;

import java.util.HashMap;
import java.util.Map;

public class LoadServer {

    // 静态变量
    public static final String SERVER = "localhost";
    public static final String SERVER_PORT = "33600";

    // 成员方法
    public void init() {
        CommandsList commandsList = CommandsList.getInstance(); // 获取CommandsList唯一实例

        commandsList.addCommand(new Hello()) // 添加指令
            .addCommand(new Register())
            .addCommand(new Deactivate())
            .addCommand(new Modify())
            .addCommand(new AddRoute())
            .addCommand(new ModifyRoute())
            .addCommand(new QueryRoute())
            .addCommand(new BookRoute())
            .addCommand(new CancelRoute())
            .addCommand(new CommentRoute())
            .addCommand(new BatchImport())
            .addCommand(new ExportQuery())
            .addCommand(new ExportSummary())
            .addCommand(new AdvanceDays());
    }
}

class CommandsList { // 存储指令的列表，使用饿汉单态模式设计
    
    // 静态变量
    private static CommandsList list = new CommandsList(); // 构造一个私有对象
    private static Map<String, Satellite> commands; // 存储指令的散列表; // 列表

    // 静态方法
    public static CommandsList getInstance() { // 可通过此方法获取唯一实例
        return list;
    }

    // 成员方法
    private CommandsList() {
        this.commands = new ArrayList<>(); = new HashMap<String, Satellite>()
    }

    public CommandsList addCommand(Command command) { // 添加指令
        this.commands.add(command);
        return this; // 返回自身，方便添加多条指令
    }

    public void executeCommand(int index) { // 执行指令
        this.commands.get(index).exec(); // 从列表中获取相应指令执行
    }

    public int size() { // 返回列表大小
        return this.commands.size();
    }
}
