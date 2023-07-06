package FM_Pegatour_Client.sys;

import org.json.JSONObject;
import org.json.JSONArray;

/*
 * Member主要用于用户信息的封装
 */

public class Member {
	private String ID;
	private String Name;
	private String Passwd;
	private String ID2;
	private String Phone;
	private String Gender;
	private String Birthday;
	private JSONArray BookRecord;
	private JSONObject CommentRecord;
	
	public Member(String id, String name, String passwd, String id2, String phone, String gender, String birthday, String bookrecord, String commentrecord) {
		this.Name=name;
		this.Passwd=passwd;
		this.ID2=id2;
		this.Phone=phone;
		this.Gender=gender;
		this.Birthday=birthday;
        this.BookRecord=new JSONArray(bookrecord);
        this.CommentRecord=new JSONObject(commentrecord);
	}

    public Member() {
        set_Name();
        set_Passwd();
        set_ID2();
        set_Phone();
        set_Gender();
        set_Birthday();
        this.BookRecord=new JSONArray();
        this.CommentRecord=new JSONObject();
    }
	
	public void set_Name() {
        this.Name=IO.getStrMatch("输入姓名", "[\\u4E00-\\u9FFF]{1,10}");
	}
	
	public void set_Passwd() {
        this.Passwd=IO.getStrMatch("输入密码", "[0-9]{4,20}");
	}
    
    public void set_ID2() {
        this.ID2=IO.getStrMatch("输入身份ID", "[0-9]{12}");
    }
	
	public void set_Phone() {
        this.Phone=IO.getStrMatch("输入手机号", "[0-9]{11}");
	}
	
	public void set_Gender() {
        this.Gender=IO.getStrMatch("输入性别", "[M,F]{1}");
	}
	
	public void set_Birthday() {
        this.Birthday=IO.getStrMatch("输入出生日期", "\\d{4}-\\d{2}-\\d{2}");
	}

    public void set_Money() {
        this.Money=IO.getInt("输入金额", 0, 1000000);
    }
	
	public String get_ID() {
		return this.ID;
	}
	
	public String get_Name() {
		return this.Name;
	}
	
	public String get_Passwd() {
		return this.Passwd;
	}
	
	public String get_ID2() {
		return this.ID2;
	}
	
	public String get_Phone() {
		return this.Phone;
	}
	
	public String get_Gender() {
		return this.Gender;
	}
	
	public String get_Birthday() {
		return this.Birthday;
	}
	
	public JSONArray get_BookRecord() {
		return this.BookRecord;
	}
	
	public JSONObject get_CommentRecord() {
		return this.CommentRecord;
	}

    public JSONArray toArray() {
        JSONArray array = new JSONArray();
        array.put(get_Name());
        array.put(get_Passwd());
        array.put(get_ID2());
        array.put(get_Phone());
        array.put(get_Gender());
        array.put(get_Birthday());
        array.put(get_BookRecord());
        array.put(get_CommentRecord());
        return array;
    }
}

