package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	public UserDto login(UserDto userDto) throws Exception; // 2021.11.18 한길 수정
	public UserDto userInfo(String user_id) throws Exception; // 2021.11.18 한길 수정
	public boolean registerMember(UserDto userDto) throws Exception; // 2021.11.19 한길 수정
//	void deleteUser(String id) throws Exception; // 2021.11.21 한길 수정
	public boolean deleteUser(String user_id) throws Exception; // 2021.11.21 한길 추가 
	int idCheck(String id) throws Exception; // 2021.11.23 한길 수정
	public boolean updateUser(UserDto userDto) throws Exception; // 2021.11.24 한길 수정 
}
