package FM_Pegatour_Client.sys;

import java.util.ArrayList;
import java.util.Iterator;

public class LoadClient {

    // 成员方法
    public void init() {
        int command;
        CommandsList commandsList = CommandsList.getInstance(); // 获取CommandsList唯一实例

        commandsList.addCommand(new Register()) // 添加指令
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
            .addCommand(new AdvanceDays())
            .addCommand(new Exit());

        while (true) { // 程序主循环
            commandsList.repr();
            command = IO.getInt("选择", 0, commandsList.size()) - 1; // 获取用户输入，同时减一以匹配数组下标

            commandsList.executeCommand(command); // 执行对应的指令
        }
    }

    static {
        OtherCommands.getInstance().hello();
    }
}

class CommandsList { // 存储指令的列表，使用饿汉单态模式设计
    
    // 静态变量
    private static CommandsList list = new CommandsList(); // 构造一个私有对象
    private ArrayList<Command> commands; // 指令列表

    // 静态方法
    public static CommandsList getInstance() { // 可通过此方法获取唯一实例
        return list;
    }

    // 成员方法
    private CommandsList() {
        this.commands = new ArrayList<>();
    }

    public void repr() {
        int count = 0; // 迭代计数器
        Iterator<Command> cmd = commands.iterator();

        IO.printSep(IO.SEP_MENU); // 打印分隔符

        IO.println("飞马历：" + OtherCommands.getInstance().time());

        for (; cmd.hasNext(); ) {
            IO.println(String // 格式化字符串
                .format("\t%-6d%s", ++count, cmd.next().toString())
                .replace(" ", "-")
            );
        }

        IO.printSep(IO.SEP_MENU);
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
