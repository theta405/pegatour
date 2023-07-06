import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTest {
    public static void main(String[] args) {
        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate();

        date1.getLocalDate();
        System.out.println(date1.toString());
        date2.parseDate("2022-12-22");
        System.out.println(date2.toString());
        System.out.println(date1.compareTo(date1));
        date1.addDays(20);
        date2.addDays(200);
        System.out.println(date2.toString());
    }
}

class MyDate {

    private LocalDate date;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 使用给定的日期字符串创建一个新的MyDate对象
    public void parseDate(String date) {
        this.date = LocalDate.parse(date, formatter);
    }

    public void getLocalDate() {
        this.date = LocalDate.now();
    }

    // 比较这个MyDate对象和另一个MyDate对象的日期
    public int compareTo(MyDate other) {
        return this.date.compareTo(other.date);
    }

    // 将这个MyDate对象的日期加上给定的天数，并输出年份变动
    public void addDays(int days) {
        int oldYear = this.date.getYear();
        this.date = this.date.plusDays(days);
        int newYear = this.date.getYear();
        int years = newYear - oldYear;
        if (years != 0) {
            System.out.println("Year changed by " + years + " years");
        }
        // 将需要的数据保存到oldyear文件，然后其它年用循环全部批量处理
    }


    // 返回这个MyDate对象的日期的字符串表示形式
    @Override
    public String toString() {
        return this.date.format(formatter);
    }
}
