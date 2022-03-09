package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.mapper.UserMapper;
import com.ssafy.happyhouse.model.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 로그인
	// 2021.11.18 한길 수정 
	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUser_id() == null || userDto.getUser_pw() == null) 
			return null;
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}
	
	// 로그인, token으로 userDto 전체 가져가기
	// 2021.11.18 한길 수정
	@Override
	public UserDto userInfo(String user_id) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(user_id);
	}
	
	// 회원정보 입력하고 회원가입 버튼 누를 때
	// 2021.11.19 한길 수정
	@Override
	public boolean registerMember(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).registerMember(userDto) == 1;
	}
	
	// 회원 탈퇴
	// 2021.11.21 한길 수정
	@Override
	public boolean deleteUser(String user_id) throws Exception {
		return sqlSession.getMapper(UserMapper.class).deleteUser(user_id) == 1;
	}
	
	// 2021.11.23 한길 수정
	@Override
	public int idCheck(String id) throws Exception {
		return sqlSession.getMapper(UserMapper.class).idCheck(id);
	}

	// 2021.11.24 한길 수정
	@Override
	public boolean updateUser(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).updateUser(userDto) == 1;
	}
}
