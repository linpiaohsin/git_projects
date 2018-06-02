/**
 * �����ݿ�ļ��裺1.���ݿ���������pojo�ı�������ͬ��2.ÿ�����������id��������
 * ��pojo(��User��)�ļ��裺 1.����һstatic����TABLE_NAMEָ����pojo��Ӧ�ı�����2.����һ��������id������������Ӧ��
 */
package db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cn.maiba.Article;
import cn.maiba.LogMes;
import cn.maiba.Remark;
import cn.maiba.User;

/**
 * jdbc�������ݿ���
 * 
 * @author weijianlee
 */
public class Dao {
	protected Connection connMagager = null; // ���ݿ�����

	/**
	 * ���캯����������ݿ�����
	 */
	public Dao() {
		try {
		
			//��ͨ������ȡ���ݿ�����
		/*	Class.forName("com.mysql.jdbc.Driver"); // ������������
			connMagager = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/maiba?useUnicode=true&characterEncoding=gbk", "root", "1314");*/ // ����mysql���ݿ�����
		
			//���ݿ����ӳط�����ȡ���ݿ�
			Context intitCtx = new InitialContext();
			Context envCtx = (Context)intitCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/maiba");
			connMagager = ds.getConnection();
		} catch (Exception e) {
			System.out.println("���ݿ�����ʧ�ܣ���ȷ��jar���Ѿ����룬���ݿ�url���˺������Ƿ���ȷ");
			e.printStackTrace();
		}
	}
	public boolean Login(String userName,Timestamp timestamp) {
		PreparedStatement preparedStatement = null;
		
//		System.out.println(user.getAccount()+" "+user.getEmail());
		
		try {
			String strSQL1 = "insert into t_log(userName,message,time) "
					+ "values(?,?,?)";
			preparedStatement = connMagager.prepareStatement(strSQL1);
			preparedStatement.setObject(1, userName);
			preparedStatement.setObject(2, "��¼");
			preparedStatement.setObject(3, timestamp);
		
			
			Boolean result =preparedStatement.execute();
			
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("�������ݿ����");
			return false;
		}finally {
			
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
			
	}
	public boolean Logout(String userName,Timestamp timestamp) {
		PreparedStatement preparedStatement = null;
		
//		System.out.println(user.getAccount()+" "+user.getEmail());
		
		try {
			String strSQL1 = "insert into t_log(userName,message,time) "
					+ "values(?,?,?)";
			preparedStatement = connMagager.prepareStatement(strSQL1);
			preparedStatement.setObject(1, userName);
			preparedStatement.setObject(2, "ע��");
			preparedStatement.setObject(3, timestamp);
		
			
			Boolean result =preparedStatement.execute();
			
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("�������ݿ����");
			return false;
		}finally {
			
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
			
	}
	public boolean writeArticle(Article article) {
		PreparedStatement preparedStatement = null;
		
//		System.out.println(user.getAccount()+" "+user.getEmail());
		
		try {
			String strSQL1 = "insert into t_article(user_id,title,content,time,hit_num,remark_num) "
					+ "values(?,?,?,?,?,?)";
			preparedStatement = connMagager.prepareStatement(strSQL1);
			preparedStatement.setObject(1, article.getUserId());
			preparedStatement.setObject(2, article.getTitle());
			preparedStatement.setObject(3, article.getContent());
			preparedStatement.setObject(4, article.getCreateTime());
			preparedStatement.setObject(5, article.getHitNum());
			preparedStatement.setObject(6, article.getRemarkNum());
			
			Boolean result =preparedStatement.execute();
			
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("�������ݿ����");
			return false;
		}finally {
			
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
			
	}
	public  ResultSet executeQuery(String sql) {
		ResultSet rs=null;
		Statement statement=null;
		try {
			statement = connMagager.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return rs;
	}
		
	

	/**
	 * finalize���ͷ����ݿ�����
	 */
	protected void finalize() throws Throwable {
		if (connMagager != null && !connMagager.isClosed())// �ͷ����ݿ�����
			connMagager.close();
	}

	/**
	 * �г���Ӧ�����������
	 * 	@param item��ָ��pojo��Ҳ����ָ�����ݿ����
	 *  @return�����е������б�
	 */
	public List list(Class item) {
		List list = null;
		try {
			Statement pst = connMagager.createStatement();
			String strSQL = "select * from "
					+ item.getField("TABLE_NAME").get(item);
			
			ResultSet rs = pst.executeQuery(strSQL);
			list = new ArrayList();

			if (rs != null) { // ��resultsetת����list
				while (rs.next()) {
					
					User user = new User();
//					System.out.println(Integer.valueOf(rs.getString(1)));
					user.setId(Integer.valueOf(rs.getString(1)));
					user.setAccount(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setUserName(rs.getString(4));
					user.setAge(rs.getString(5));
					user.setEmail(rs.getString(6));
					list.add(user);
				}
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}
	public List listLog() {
		List<LogMes> list = null;
		try {
			Statement pst = connMagager.createStatement();
			String strSQL = "select * from t_log";
					
			
			ResultSet rs = pst.executeQuery(strSQL);
			list = new ArrayList();

			if (rs != null) { // ��resultsetת����list
				while (rs.next()) {
					LogMes log=new LogMes();
					log.setId(rs.getInt(1));
					log.setUserName(rs.getString(2));
					log.setMessage(rs.getString(3));
					log.setTimestamp(rs.getTimestamp(4));
					list.add(log);
					
				}
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}
	public List listArticle(Class item) {
		List<Article> list = null;
		try {
			Statement pst = connMagager.createStatement();
			String strSQL = "select * from t_article";
					
			
			ResultSet rs = pst.executeQuery(strSQL);
			list = new ArrayList();

			if (rs != null) { // ��resultsetת����list
				while (rs.next()) {
					Article article=new Article();
					article.setId(rs.getInt(1));
					article.setUserId(rs.getInt(2));
					article.setTitle(rs.getString(3));
					article.setContent(rs.getString(4));
					article.setCreateTime(Timestamp.valueOf(rs.getString(5)));
					article.setHitNum(rs.getInt(6));
					article.setRemarkNum(rs.getInt(7));
					list.add(article);
				}
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}
	public List listmyArticle(int id) {
		List<Article> list = null;
		try {
			Statement pst = connMagager.createStatement();
			String strSQL = "select * from t_article where user_id="+id;
					
			
			ResultSet rs = pst.executeQuery(strSQL);
			list = new ArrayList();

			if (rs != null) { // ��resultsetת����list
				while (rs.next()) {
					Article article=new Article();
					article.setId(rs.getInt(1));
					article.setUserId(rs.getInt(2));
					article.setTitle(rs.getString(3));
					article.setContent(rs.getString(4));
					article.setCreateTime(Timestamp.valueOf(rs.getString(5)));
					article.setHitNum(rs.getInt(6));
					article.setRemarkNum(rs.getInt(7));
					list.add(article);
				}
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}
	public List select(Class item) throws Exception {
		List list=null;
		String sql="select account from t_user";
		Statement statement;
		try {
			statement = connMagager.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			list=new ArrayList<>();
			if(resultSet!=null) {
				while(resultSet.next()) {
					list.add(creatObject(resultSet, item));
				}
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	/**
	 * �г���������query������
	 * @param item��ָ��pojo��Ҳ����ָ�����ݿ����
	 * @param query:ָ��������Ҳ���൱��select xx where ���������
	 * @return�����������������б�
	 */
	public List listbyQuery(Class item, String query) {
		List list = null;
		try {
			String strSQL = "select * from "
				+ item.getField("TABLE_NAME").get(item);
			strSQL += " where " + query;
			System.out.println(strSQL);
			Statement pst = connMagager.createStatement();
			ResultSet rs = pst.executeQuery(strSQL);
			list = new ArrayList();

			if (rs != null) {
				while (rs.next()) {
					list.add(creatObject(rs, item));
				}
			}
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}

	/**
	 * ͨ��id���ض�Ӧ������
	 * @param item��ָ��pojo��Ҳ����ָ�����ݿ����
	 * @param id:���Ӧ��id
	 * @return��id��Ӧ������
	 */
	public Article showAnArtcle(int id) {
		// TODO Auto-generated method stub
		Article article=new Article();
		try {
			String strSQL = "select * from t_article" + " where id="+id;
			PreparedStatement pst = null;
			
			pst = connMagager.prepareStatement(strSQL);
		
				
			ResultSet rs = pst.executeQuery();
//			System.out.println(strSQL);
//			System.out.println(id);
			User user = new User();
			if (rs != null && rs.next()) {// ����id������ֻ�����һ�����ݣ�����ֻ���ص�һ����¼
				article.setUserId(rs.getInt(2));
				article.setTitle(rs.getString(3));
				article.setContent(rs.getString(4));
				article.setCreateTime(Timestamp.valueOf(rs.getString(5)));
				article.setHitNum(Integer.valueOf(rs.getString(6)));
				article.setRemarkNum(Integer.valueOf(rs.getString(7)));
//				System.out.println(article.getTitle());
			
			} else {
				System.out.println("user���ղ������ݣ�");
			}

			rs.close();
			pst.close();
			return article;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("load�������쳣");
			return null;
		} finally {
		}
		
	}
	public User load(Class item, int id) {
		try {
			String strSQL = "select * from "
					+ item.getField("TABLE_NAME").get(item) + " where id="+id;
			PreparedStatement pst = null;
			
			pst = connMagager.prepareStatement(strSQL);
		
				
			ResultSet rs = pst.executeQuery();
//			System.out.println(strSQL);
//			System.out.println(id);
			User user = new User();
			if (rs != null && rs.next()) {// ����id������ֻ�����һ�����ݣ�����ֻ���ص�һ����¼
				user.setId(Integer.valueOf(rs.getString(1)));
				user.setAccount(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setAge(rs.getString(5));
				user.setEmail(rs.getString(6));
//				System.out.println(user);
			
			} else {
				System.out.println("user���ղ������ݣ�");
			}

			rs.close();
			pst.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("load�������쳣");
			return null;
		} finally {
		}
	}
	public String getAuthor(Integer id) {
		String account=null;
		try {
			String strSQL = "select account from t_user" + " where id="+id;
			PreparedStatement pst = null;
			
			pst = connMagager.prepareStatement(strSQL);
		
				
			ResultSet rs = pst.executeQuery();
//			System.out.println(strSQL);
//			System.out.println(id);
			if (rs != null && rs.next()) {// ����id������ֻ�����һ�����ݣ�����ֻ���ص�һ����¼
				account=rs.getString(1);
				
			/*	System.out.println(account);*/
//				System.out.println(user);
			
			} else {
				System.out.println("user���ղ������ݣ�");
			}

			rs.close();
			pst.close();
			return account;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("load�������쳣");
			return null;
		} finally {
		}
	}
	/**
	 * ͨ��idɾ�����Ӧ����
	 * @param item��ָ��pojo��Ҳ����ָ�����ݿ����
	 * @param id:���Ӧ��id
	 * @return��ɾ���ɹ���ʧ��
	 */
	public boolean deleteById(Class item, Object id) {
		PreparedStatement pst = null;
		try {
			String strSQL = "delete FROM "
					+ "t_user" + " where id=?";//����delet���
			

			pst = connMagager.prepareStatement(strSQL);
			pst.setObject(1, id);//���ò���id

			pst.executeUpdate();//ִ��
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��������
	 * @param obj��Ҫ��������ݶ�Ӧ��pojo����
	 * @return������ɹ���ʧ��
	 */
	public boolean save(Object obj) {
		try {
			String strSQL = "insert into " + obj.getClass().getField("TABLE_NAME").get(obj.getClass()) + "(";
			
			Field[] fields = obj.getClass().getDeclaredFields(); // ���������ݳ�Ա
			Method[] methods = obj.getClass().getDeclaredMethods(); // �����ĺ�����Ա
			Map colMap = new HashMap();
			int nCounter = 0 ;
			String strCounter="";
			for (int i = 0; i < fields.length; i++) { // �������ÿ�����ݳ�Ա
				if ("TABLE_NAME".equals(fields[i].getName())||"id".equals(fields[i].getName()))
					continue; // �������ݳ�Ա�����Բ�����
				
				//���ݱ�������������getter����
				String methodName = "get"
						+ fields[i].getName().substring(0, 1).toUpperCase()
						+ fields[i].getName().substring(1);

				Method method = null;

				//����getter���������ҵ���Ӧ�ĺ�������
				for (int j = 0; j < methods.length; j++) {
					if (methods[j].getName().equals(methodName)) {
						method = methods[j];
						break;
					}
				}

				//ִ�к������õ�ֵ
				Object col = method.invoke(obj);
				if(col != null){
					nCounter ++;
					colMap.put(nCounter, col);
					if(nCounter >1){
						strSQL += ",";
						strCounter +=",";	
					}
					strSQL += fields[i].getName();
					strCounter +="?";
				}
			}
			
			strSQL += ") value("+ strCounter + ")";
		
			PreparedStatement pst = connMagager.prepareStatement(strSQL);
			for(int i=1; i<=colMap.size(); i++){
				pst.setObject(i, colMap.get(i));
			}

			pst.executeUpdate();
			pst.close();
			return true;
		} catch (Exception e) {
			
			return false;
		} finally {
		}
	}
	
	/**
	 * ��������
	 * @param obj��Ҫ���µĶ�������id����ǿգ����ݸ�idȥ�������ݿ�
	 * @return�������ɹ���ʧ��
	 */
	public boolean updateById(User user,int id) {
		PreparedStatement preparedStatement = null;
	
//		System.out.println(user.getAccount()+" "+user.getEmail());
		
		try {
			String strSQL1 = "UPDATE t_user SET account='"+user.getAccount()+"',password='"+user.getPassword()+"',"
			+ "userName='"+ user.getUserName()+"',"
			+ "age="+Integer.valueOf(user.getAge())+","
			+ "email='"+user.getEmail()+"'"
			+ "WHERE id="+id;
			preparedStatement = connMagager.prepareStatement(strSQL1);
//			System.out.println(strSQL1);
			Boolean result =preparedStatement.execute();
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("�������ݿ����");
			return false;
		}finally {
			
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
			
		
	}
	public boolean updateRemark(int id,int num) {
		PreparedStatement preparedStatement = null;
		try {
			String strSQL1 = "UPDATE t_article SET remark_num="+num+" where id ="+id;
			preparedStatement = connMagager.prepareStatement(strSQL1);
//			System.out.println(strSQL1);
			Boolean result =preparedStatement.execute();
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("�������ݿ����");
			return false;
		}finally {
			
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
	}
	public boolean updateHit(int id) {
		PreparedStatement preparedStatement = null;
		
//		System.out.println(user.getAccount()+" "+user.getEmail());
		
		try {
			String strSQL1 = "UPDATE t_article SET hit_num=hit_num+1 where id ="+id;
			preparedStatement = connMagager.prepareStatement(strSQL1);
//			System.out.println(strSQL1);
			Boolean result =preparedStatement.execute();
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("�������ݿ����");
			return false;
		}finally {
			
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
	}
	
	/**
	 * ���÷���ͨ�����ݼ��������� (Ҫ��pojo���set�������Ʊ�����set+����������������������ĸ�����д)
	 * 
	 * @param rs : ���ݿ��ж�ȡ�������ݼ�
	 * @param obj ��Ҫ�������������
	 * @return �����ݼ�ת���� �������
	 * @throws Exception
	 */
	private Object creatObject(ResultSet rs, Class objClass) throws Exception {
		Object object = objClass.newInstance(); // ͨ��������ʵ��������Ķ���
		Field[] fields = objClass.getDeclaredFields(); // ���������ݳ�Ա
		Method[] mds = objClass.getDeclaredMethods(); // �����ĺ�����Ա

		for (int i = 0; i < fields.length; i++) { // �������ÿ�����ݳ�Ա��������setter������ֵ
			if ("TABLE_NAME".equals(fields[i].getName())||"id".equals(fields[i].getName()))
				continue; // �������ݳ�Ա�����Բ�����

			//���ݱ���������setter����
			String methodName = "set"
					+ fields[i].getName().substring(0, 1).toUpperCase()
					+ fields[i].getName().substring(1);

			Method method = null;

			//setter���������ҵ���Ӧ�ĺ�������
			for (int j = 0; j < mds.length; j++) {
				if (mds[j].getName().equals(methodName)) {
					method = mds[j];
					break;
				}
			}

			//ִ�к���
			method.invoke(object, new Object[] { rs.getObject(fields[i].getName()) });
		}
		return object;
	}

	public boolean writeRemark(Remark remark) {
		// TODO Auto-generated method stub
		
			PreparedStatement preparedStatement = null;
			
//			System.out.println(user.getAccount()+" "+user.getEmail());
			
			try {
				String strSQL = "insert into t_remark(article_id,content,time,user_id) "
						+ "values("+remark.getArticleId()+",'"+remark.getRemark()+"','"+remark.getRemarkTime()+"',"+
						remark.getUserId()+")";
		
				
				preparedStatement = connMagager.prepareStatement(strSQL);
				/*preparedStatement.setObject(1, remark.getArticleId());
				preparedStatement.setObject(2, remark.getRemark());
				preparedStatement.setObject(3, remark.getRemarkTime());
				preparedStatement.setObject(4, remark.getUserId());*/
				
		

				boolean result =preparedStatement.execute();
				return true;
			}
			catch (Exception e) { 
				e.printStackTrace();
				System.out.println("�������ݿ����zzz��");
				return false;
			}finally {
				
						try {
							preparedStatement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				}
				
		
	}
	
	


}