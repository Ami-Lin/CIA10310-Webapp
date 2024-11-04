package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/my_database?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "kk880722";
	
	private static final String INSERT_STMT = 
			"INSERT INTO member VALUES (?, ?, ?, ?, ?,? , ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String INSERT_STMT = 
//			"INSERT INTO member (mem_id,ac,pw,email,status,registertime,name,birth,sex,phone,city,disc,address,img) VALUES (?, ?, ?, ?, ?,now(), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT mem_id,ac,pw,email,status,registertime,name,birth,sex,phone,city,disc,address,img FROM member order by mem_id";
	private static final String GET_ONE_STMT = 
			"SELECT mem_id,ac,pw,email,status,registertime,name,birth,sex,phone,city,disc,address,img FROM member where mem_id = ?";
	private static final String DELETE = 
			"DELETE FROM member where mem_id = ?";
	private static final String UPDATE = 
			"UPDATE member set ac=?,pw=?,email=?,status=?,registertime=?,name=?,birth=?,sex=?,phone=?,city=?,disc=?,address=?,img=? where mem_id = ?";
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberVO.getMemid());
			pstmt.setString(2, memberVO.getAc());
			pstmt.setString(3, memberVO.getPw());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setInt(5, 0);
			pstmt.setDate(6,memberVO.getRegistertime());
			pstmt.setString(7, memberVO.getName());
			pstmt.setDate(8, memberVO.getBirth());
			pstmt.setString(9, memberVO.getSex());
			pstmt.setString(10, memberVO.getPhone());
			pstmt.setString(11, memberVO.getCity());
			pstmt.setString(12, memberVO.getDisc());
			pstmt.setString(13, memberVO.getAddress());
			pstmt.setBytes(14, memberVO.getImg());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			UPDATE member set ac=?,pw=?,email=?,status=?,registertime=?,name=?,birth=?,sex=?,phone=?,city=?,disc=?,address=?,img=? where mem_id = ?
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
// 
			pstmt.setString(1, memberVO.getAc());
			pstmt.setString(2, memberVO.getPw());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setInt(4, memberVO.getStatus());
			pstmt.setDate(5, memberVO.getRegistertime());
			pstmt.setString(6, memberVO.getName());
			pstmt.setDate(7, memberVO.getBirth());
			pstmt.setString(8, memberVO.getSex());
			pstmt.setString(9, memberVO.getPhone());
			pstmt.setString(10, memberVO.getCity());
			pstmt.setString(11, memberVO.getDisc());
			pstmt.setString(12, memberVO.getAddress());
			pstmt.setBytes(13, memberVO.getImg());

			pstmt.setInt(14, memberVO.getMemid());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}
	@Override
	public void delete(Integer mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	@Override
	public MemberVO findByPrimaryKey(Integer mem_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMemid(rs.getInt("mem_id"));
				memberVO.setAc(rs.getString("ac"));
				memberVO.setPw(rs.getString("pw"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setStatus(rs.getInt("status"));
				memberVO.setRegistertime(rs.getDate("registertime"));
				memberVO.setName(rs.getString("name"));
				memberVO.setBirth(rs.getDate("birth"));
				memberVO.setSex(rs.getString("sex"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setCity(rs.getString("city"));
				memberVO.setDisc(rs.getString("disc"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setImg(rs.getBytes("img"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}
	
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				MemberVO = new MemberVO();
				MemberVO.setMemid(rs.getInt("mem_id"));
				MemberVO.setAc(rs.getString("ac"));
				MemberVO.setPw(rs.getString("pw"));
				MemberVO.setEmail(rs.getString("email"));
				MemberVO.setStatus(rs.getInt("status"));
				MemberVO.setRegistertime(rs.getDate("registertime"));
				MemberVO.setName(rs.getString("name"));
				MemberVO.setBirth(rs.getDate("birth"));
				MemberVO.setSex(rs.getString("sex"));
				MemberVO.setPhone(rs.getString("phone"));
				MemberVO.setCity(rs.getString("city"));
				MemberVO.setDisc(rs.getString("disc"));
				MemberVO.setAddress(rs.getString("address"));
				MemberVO.setImg(rs.getBytes("img"));
				list.add(MemberVO); // Store the row in the list
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
		// 新增		
		MemberVO memberVO1 = new MemberVO();
		java.util.Date utilDate = new Date(System.currentTimeMillis());
        Timestamp sqlDate = new Timestamp(utilDate.getTime());
		
        memberVO1.setMemid(1012);
		memberVO1.setAc("weiwei2");
		memberVO1.setPw("wasiweiwei");
		memberVO1.setEmail("weiwei@gmail.com");
		memberVO1.setStatus(0);
		memberVO1.setRegistertime(java.sql.Date.valueOf("2024-11-04"));
		memberVO1.setName("張均濰");
		memberVO1.setBirth(java.sql.Date.valueOf("2001-02-18"));
		memberVO1.setSex("男");
		memberVO1.setPhone("0900123456");
		memberVO1.setCity("桃園市");
		memberVO1.setDisc("大溪區");
		memberVO1.setAddress("大溪老街");
		dao.insert(memberVO1);

//		 修改
//		"UPDATE member set ac=?,pw=?,email=?,status=?,registertime=?,name=?,birth=?,sex=?,phone=?,city=?,disc=?,address=?,img=? where mem_id = ?";
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setAc("ami0722");
//		memberVO2.setPw("ami0722");
//		memberVO2.setEmail("ami0722@gmail.com");
//		memberVO2.setStatus(1);
//		memberVO2.setRegistertime(java.sql.Date.valueOf("2024-11-04"));
//		memberVO2.setName("Ami");
//		memberVO2.setBirth(java.sql.Date.valueOf("1999-08-03"));
//		memberVO2.setSex("1");
//		memberVO2.setPhone("0919475657");
//		memberVO2.setCity("新北市");
//		memberVO2.setDisc("土城區");
//		memberVO2.setAddress("學府路一段");
//		memberVO2.setImg(null);
//		memberVO2.setMemid(1001);
//		dao.update(memberVO2);

//		 刪除
//		dao.delete(1010); //輸memid

//		 查詢
//		MemberVO memberVO3 = dao.findByPrimaryKey(1001);
//		System.out.print(memberVO3.getMemid() + ",");
//		System.out.print(memberVO3.getAc() + ",");
//		System.out.print(memberVO3.getPw() + ",");
//		System.out.print(memberVO3.getEmail() + ",");
//		System.out.print(memberVO3.getStatus() + ",");
//		System.out.print(memberVO3.getRegistertime() + ",");
//		System.out.print(memberVO3.getName() + ",");
//		System.out.print(memberVO3.getBirth() + ",");
//		System.out.print(memberVO3.getPhone() + ",");
//		System.out.print(memberVO3.getCity() + ",");
//		System.out.print(memberVO3.getDisc() + ",");
//		System.out.print(memberVO3.getAddress() + ",");
//		System.out.print(memberVO3.getImg() + ",");
//
//		System.out.println("---------------------");
		
		// 查詢
//			List<MemberVO> list = dao.getAll();
//			for (MemberVO aMem : list) {
//				System.out.print(aMem.getMemid()+",");
//				System.out.print(aMem.getAc()+",");
//				System.out.print(aMem.getPw()+",");
//				System.out.print(aMem.getEmail()+",");
//				System.out.print(aMem.getStatus()+",");
//				System.out.print(aMem.getRegistertime()+",");
//				System.out.print(aMem.getName()+",");
//				System.out.print(aMem.getBirth()+",");
//				System.out.print(aMem.getSex()+",");
//				System.out.print(aMem.getPhone()+",");
//				System.out.print(aMem.getCity()+",");
//				System.out.print(aMem.getDisc()+",");
//				System.out.print(aMem.getAddress()+",");
//				System.out.print(aMem.getImg()+",");
//				System.out.println();
//			}		
	}
	
	
}
