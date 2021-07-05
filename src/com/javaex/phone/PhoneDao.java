package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	public void getConnection() {
		try {

			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void closed() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public List<PhoneVo> getPersonList() {
		List<PhoneVo> personList = new ArrayList<PhoneVo>();

		getConnection();
		try {
			String query = "";
			query += "select person_id,name,hp,company ";
			query += " from person ";
			query += " order by person_id asc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String personId = rs.getString(1);
				String personName = rs.getString(2);
				String personHp = rs.getString(3);
				String personCom = rs.getString(4);

				PhoneVo phoneVo = new PhoneVo(personId, personName, personHp, personCom);
				personList.add(phoneVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		closed();
		return personList;

	}

	public int personInsert(PhoneVo phoneVo) {
		getConnection();
		int count = 0;

		try {
			String query = ""; //
			query += " insert into person ";
			query += " values(seq_person_id.nextval,?,?,?) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phoneVo.getPersonName());
			pstmt.setString(2, phoneVo.getPersonHp());
			pstmt.setString(3, phoneVo.getPersonCom());
			count = pstmt.executeUpdate();

			System.out.println(count + "건이 저장되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		closed();
		return count;

	}

	public int personUpdate(PhoneVo phoneVo) {
		getConnection();
		int count = 0;

		try {
			String query = ""; //
			query += " update person ";
			query += " set name = ?, hp = ?, company = ? where person_id=? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phoneVo.getPersonName());
			pstmt.setString(2, phoneVo.getPersonHp());
			pstmt.setString(3, phoneVo.getPersonCom());
			pstmt.setString(4, phoneVo.getPersonId());
			count = pstmt.executeUpdate();

			System.out.println(count + "건이 수정 되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		closed();
		return count;

	}

	public int personDelete(int personId) {
		int count = 0;
		getConnection();
		try {
			String query = "";

			query += " delete from person ";
			query += " where person_id= ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			System.out.println(count + "건이 삭제되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		closed();
		return count;
	}

	public List<PhoneVo> personSearch(String search) {

		List<PhoneVo> personList = new ArrayList<PhoneVo>();
		getConnection();
		try {
			String query = "";

			query += " select person_id,name,hp,company ";
			query += " from person ";
			query += " where (name || hp || company) like " + "'%" + search + "%' ";
			query += " order by person_id asc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String personId = rs.getString(1);
				String personName = rs.getString(2);
				String personHp = rs.getString(3);
				String personCom = rs.getString(4);

				PhoneVo phoneVo = new PhoneVo(personId, personName, personHp, personCom);
				personList.add(phoneVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		closed();
		return personList;

	}
}