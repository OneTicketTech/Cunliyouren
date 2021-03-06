package com.clyr.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.clyr.dao.IUserDao;
import com.clyr.domain.Admin;
import com.clyr.domain.Product;
import com.clyr.domain.User;
import com.clyr.utils.DBConn;

public class UserDao implements IUserDao {

	@Override
	public void add(User u) {
		DBConn db = new DBConn();
		db.getConn();
		db.doInsert("insert into t_user values(null,'" + u.getOpenId() + "','"
				+ u.getNickName() + "','" + u.getAccessToken() + "','"
				+ u.getHeadImgUrl() + "','" + u.getTelNum() + "','"
				+ u.getHomeTown() + "','" + u.getHighSchool() + "','"
				+ u.getUniversity() + "','" + u.getHomeAddress() + "','"
				+ u.getHomeAddressLocation() + "','" + u.getWorkingAddress() + "','"
				+ u.getWorkingAddressLocation() + "'," + u.getState() + ")");
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(User u) {
		DBConn db = new DBConn();
		db.getConn();
		db.doUpdate("update t_user set nickName='" + u.getNickName()
				+ "' where uId=" + u.getuId());
		db.doUpdate("update t_user set state='" + u.getState() + "' where uId="
				+ u.getuId());
		if (!u.getAccessToken().equals(""))
			db.doUpdate("update t_user set accessToken='" + u.getAccessToken()
					+ "' where uId=" + u.getuId());
		if (!u.getTelNum().equals(""))
			db.doUpdate("update t_user set telNum='" + u.getTelNum()
					+ "' where uId=" + u.getuId());
		if (!u.getHomeTown().equals("- - -") && !u.getHomeTown().equals(""))
			db.doUpdate("update t_user set homeTown='" + u.getHomeTown()
					+ "' where uId=" + u.getuId());
		if (!u.getHighSchool().equals(""))
			db.doUpdate("update t_user set highSchool='" + u.getHighSchool()
					+ "' where uId=" + u.getuId());
		if (!u.getUniversity().equals(""))
			db.doUpdate("update t_user set university='" + u.getUniversity()
					+ "' where uId=" + u.getuId());
		if (!u.getHomeAddress().trim().equals(""))
		{
			db.doUpdate("update t_user set homeAddress='" + u.getHomeAddress()
					+ "' where uId=" + u.getuId());
			db.doUpdate("update t_user set homeAddressLocation='" + u.getHomeAddressLocation()
					+ "' where uId=" + u.getuId());
		}
		if (!u.getWorkingAddress().trim().equals(""))
		{
			db.doUpdate("update t_user set workingAddress='"
					+ u.getWorkingAddress() + "' where uId=" + u.getuId());
			db.doUpdate("update t_user set workingAddressLocation='"
					+ u.getWorkingAddressLocation() + "' where uId=" + u.getuId());
		}
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<User> select(User u) {
		DBConn db = new DBConn();
		db.getConn();
		ArrayList<User> a = new ArrayList<User>();
		ResultSet rs = null;
		String key = " ";
		if (!u.getOpenId().equals(""))
			key += "openId='" + u.getOpenId() + "' and ";
		if (!u.getHighSchool().equals(""))
			key += "highSchool='" + u.getHighSchool() + "' and ";
		if (!u.getUniversity().equals(""))
			key += "university='" + u.getUniversity() + "' and ";
		rs = db.doSelect("select * from t_user where" + key + " 1=1");
		try {
			while (rs.next()) {
				User temp = new User();
				temp.setuId(rs.getInt("uId"));
				temp.setOpenId(rs.getString("openId"));
				temp.setNickName(rs.getString("nickName"));
				temp.setAccessToken(rs.getString("accessToken"));
				temp.setHeadImgUrl(rs.getString("headImgUrl"));
				temp.setTelNum(rs.getString("telNum"));
				temp.setHomeTown(rs.getString("homeTown"));
				temp.setHighSchool(rs.getString("highSchool"));
				temp.setUniversity(rs.getString("university"));
				temp.setHomeAddress(rs.getString("homeAddress"));
				temp.setWorkingAddress(rs.getString("workingAddress"));
				temp.setHomeAddressLocation(rs.getString("homeAddressLocation"));
				temp.setWorkingAddressLocation(rs.getString("workingAddressLocation"));
				temp.setState(rs.getInt("state"));
				a.add(temp);
			}
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public User selectByUId(int uId) {
		DBConn db = new DBConn();
		db.getConn();
		User u = new User();
		ResultSet rs = null;
		rs = db.doSelect("select * from t_user where uId=" + uId);
		try {
			while (rs.next()) {
				User temp = new User();
				temp.setuId(rs.getInt("uId"));
				temp.setOpenId(rs.getString("openId"));
				temp.setNickName(rs.getString("nickName"));
				temp.setAccessToken(rs.getString("accessToken"));
				temp.setHeadImgUrl(rs.getString("headImgUrl"));
				temp.setTelNum(rs.getString("telNum"));
				temp.setHomeTown(rs.getString("homeTown"));
				temp.setHighSchool(rs.getString("highSchool"));
				temp.setUniversity(rs.getString("university"));
				temp.setHomeAddress(rs.getString("homeAddress"));
				temp.setWorkingAddress(rs.getString("workingAddress"));
				temp.setHomeAddressLocation(rs.getString("homeAddressLocation"));
				temp.setWorkingAddressLocation(rs.getString("workingAddressLocation"));
				temp.setState(rs.getInt("state"));
				u = temp;
			}
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public User selectByOpenId(String openId) {
		DBConn db = new DBConn();
		db.getConn();
		User u = new User();
		ResultSet rs = null;
		rs = db.doSelect("select * from t_user where openId= '" + openId+"'");
		try {
			while (rs.next()) {
				User temp = new User();
				temp.setuId(rs.getInt("uId"));
				temp.setOpenId(rs.getString("openId"));
				temp.setNickName(rs.getString("nickName"));
				temp.setAccessToken(rs.getString("accessToken"));
				temp.setHeadImgUrl(rs.getString("headImgUrl"));
				temp.setTelNum(rs.getString("telNum"));
				temp.setHomeTown(rs.getString("homeTown"));
				temp.setHighSchool(rs.getString("highSchool"));
				temp.setUniversity(rs.getString("university"));
				temp.setHomeAddress(rs.getString("homeAddress"));
				temp.setWorkingAddress(rs.getString("workingAddress"));
				temp.setHomeAddressLocation(rs.getString("homeAddressLocation"));
				temp.setWorkingAddressLocation(rs.getString("workingAddressLocation"));
				temp.setState(rs.getInt("state"));
				u = temp;
			}
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public User selectByAccessToken(String accessToken) {
		DBConn db = new DBConn();
		db.getConn();
		User u = new User();
		ResultSet rs = null;
		rs = db.doSelect("select * from t_user where accessToken= '" + accessToken+"'");
		try {
			while (rs.next()) {
				User temp = new User();
				temp.setuId(rs.getInt("uId"));
				temp.setOpenId(rs.getString("openId"));
				temp.setNickName(rs.getString("nickName"));
				temp.setAccessToken(rs.getString("accessToken"));
				temp.setHeadImgUrl(rs.getString("headImgUrl"));
				temp.setTelNum(rs.getString("telNum"));
				temp.setHomeTown(rs.getString("homeTown"));
				temp.setHighSchool(rs.getString("highSchool"));
				temp.setUniversity(rs.getString("university"));
				temp.setHomeAddress(rs.getString("homeAddress"));
				temp.setWorkingAddress(rs.getString("workingAddress"));
				temp.setHomeAddressLocation(rs.getString("homeAddressLocation"));
				temp.setWorkingAddressLocation(rs.getString("workingAddressLocation"));
				temp.setState(rs.getInt("state"));
				u = temp;
			}
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
