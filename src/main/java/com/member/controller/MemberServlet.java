package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;


public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer memid = null;
				try {
					memid = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memid);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer memid = Integer.valueOf(req.getParameter("memid"));
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer memid = Integer.valueOf(req.getParameter("memid").trim());
				
// 帳號
String ac = req.getParameter("ac");
				String acReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if (ac == null || ac.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!ac.trim().matches(acReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英數字和_ , 且長度必需在6到20之間");
	            }
				
// 密碼
				String pw = req.getParameter("pw");
				String pwReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if (pw == null || pw.trim().length() == 0) {
				errorMsgs.add("會員密碼: 請勿空白");
				} else if(!pw.trim().matches(pwReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員密碼: 只能是英數字和_ , 且長度必需在6到20之間");
				}
				
// 電子郵件
String email = req.getParameter("email");

// 會員狀態
Integer status = Integer.valueOf(req.getParameter("status").trim());

// 註冊日期
		java.sql.Date registertime = null;
		try {
registertime = java.sql.Date.valueOf(req.getParameter("registertime").trim());
		} catch (IllegalArgumentException e) {
			registertime=new java.sql.Date(System.currentTimeMillis());
			errorMsgs.add("應該不會出錯吧ㄏㄏ");
		}

// 姓名
String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

// 生日 (有需要格式限定嗎？)
				java.sql.Date birth = null;
				try {
birth = java.sql.Date.valueOf(req.getParameter("birth").trim());
				} catch (IllegalArgumentException e) {
					birth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("應該不會出錯吧ㄏㄏ");
				}
// 性別				
String sex = req.getParameter("sex").trim();

// 手機號碼
String phone = req.getParameter("phone");
				String phoneReg = "^09[0-9][0-9]-\\d{6}$"; //電話號碼
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				} else if(!phone.trim().matches(phoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機號碼: 格式錯誤");
				}
// 市
String city = req.getParameter("city").trim();
				if (city == null || city.trim().length() == 0) {
					errorMsgs.add("市請勿空白");
				}	
// 區				
String disc = req.getParameter("disc").trim();
				if (disc == null || disc.trim().length() == 0) {
					errorMsgs.add("區請勿空白");
				}
// 地址
String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
//照片 (測試)
				InputStream imgStream = req.getInputStream();
byte[] img = new byte[req.getContentLength()];
				imgStream.read(img);

				MemberVO memberVO = new MemberVO();
				memberVO.setMemid(memid);
				memberVO.setAc(ac);
				memberVO.setPw(pw);
				memberVO.setEmail(email);
				memberVO.setRegistertime(registertime);
				memberVO.setName(name);
				memberVO.setBirth(birth);
				memberVO.setSex(sex);
				memberVO.setPhone(phone);
				memberVO.setCity(city);
				memberVO.setDisc(disc);
				memberVO.setAddress(address);
				memberVO.setImg(img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(memid,ac, pw, email, registertime, name, birth, sex, phone,
						city, disc, address, img);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
// 帳號
String ac = req.getParameter("ac");
							String acReg = "^[(a-zA-Z0-9_)]{6,20}$";
							if (ac == null || ac.trim().length() == 0) {
								errorMsgs.add("會員帳號: 請勿空白");
							} else if(!ac.trim().matches(acReg)) { //以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("會員帳號: 只能是英數字和_ , 且長度必需在6到20之間");
				            }
							
// 密碼
String pw = req.getParameter("pw");
			String pwReg = "^[(a-zA-Z0-9_)]{6,20}$";
							if (pw == null || pw.trim().length() == 0) {
							errorMsgs.add("會員密碼: 請勿空白");
							} else if(!pw.trim().matches(pwReg)) { //以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("會員密碼: 只能是英數字和_ , 且長度必需在6到20之間");
							}
							
// 電子郵件
String email = req.getParameter("email");

// 會員狀態
Integer status = Integer.valueOf(req.getParameter("status").trim());

// 註冊日期
					java.sql.Date registertime = null;
					try {
registertime = java.sql.Date.valueOf(req.getParameter("registertime").trim());
					} catch (IllegalArgumentException e) {
						registertime=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("應該不會出錯吧ㄏㄏ");
					}

// 姓名
String name = req.getParameter("name");
							String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
							if (name == null || name.trim().length() == 0) {
								errorMsgs.add("會員姓名: 請勿空白");
							} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				            }

// 生日 (有需要格式限定嗎？)
							java.sql.Date birth = null;
							try {
birth = java.sql.Date.valueOf(req.getParameter("birth").trim());
							} catch (IllegalArgumentException e) {
								birth=new java.sql.Date(System.currentTimeMillis());
								errorMsgs.add("應該不會出錯吧ㄏㄏ");
							}
// 性別				
String sex = req.getParameter("sex").trim();

// 手機號碼
String phone = req.getParameter("phone");
							String phoneReg = "^09[0-9][0-9]-\\d{6}$"; //電話號碼
							if (phone == null || phone.trim().length() == 0) {
								errorMsgs.add("手機號碼: 請勿空白");
							} else if(!phone.trim().matches(phoneReg)) { //以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("手機號碼: 格式錯誤");
							}
// 市
String city = req.getParameter("city").trim();
							if (city == null || city.trim().length() == 0) {
								errorMsgs.add("市請勿空白");
							}	
// 區				
String disc = req.getParameter("disc").trim();
							if (disc == null || disc.trim().length() == 0) {
								errorMsgs.add("區請勿空白");
							}
// 地址
String address = req.getParameter("address").trim();
							if (address == null || address.trim().length() == 0) {
								errorMsgs.add("地址請勿空白");
							}
							
//照片 (測試)
							InputStream imgStream = req.getInputStream();
byte[] img = new byte[req.getContentLength()];
							imgStream.read(img);

							MemberVO memberVO = new MemberVO();
//							memberVO.setMemid(memid);
							memberVO.setAc(ac);
							memberVO.setPw(pw);
							memberVO.setEmail(email);
							memberVO.setRegistertime(registertime);
							memberVO.setName(name);
							memberVO.setBirth(birth);
							memberVO.setSex(sex);
							memberVO.setPhone(phone);
							memberVO.setCity(city);
							memberVO.setDisc(disc);
							memberVO.setAddress(address);
							memberVO.setImg(img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(ac, pw, email, registertime, name, birth, 
						sex, phone,city, disc, address,img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer memid = Integer.valueOf(req.getParameter("memid"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(memid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
