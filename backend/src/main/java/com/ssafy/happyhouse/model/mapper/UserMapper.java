package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserMapper {
	public UserDto login(UserDto userDto) throws Exception; // 2021.11.18 한길수정
	public UserDto userInfo(String user_id) throws Exception; // 2021.11.18 한길수정
	public int registerMember(UserDto userDto) throws Exception; // 2021.11.19 한길 수정
//	void deleteUser(String id) throws SQLException; // 2021.11.21 한길 수정 :: 유저 회원탈퇴 or 제명
	public int deleteUser(String user_id) throws Exception; // 2021.11.21 한길 추가 :: 유저 회원탈퇴 or 제명
	public int updateUser(UserDto userDto) throws SQLException; // 2021.11.24 한길 추가 :: 유저정보 수정
	
	int idCheck(String id) throws SQLException; // 아이디 체크
}
