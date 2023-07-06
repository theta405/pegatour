package FM_Pegatour_Server.sys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date {
    private LocalDate date;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 使用给定的日期字符串创建一个新的Date对象
    public void parseDate(String date) {
        this.date = LocalDate.parse(date, formatter);
    }

    public void getFMDate() throws Exception {
        Database db = new Database();
        this.date = LocalDate.parse(db.getTime(), formatter);
    }

    public void setFMDate() throws Exception {
        Database db = new Database();
        db.modifyTime(toString());
    }

    // 比较这个Date对象和另一个Date对象的日期
    public int compareTo(Date other) {
        return this.date.compareTo(other.date);
    }

    // 将这个Date对象的日期加上给定的天数，并输出年份变动
    public void addDays(int days) {
        int oldYear = this.date.getYear();
        this.date = this.date.plusDays(days);
        int newYear = this.date.getYear();
        int years = newYear - oldYear;
        if (years != 0) {
            System.out.println("又过去了 " + years + " 年");
        }
        // 将需要的数据保存到oldyear文件，然后其它年用循环全部批量处理
    }

    public long years(String compDate) {
        Date oldDate = new Date();
        oldDate.parseDate(compDate);
        return ChronoUnit.YEARS.between(oldDate.date, this.date);
    }

    // 返回这个Date对象的日期的字符串表示形式
    @Override
    public String toString() {
        return this.date.format(formatter);
    }
}
