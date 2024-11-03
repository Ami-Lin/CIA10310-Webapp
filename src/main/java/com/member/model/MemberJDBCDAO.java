package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/my_database?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "kk880722";
	
	private static final String INSERT_STMT = 
			"INSERT INTO member (mem_id,ac,pw,email,registertime,name,birth,sex,phone,city,disc,address,img) VALUES (?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?, ?, ?)";
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
			pstmt.setDate(5, memberVO.getRegistertime());
			pstmt.setString(6, memberVO.getName());
			pstmt.setDate(7, memberVO.getBirth());
			pstmt.setString(8, memberVO.getSex());
			pstmt.setString(9, memberVO.getPhone());
			pstmt.setString(10, memberVO.getCity());
			pstmt.setString(11, memberVO.getDisc());
			pstmt.setString(12, memberVO.getAddress());
			pstmt.setBytes(13, memberVO.getImg());

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
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);
//		dao.insert(empVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptno(20);
//		dao.update(empVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
//		System.out.println("---------------------");
		
		// 查詢
			List<MemberVO> list = dao.getAll();
			for (MemberVO aMem : list) {
				System.out.print(aMem.getMemid()+",");
				System.out.print(aMem.getAc()+",");
				System.out.print(aMem.getPw()+",");
				System.out.print(aMem.getEmail()+",");
				System.out.print(aMem.getStatus()+",");
				System.out.print(aMem.getRegistertime()+",");
				System.out.print(aMem.getName()+",");
				System.out.print(aMem.getBirth()+",");
				System.out.print(aMem.getSex()+",");
				System.out.print(aMem.getPhone()+",");
				System.out.print(aMem.getCity()+",");
				System.out.print(aMem.getDisc()+",");
				System.out.print(aMem.getAddress()+",");
				System.out.print(aMem.getImg()+",");
				System.out.println();
			}		
	}
	
	
}
