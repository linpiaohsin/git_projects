/**
 * 对数据库的假设：1.数据库表的列名与pojo的变量名相同；2.每个表的主键叫id，自增。
 * 对pojo(如User类)的假设： 1.必有一static变量TABLE_NAME指出本pojo对应的表名；2.必有一个变量叫id，与表的主键对应。
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
 * jdbc连接数据库类
 * 
 * @author weijianlee
 */
public class Dao {
	protected Connection connMagager = null; // 数据库连接

	/**
	 * 构造函数，获得数据库连接
	 */
	public Dao() {
		try {
		
			//普通方法获取数据库连接
		/*	Class.forName("com.mysql.jdbc.Driver"); // 加载驱动程序
			connMagager = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/maiba?useUnicode=true&characterEncoding=gbk", "root", "1314");*/ // 建立mysql数据库连接
		
			//数据库连接池方法获取数据库
			Context intitCtx = new InitialContext();
			Context envCtx = (Context)intitCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/maiba");
			connMagager = ds.getConnection();
		} catch (Exception e) {
			System.out.println("数据库连接失败！请确认jar包已经放入，数据库url和账号密码是否正确");
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
			preparedStatement.setObject(2, "登录");
			preparedStatement.setObject(3, timestamp);
		
			
			Boolean result =preparedStatement.execute();
			
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("更新数据库出错！");
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
			preparedStatement.setObject(2, "注销");
			preparedStatement.setObject(3, timestamp);
		
			
			Boolean result =preparedStatement.execute();
			
			return true;
		}
		catch (Exception e) { 
			e.printStackTrace();
			System.out.println("更新数据库出错！");
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
			System.out.println("更新数据库出错！");
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
	 * finalize，释放数据库连接
	 */
	protected void finalize() throws Throwable {
		if (connMagager != null && !connMagager.isClosed())// 释放数据库连接
			connMagager.close();
	}

	/**
	 * 列出对应表的所有数据
	 * 	@param item：指出pojo，也即是指出数据库表名
	 *  @return：所有的数据列表
	 */
	public List list(Class item) {
		List list = null;
		try {
			Statement pst = connMagager.createStatement();
			String strSQL = "select * from "
					+ item.getField("TABLE_NAME").get(item);
			
			ResultSet rs = pst.executeQuery(strSQL);
			list = new ArrayList();

			if (rs != null) { // 从resultset转化成list
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

			if (rs != null) { // 从resultset转化成list
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

			if (rs != null) { // 从resultset转化成list
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

			if (rs != null) { // 从resultset转化成list
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
	 * 列出符合条件query的数据
	 * @param item：指出pojo，也即是指出数据库表名
	 * @param query:指出条件。也就相当与select xx where 后面的条件
	 * @return：符合条件的数据列表
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
	 * 通过id加载对应的数据
	 * @param item：指出pojo，也即是指出数据库表名
	 * @param id:表对应的id
	 * @return：id对应的数据
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
			if (rs != null && rs.next()) {// 根据id读数据只会读到一条数据，所以只返回第一条记录
				article.setUserId(rs.getInt(2));
				article.setTitle(rs.getString(3));
				article.setContent(rs.getString(4));
				article.setCreateTime(Timestamp.valueOf(rs.getString(5)));
				article.setHitNum(Integer.valueOf(rs.getString(6)));
				article.setRemarkNum(Integer.valueOf(rs.getString(7)));
//				System.out.println(article.getTitle());
			
			} else {
				System.out.println("user接收不到数据！");
			}

			rs.close();
			pst.close();
			return article;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("load处出现异常");
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
			if (rs != null && rs.next()) {// 根据id读数据只会读到一条数据，所以只返回第一条记录
				user.setId(Integer.valueOf(rs.getString(1)));
				user.setAccount(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setAge(rs.getString(5));
				user.setEmail(rs.getString(6));
//				System.out.println(user);
			
			} else {
				System.out.println("user接收不到数据！");
			}

			rs.close();
			pst.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("load处出现异常");
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
			if (rs != null && rs.next()) {// 根据id读数据只会读到一条数据，所以只返回第一条记录
				account=rs.getString(1);
				
			/*	System.out.println(account);*/
//				System.out.println(user);
			
			} else {
				System.out.println("user接收不到数据！");
			}

			rs.close();
			pst.close();
			return account;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("load处出现异常");
			return null;
		} finally {
		}
	}
	/**
	 * 通过id删除表对应的行
	 * @param item：指出pojo，也即是指出数据库表名
	 * @param id:表对应的id
	 * @return：删除成功或失败
	 */
	public boolean deleteById(Class item, Object id) {
		PreparedStatement pst = null;
		try {
			String strSQL = "delete FROM "
					+ "t_user" + " where id=?";//构造delet语句
			

			pst = connMagager.prepareStatement(strSQL);
			pst.setObject(1, id);//设置参数id

			pst.executeUpdate();//执行
			
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
	 * 插入数据
	 * @param obj：要插入的数据对应的pojo对象
	 * @return：插入成功或失败
	 */
	public boolean save(Object obj) {
		try {
			String strSQL = "insert into " + obj.getClass().getField("TABLE_NAME").get(obj.getClass()) + "(";
			
			Field[] fields = obj.getClass().getDeclaredFields(); // 获得类的数据成员
			Method[] methods = obj.getClass().getDeclaredMethods(); // 获得类的函数成员
			Map colMap = new HashMap();
			int nCounter = 0 ;
			String strCounter="";
			for (int i = 0; i < fields.length; i++) { // 对于类的每个数据成员
				if ("TABLE_NAME".equals(fields[i].getName())||"id".equals(fields[i].getName()))
					continue; // 特殊数据成员，忽略不处理
				
				//根据变量名，构造其getter函数
				String methodName = "get"
						+ fields[i].getName().substring(0, 1).toUpperCase()
						+ fields[i].getName().substring(1);

				Method method = null;

				//根据getter函数名，找到对应的函数对象
				for (int j = 0; j < methods.length; j++) {
					if (methods[j].getName().equals(methodName)) {
						method = methods[j];
						break;
					}
				}

				//执行函数，得到值
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
	 * 更新数据
	 * @param obj：要更新的对象。其中id必须非空，根据该id去更新数据库
	 * @return：操作成功或失败
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
			System.out.println("更新数据库出错！");
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
			System.out.println("更新数据库出错！");
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
			System.out.println("更新数据库出错！");
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
	 * 利用反射通过数据集创建对象 (要求pojo类的set方法名称必须是set+属性名，其中属性名首字母必须大写)
	 * 
	 * @param rs : 数据库中读取到的数据集
	 * @param obj ：要创建对象的名称
	 * @return ：数据集转化成 的类对象
	 * @throws Exception
	 */
	private Object creatObject(ResultSet rs, Class objClass) throws Exception {
		Object object = objClass.newInstance(); // 通过类名，实例化该类的对象
		Field[] fields = objClass.getDeclaredFields(); // 获得类的数据成员
		Method[] mds = objClass.getDeclaredMethods(); // 获得类的函数成员

		for (int i = 0; i < fields.length; i++) { // 对于类的每个数据成员，调用其setter来设置值
			if ("TABLE_NAME".equals(fields[i].getName())||"id".equals(fields[i].getName()))
				continue; // 特殊数据成员，忽略不处理

			//根据变量名构造setter函数
			String methodName = "set"
					+ fields[i].getName().substring(0, 1).toUpperCase()
					+ fields[i].getName().substring(1);

			Method method = null;

			//setter函数名，找到对应的函数对象
			for (int j = 0; j < mds.length; j++) {
				if (mds[j].getName().equals(methodName)) {
					method = mds[j];
					break;
				}
			}

			//执行函数
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
				System.out.println("更新数据库出错zzz！");
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