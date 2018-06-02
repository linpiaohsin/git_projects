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
	 * �������ܣ� ��ָ�����ݿ���в���һ��
	 * ������
	 * 		tableName,ָ�����ݿ�������
	 * 		tableItem,Ҫ�������
	 * ����ֵ��
	 * 		����ɹ���true����ʧ�ܣ�false��
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
	 * �������ܣ� ��ȡָ�����ݿ����IDֵΪid����ֵ
	 * ������
	 * 		tableName,ָ�����ݿ�������
	 * 		id,Ҫ��ȡ�����id
	 * ����ֵ��
	 * 		ָ�����ݿ����IDֵΪid����ֵ
	 */
	@SuppressWarnings("unchecked")
	public static User load(String tableName, int id){

		
		User user=(User) dao.load(User.class, id);
//		System.out.println(user);
		return user;
	}
	
	/*
	 * �������ܣ� �޸�ָ�����ݿ���е�ĳһ��
	 * ������
	 * 		tableName,ָ�����ݿ�������
	 * 		tableItem,Ҫ�޸ĵ���Ѿ��޸ĵ�����
	 * ����ֵ��
	 * 		�޸ĳɹ���true����ʧ�ܣ�false��
	 */
	@SuppressWarnings("unchecked")
	public static boolean update(User user,int id){
		
	/*	System.out.println(user);*/
		return dao.updateById(user,id);
	}
	
	/*
	 * �������ܣ� ɾ��ָ�����ݿ����IDֵΪid����
	 * ������
	 * 		tableName,ָ�����ݿ�������
	 * 		id,Ҫɾ�������id
	 * ����ֵ��
	 * 		ɾ���ɹ���true����ʧ�ܣ�false��
	 */
	@SuppressWarnings("unchecked")
	public static boolean delete(String tableName, Integer id){
		
		return dao.deleteById(User.class, id);
	}
	
	/*
	 * �������ܣ� ����ָ�����ݿ��������
	 * ������
	 * 		tableName,ָ�����ݿ�������
	 * ����ֵ��
	 * 		�������ݿ��ֵ���б�
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
	 * �������ܣ� ����ָ�����ݿ��ָ����Ϊ�ض�ֵ����������
	 * ������
	 * 		tableName,ָ�����ݿ�������
	 * 		tableColName,ָ���е�����
	 * 		tableColValue,ָ���е�ֵ
	 * ����ֵ��
	 * 		ָ�����ݿ���з���������������
	 * �÷���
	 * 		List userList = MyDataBase.select(
	 * 							User.TABLE_NAME,"account","weijianlee");
	 * 		���������˺�(account)Ϊ"weijianlee"���û���Ϣ(User��)��
	 */
	@SuppressWarnings("unchecked")
	public static List select(String tableName, String tableColName, Object tableColValue, String operator)throws Exception{
		
		
		List  list = dao.select(User.class);
		return list;
	}
	
	/*
	 * �������ܣ� ����ָ�����ݿ��ָ����Ϊ�ض�ֵ�����ݣ���������ݴ��ڣ��򷵻ص�һ������ֵ�����û�������򷵻�null
	 * ������
	 * 		tableName,ָ�����ݿ�������
	 * 		tableColName,ָ���е�����
	 * 		tableColValue,ָ���е�ֵ
	 * ����ֵ��
	 * 		ָ�����ݿ���з�����������ֵ����������ݴ��ڣ��򷵻ص�һ������ֵ�����û�������򷵻�null
	 * �÷���
	 * 		User user = MyDataBase.uniqueValue(
	 * 							User.TABLE_NAME,"account","weijianlee");
	 * 		�����User���ݿ���У�(account)Ϊ"weijianlee"�û���Ϣ�棬�򷵻ظ������ݵ�һ��(User��)�����򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public static Object uniqueValue(String tableName, String tableColName, Object tableColValue, String operator)throws Exception{
		List list = MyDataBase.select(tableName, tableColName, tableColValue, operator);
		//���Ҫ��ȡ�����ݴ��ڣ����ظ���ֵ
		if( list!=null && list.size()!=0){
			return list.get(0);
		}else{//���û�����ݣ��򷵻�null
			return null;
		}
	}

	public static ResultSet executeQuery(String sql) {
		// TODO Auto-generated method stub
		
		ResultSet resultSet=dao.executeQuery(sql);
		return resultSet;
	}
}
