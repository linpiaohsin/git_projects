package cn.maiba;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Dao;

public class MyDataBase {
	static Map<String, Map> dataBase = Collections.synchronizedMap(new HashMap<String, Map>());
	static Dao dao=new Dao();
	/*
	 * 函数功能： 往指定数据库表中插入一项
	 * 参数：
	 * 		tableName,指定数据库表的名称
	 * 		tableItem,要插入的项
	 * 返回值：
	 * 		插入成功（true）或失败（false）
	 */
	@SuppressWarnings("unchecked")
	public static boolean save(String tableName, User user){
		
		Boolean result=dao.save(user);
		return result;
		
		
	}
	

	
	@SuppressWarnings("unchecked")
	public static Article showAnArtcle(int id){
		
		return dao.showAnArtcle(id);
		
		
	}
	@SuppressWarnings("unchecked")
	public static String getAuthor(Integer id) {
	 
		return dao.getAuthor(id);
	}
	@SuppressWarnings("unchecked")
	public static boolean writeArticle(Article article) {
		return dao.writeArticle(article);
	}
	public static boolean writeRemark(Remark remark){
		
//		return dao.writeRemark(remark);
		return dao.writeRemark(remark);
		
		
	}
	/*
	 * 函数功能： 读取指定数据库表中ID值为id的项值
	 * 参数：
	 * 		tableName,指定数据库表的名称
	 * 		id,要读取的项的id
	 * 返回值：
	 * 		指定数据库表中ID值为id的项值
	 */
	@SuppressWarnings("unchecked")
	public static User load(String tableName, int id){

		
		User user=(User) dao.load(User.class, id);
//		System.out.println(user);
		return user;
	}
	
	/*
	 * 函数功能： 修改指定数据库表中的某一项
	 * 参数：
	 * 		tableName,指定数据库表的名称
	 * 		tableItem,要修改的项，已经修改的内容
	 * 返回值：
	 * 		修改成功（true）或失败（false）
	 */
	@SuppressWarnings("unchecked")
	public static boolean update(User user,int id){
		
	/*	System.out.println(user);*/
		return dao.updateById(user,id);
	}
	
	/*
	 * 函数功能： 删除指定数据库表中ID值为id的项
	 * 参数：
	 * 		tableName,指定数据库表的名称
	 * 		id,要删除的项的id
	 * 返回值：
	 * 		删除成功（true）或失败（false）
	 */
	@SuppressWarnings("unchecked")
	public static boolean delete(String tableName, Integer id){
		
		return dao.deleteById(User.class, id);
	}
	
	/*
	 * 函数功能： 读出指定数据库表所有项
	 * 参数：
	 * 		tableName,指定数据库表的名称
	 * 返回值：
	 * 		整个数据库的值的列表
	 */
	@SuppressWarnings("unchecked")
	public static List list(String tableName){
		
		return dao.list(User.class);
	}
	@SuppressWarnings("unchecked")
	public static List listArticle(){
		
		return dao.listArticle(User.class);
	}
	@SuppressWarnings("unchecked")
	public static List listmyArticle(int userId){
		
		return dao.listmyArticle(userId);
	}
	/*
	 * 函数功能： 读出指定数据库表指定列为特定值的所有数据
	 * 参数：
	 * 		tableName,指定数据库表的名称
	 * 		tableColName,指定列的名字
	 * 		tableColValue,指定列的值
	 * 返回值：
	 * 		指定数据库表中符合条件的所有项
	 * 用法：
	 * 		List userList = MyDataBase.select(
	 * 							User.TABLE_NAME,"account","weijianlee");
	 * 		返回所有账号(account)为"weijianlee"的用户信息(User类)。
	 */
	@SuppressWarnings("unchecked")
	public static List select(String tableName, String tableColName, Object tableColValue, String operator)throws Exception{
		
		
		List  list = dao.select(User.class);
		return list;
	}
	
	/*
	 * 函数功能： 读出指定数据库表指定列为特定值的数据，如果该数据存在，则返回第一行数据值；如果没有数据则返回null
	 * 参数：
	 * 		tableName,指定数据库表的名称
	 * 		tableColName,指定列的名字
	 * 		tableColValue,指定列的值
	 * 返回值：
	 * 		指定数据库表中符合条件数据值。如果该数据存在，则返回第一行数据值；如果没有数据则返回null
	 * 用法：
	 * 		User user = MyDataBase.uniqueValue(
	 * 							User.TABLE_NAME,"account","weijianlee");
	 * 		如果在User数据库表中，(account)为"weijianlee"用户信息存，则返回该行数据第一行(User类)，否则返回null
	 */
	@SuppressWarnings("unchecked")
	public static Object uniqueValue(String tableName, String tableColName, Object tableColValue, String operator)throws Exception{
		List list = MyDataBase.select(tableName, tableColName, tableColValue, operator);
		//如果要读取的数据存在，返回该项值
		if( list!=null && list.size()!=0){
			return list.get(0);
		}else{//如果没有数据，则返回null
			return null;
		}
	}

	public static ResultSet executeQuery(String sql) {
		// TODO Auto-generated method stub
		
		ResultSet resultSet=dao.executeQuery(sql);
		return resultSet;
	}
}
