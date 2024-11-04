package com.member.model;

import java.util.List;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberJDBCDAO();
	}

	public MemberVO addMember(String ac, String pw,Integer status,String email,java.sql.Date registertime,
			String name, java.sql.Date birth, String sex,String phone,
			String city,String disc,String address,byte[] img) {

		MemberVO memberVO = new MemberVO();

//		memberVO.setMemid(memid);
		memberVO.setAc(ac);
		memberVO.setPw(pw);
		memberVO.setEmail(email);
		memberVO.setStatus(status);
		memberVO.setRegistertime(registertime);
		memberVO.setName(name);
		memberVO.setBirth(birth);
		memberVO.setSex(sex);
		memberVO.setPhone(phone);
		memberVO.setCity(city);
		memberVO.setDisc(disc);
		memberVO.setAddress(address);
		memberVO.setImg(img);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(Integer memid,String ac, String pw,Integer status,String email,java.sql.Date registertime,
			String name, java.sql.Date birth, String sex,String phone,
			String city,String disc,String address,byte[] img) {

		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemid(memid);
		memberVO.setAc(ac);
		memberVO.setPw(pw);
		memberVO.setStatus(status);
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
		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(Integer memid) {
		dao.delete(memid);
	}

	public MemberVO getOneMember(Integer memid) {
		return dao.findByPrimaryKey(memid);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
