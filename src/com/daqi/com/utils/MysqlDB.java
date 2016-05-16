
 package com.daqi.com.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mysql 宸ュ叿绫�
 * 
 * @author abram
 */
public class MysqlDB {
	static Logger log = LoggerFactory.getLogger(MysqlDB.class);
	public static final int NOT_RESULT = -1;
	
	/**
	 * 榛樿鐨凥andler
	 */
	public static final RowHandler<Map<String,Object>> DEFAULT_HANDLER = new RowHandler<Map<String,Object>>() {
		@Override
		public Map<String, Object> execute(ResultSet row) throws SQLException {
			Map<String,Object> map = new HashMap<String,Object>();
			int colCount = row.getMetaData().getColumnCount();
			for(int i =1;i<=colCount;i++){
				map.put(row.getMetaData().getColumnLabel(i),row.getObject(i));
			}
			return map;
		}
	};

	public static <T> T queryObj(Connection conn, RowHandler<T> handler, String sql, Object... params) throws SQLException,
			InstantiationException, IllegalAccessException {
		PreparedStatement preStat = null;
		ResultSet rs = null;
		try {
			preStat = conn.prepareStatement(sql);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			rs = preStat.executeQuery();
			T clazz = null;
			while (rs.next()) {
				clazz = handler.execute(rs);
				break;
			}
			return clazz;
		}catch(Exception ex){
			System.out.print(ex);
		}finally {
			if (rs != null)
				rs.close();
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public static int queryForInt(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		ResultSet rs = null;
		try {
			preStat = conn.prepareStatement(sql);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			rs = preStat.executeQuery();
			int id = NOT_RESULT;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} finally {
			if (rs != null)
				rs.close();
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}

	public static Timestamp queryForTimeStamp(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		ResultSet rs = null;
		try {
			preStat = conn.prepareStatement(sql);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			rs = preStat.executeQuery();
			Timestamp id = null;
			if (rs.next()) {
				id = rs.getTimestamp(1);
			}
			return id;
		} finally {
			if (rs != null)
				rs.close();
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}

	public static float queryForFloat(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		ResultSet rs = null;
		try {
			preStat = conn.prepareStatement(sql);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			rs = preStat.executeQuery();
			float id = NOT_RESULT;
			if (rs.next()) {
				id = rs.getFloat(1);
			}
			return id;
		} finally {
			if (rs != null)
				rs.close();
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}
	
	public static String queryForString(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		ResultSet rs = null;
		try {
			preStat = conn.prepareStatement(sql);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			rs = preStat.executeQuery();
			String result = "";
			if (rs.next()) {
				result = rs.getString(1);
			}
			return result;
		} finally {
			if (rs != null)
				rs.close();
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 鎵归噺鎻掑叆鏁版嵁搴�
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @author Kevin
	 * @since 2014-06-19
	 */
	public static int executeBatch(Connection conn, String[] sql) throws SQLException {
		int times = 0;
		Statement st = null;
		try {
			if (null == sql) {
				return times;
			}
			conn.setAutoCommit(false);
			st = conn.createStatement();
			for (int i = 0; i < sql.length; i++) {
				st.addBatch(sql[i]);
			}
			times = st.executeBatch().length;
			conn.commit();
			return times;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}
	
	

	/**
	 * 浠庢暟鎹簱涓绱竴涓猯ong瀛楁
	 * 
	 * @author abram
	 * @param conn 杩炴帴
	 * @param sql sql璇彞
	 * @param params 鍙傛暟
	 * @return long
	 * @throws SQLException
	 * @sinc 0.0.2
	 */
	public static long queryForLong(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		ResultSet rs = null;
		try {
			preStat = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				setPreStateParams(preStat, params);
			}
			rs = preStat.executeQuery();
			long res = NOT_RESULT;
			if (rs.next()) {
				res = rs.getLong(1);
			}
			return res;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)
				rs.close();
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}

	public static <T> List<T> queryList(Connection conn, RowHandler<T> handler, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		ResultSet rs = null;
		try {
			preStat = conn.prepareStatement(sql);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			rs = preStat.executeQuery();
			List<T> list = new ArrayList<T>();
			while (rs.next()) {
				list.add(handler.execute(rs));
			}
			return list;
		} finally {
			if (rs != null)
				rs.close();
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}
	
	public static int execute(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		try {
			if (conn == null) {
				throw new SQLException("connection is null");
			}
			preStat = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			int updated = preStat.executeUpdate();
			conn.commit();
			return updated;
		} catch (SQLException e) {
			if (conn != null)
				conn.rollback();
			throw e;
		} finally {
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}
	
	

	/**
	 * 鎻掑叆鏁版嵁骞惰繑鍥炵敓鎴愮殑涓婚敭
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Integer> insert(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			if (conn == null) {
				throw new SQLException("connection is null");
			}
			preStat = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			conn.setAutoCommit(false);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			preStat.executeUpdate();
			ResultSet rst = preStat.getGeneratedKeys();
			if (rst == null)
				throw new SQLException("sql error");

			while (rst.next()) {
				ids.add(rst.getInt(1));
			}
			rst.close();
			conn.commit();
			return ids;
		} catch (SQLException e) {
			if (conn != null)
				conn.rollback();
			throw e;
		} finally {
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}
	
	/**
	 * 鎻掑叆鏁版嵁骞惰繑鍥炵敓鎴愮殑涓婚敭
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int insertOne(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement preStat = null;
		try {
			if (conn == null) {
				throw new SQLException("connection is null");
			}
			preStat = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			conn.setAutoCommit(false);
			if (params != null) {
				setPreStateParams(preStat, params);
			}
			preStat.executeUpdate();
			ResultSet rst = preStat.getGeneratedKeys();
			if (rst == null)
				throw new SQLException("sql error");
			int id=0;
			while (rst.next()) {
				id=rst.getInt(1);
				break;
			}
			rst.close();
			conn.commit();
			return id;
		} catch (SQLException e) {
			if (conn != null)
				conn.rollback();
			throw e;
		} finally {
			if (preStat != null)
				preStat.close();
			if (conn != null)
				conn.close();
		}
	}

	public static void setPreStateParams(PreparedStatement preStat, Object... params) throws SQLException {
		int i = 1;
		for (Object p : params) {
			if (p instanceof Integer) {
				preStat.setInt(i, (Integer) p);
			}
			if (p instanceof Short) {
				preStat.setShort(i, (Short) p);
			}
			if (p instanceof Byte) {
				preStat.setByte(i, (Byte) p);
			}
			if (p instanceof Long) {
				preStat.setLong(i, (Long) p);
			}
			if (p instanceof String) {
				preStat.setString(i, (String) p);
			}
			if (p instanceof Float) {
				preStat.setFloat(i, (Float) p);
			}
			if (p instanceof Double) {
				preStat.setDouble(i, (Double) p);
			}
			if (p instanceof Timestamp) {
				preStat.setTimestamp(i, (Timestamp) p);
			}
			if (p instanceof Date) {
				preStat.setDate(i, (Date) p);
			}
			if (p instanceof byte[]) {
				preStat.setBytes(i, (byte[]) p);
			}
			i++;
		}
	}

	public static interface RowHandler<T> {
		public T execute(ResultSet row) throws SQLException;
	}

	public static interface Handler<T> {
		public T execute(ResultSet result) throws SQLException;
	}
}
