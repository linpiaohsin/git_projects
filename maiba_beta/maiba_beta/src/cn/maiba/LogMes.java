package cn.maiba;

import java.sql.Timestamp;

public class LogMes {
int id;
String userName;
String message;
Timestamp timestamp;
public LogMes() {
	// TODO Auto-generated constructor stub
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Timestamp getTimestamp() {
	return timestamp;
}
public void setTimestamp(Timestamp timestamp) {
	this.timestamp = timestamp;
}

}
