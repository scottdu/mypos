package com.thoughtworks.constant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;

import com.thoughtworks.domain.ActivityEntity;
import com.thoughtworks.domain.ItemActivityEntity;
import com.thoughtworks.domain.ItemEntity;
import com.thoughtworks.util.DBUtil;


public class Constant {
	
	public static Map<String, ItemEntity> ITEM_CODE_ENTITY_MAP = new HashMap<String, ItemEntity>();
	
	public static Map<String, ActivityEntity> ACTIVITY_CODE_ENTITY_MAP = new HashMap<String, ActivityEntity>();
	
	public static List<ItemActivityEntity> ITEM_ACTIVITY_LIST = new ArrayList<ItemActivityEntity>();
	
	public static void getParameters() {
		//加载配置文件
		loadDBConfig();
		
		//加载缓存
		loadCache();
	}
	
	private static void loadDBConfig() {
		try 
		{
			PropertyConfigurator.configure("./config/proxool.properties");
		}
		catch (ProxoolException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void loadCache() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT `id`,`code`,`name`,`unit`,`unit_price` FROM `item`");
			ITEM_CODE_ENTITY_MAP.clear();
			while(rs.next()) {
				ItemEntity entity = new ItemEntity();
				entity.setId(rs.getInt("id"));
				entity.setCode(rs.getString("code"));
				entity.setName(rs.getString("name"));
				entity.setUnit(rs.getString("unit"));
				entity.setUnitPrice(rs.getBigDecimal("unit_price"));
				ITEM_CODE_ENTITY_MAP.put(entity.getCode(), entity);
			}
			
			rs.close();
			
			ACTIVITY_CODE_ENTITY_MAP.clear();
			rs = stmt.executeQuery("SELECT `id`,`code`,`name`,`impl_class_name` FROM `activity`");
			while(rs.next()) {
				ActivityEntity entity = new ActivityEntity();
				entity.setId(rs.getInt("id"));
				entity.setCode(rs.getString("code"));
				entity.setName(rs.getString("name"));
				entity.setImplClassName(rs.getString("impl_class_name"));
				ACTIVITY_CODE_ENTITY_MAP.put(entity.getCode(), entity);
			}
			
			rs.close();
			ITEM_ACTIVITY_LIST.clear();
			rs = stmt.executeQuery("SELECT `id`,`item_code`,`activity_code`,`seq_no` FROM `item_activity_relation` ORDER BY `seq_no` ASC");
			while(rs.next()) {
				ItemActivityEntity entity = new ItemActivityEntity();
				entity.setId(rs.getInt("id"));
				entity.setItemCode(rs.getString("item_code"));
				entity.setActivityCode(rs.getString("activity_code"));
				entity.setSeqNo(rs.getInt("seq_no"));
				ITEM_ACTIVITY_LIST.add(entity);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		
		
	}
}
