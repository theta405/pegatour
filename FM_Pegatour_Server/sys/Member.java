package FM_Pegatour_Server.sys;

/*
 * Member主要用于用户信息的封装
 */
import java.util.regex.*;
public class Member {
	private String ID;
	private String Name;
	private String Passwd;
	private String ID2;
	private String Phone;
	private String Gender;
	private String Birthday;
	private String Record="0";
	
	public Member(String id, String name, String passwd, String id2, String phone, String gender, String birthday) {
		this.Birthday=birthday;
		this.Gender=gender;
		this.Passwd=passwd;
		this.ID2=id2;
		this.Name=name;
		this.Phone=phone;
	}
	
	public void set_ID(String new_String) {
		String  pattern = "[0-9]{10,10}";
		if(Pattern.matches(pattern, new_String)) {
			this.ID = new_String;
		}else {
			System.out.println("Failed");
		}
	}
	
	
	public void set_Name(String new_String) {
		String  pattern = "[\\u4E00-\\u9FFF]{1,10}";
		if(Pattern.matches(pattern, new_String)) {
			this.Name = new_String;
		}else {
			System.out.println("Failed");
		}
	}
	
	public void set_Passwd(String new_String) {
		String  pattern = "[0-9]{4,20}";
		if(Pattern.matches(pattern, new_String)) {
			this.Passwd = new_String;
		}else {
			System.out.println("Failed");
		}
	}
	
	public void ID2(String new_String) {
		String  pattern = "[0-9]{12,12}";
		if(Pattern.matches(pattern, new_String)) {
			this.ID2 = new_String;
		}else {
			System.out.println("Failed");
		}
	}
	
	public void set_Phone(String new_String) {
		String  pattern = "[0-9]{11,11}";
		if(Pattern.matches(pattern, new_String)) {
			this.Phone = new_String;
		}else {
			System.out.println("Failed");
		}
	}
	
	public void set_Gender(String new_String) {
		String  pattern = "[M,F]{1,1}";
		if(Pattern.matches(pattern, new_String)) {
			this.Gender = new_String;
		}else {
			System.out.println("Failed");
		}
	}
	
	public void set_Birthday(String new_String) {
		String  pattern = "[0-9]{4,20}";
		if(Pattern.matches(pattern, new_String)) {
			this.Passwd = new_String;
		}else {
			System.out.println("Failed");
		}
	}
	
	public void set_Record(String new_String) {
		String  pattern = "[0-9]{4,20}";
		if(Pattern.matches(pattern, new_String)) {
			this.Record = new_String;
		}else {
			System.out.println("Failed");
		}
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
	
	public String get_Record() {
		return this.Record;
	}
}

