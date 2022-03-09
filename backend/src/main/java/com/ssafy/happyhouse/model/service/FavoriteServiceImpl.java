package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<FavoriteDto> getFavoriteList(String user_id) throws Exception {
	return sqlSession.getMapper(FavoriteMapper.class).getFavoriteList(user_id);
	}

	@Override
	public boolean registerFavorite(FavoriteDto favoriteDto) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).registerFavorite(favoriteDto) == 1;
	}

	@Override
	public int favCheck(String user_id, String sidoCode, String gugunCode) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).favCheck(user_id, sidoCode, gugunCode);
	}

	@Override
	public boolean deleteFav(String user_id, String sidoCode, String gugunCode) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).deleteFav(user_id, sidoCode, gugunCode) == 1;
	}
	
	
}
