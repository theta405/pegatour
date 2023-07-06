package FM_Pegatour_Client.sys;

/*
 * Member主要用于用户信息的封装
 */
import java.util.regex.*;

public class Route {
	private String ID;
	private String Date;
	private String Destination;
	
	public Route(String date, String destination) {
		this.Date=date;
		this.Destination=destination;
	}

    public Route() {
        set_Date();
        set_Destination();
    }
	
	public void set_Destination() {
        this.Destination=IO.getStrMatch("输入目的地", "[\\u4E00-\\u9FFF]{1,10}");
    }

	public void set_Date() {
        this.Date=IO.getStrMatch("输入开始日期", "\\d{4}-\\d{2}-\\d{2}");
	}
	
	public String get_ID() {
		return this.ID;
	}
	
	public String get_Date() {
		return this.Date;
	}
	
	public String get_Destination() {
		return this.Destination;
	}
}