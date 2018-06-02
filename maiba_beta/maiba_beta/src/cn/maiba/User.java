package cn.maiba;


public class User  {
	public static final String TABLE_NAME="t_user"; //Javabean对应数据库中表名，必须有否则出错。
	
	
	int id;
	String userName="tmp";
	String account="tmp";
	String password="tmp";
	String age="11";
	String email="tmp";
	


	public User() {

	}

	public User(String account, String password) {
		
		this.account = account;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
