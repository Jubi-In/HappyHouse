package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SqlSession sqlSession;

//	@Override
//	public List<HouseInfoDto> listSearchWord(String word) throws Exception {
//		return sqlSession.getMapper(SearchMapper.class).listSearchWord(word);
//	}
	
	@Override
	public List<String> listSearchWord(String word) throws Exception {
		return sqlSession.getMapper(SearchMapper.class).listSearchWord(word);
	}

	@Override
	public List<HouseInfoDto> searchWithName(String word) throws Exception {
		return sqlSession.getMapper(SearchMapper.class).searchWithName(word);
	}
	

}
