package FM_Pegatour_Server.sys;

import java.sql.*;

public class DataBase_Handle implements Runnable{
	private Connection conn;
	
	public void Login(String url, String username, String passwd) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection(url, username, passwd);}catch(Exception e) {
			System.out.println("Failed");
		}
	}
	
	public void registe(Member m)throws SQLException {
		String name = m.get_Name();
		String id = m.get_ID();
		String id2 = m.get_ID2();
		String phone = m.get_Phone();
		String gender = m.get_Birthday();
		String birthday = m.get_Birthday();
		String passwd = m.get_Passwd();
		
		String sql = "INSERT INTO Coustomers(user_name, sex, age, birthday, email, mobile,"+
	            "create_user, create_date, update_user, update_date, isdel)"
	                +"values("+"?,?,?,?,?,?,?,CURRENT_DATE(),?,CURRENT_DATE(),?)";//改一下
	        
	   PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
	        
	   ptmt.setString(1, g.getUser_name());
	   ptmt.setInt(2, g.getSex());
	   ptmt.setInt(3, g.getAge());
	   ptmt.setDate(4, new Date(g.getBirthday().getTime()));
	   ptmt.setString(5, g.getEmail());
	   ptmt.setString(6, g.getMobile());
	   ptmt.setString(7, g.getCreate_user());
	   ptmt.setString(8, g.getUpdate_user());
	   ptmt.setInt(9, g.getIsDel());
	        
	   //执行
	   ptmt.execute();
	}
	
	public void delete(Member m)throws SQLException{
		String sql = "delete from imooc_goddess where id=?";
        //预编译SQL，减少sql执行
        PreparedStatement ptmt = conn.prepareStatement(sql);

        //传参
        ptmt.setInt(1, id);

        //执行
        ptmt.execute();
	}
	
	public void findpath(Member m)throws SQLException{
		Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT user_name, age FROM imooc_goddess");

        List<Goddess> gs = new ArrayList<Goddess>();
        Goddess g = null;
        while(rs.next()){
            g = new Goddess();
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));

            gs.add(g);
        }
        return gs;
	}
	
	//该方法同时实现修改信息，预定路线和取消路线
	public void update(Member m ,String field)throws SQLException {
		
	}
}
