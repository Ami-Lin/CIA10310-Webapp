package com.member.model;

import java.util.List;

import com.member.model.MemberVO;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void delete(Integer mem_id);
    public MemberVO findByPrimaryKey(Integer mem_id);
    public List<MemberVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
