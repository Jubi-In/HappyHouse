package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.MemoDto;
import com.ssafy.happyhouse.model.mapper.MemoMapper;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class MemoServiceImpl implements MemoService{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeQnAMemo(MemoDto memoDto) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).writeQnAMemo(memoDto) == 1;
	}

	@Override
	public boolean writeNoticeMemo(MemoDto memoDto) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).writeNoticeMemo(memoDto) == 1;
	}

	@Override
	public List<MemoDto> listQnAMemo(int articleno) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).listQnAMemo(articleno);
	}

	@Override
	public List<MemoDto> listNoticeMemo(int articleno) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).listNoticeMemo(articleno);
	}
	
	@Override
	@Transactional
	public boolean deleteQnAMemo(int memono) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).deleteQnAMemo(memono) == 1;		
	}
	
	@Override
	@Transactional
	public boolean deleteNoticeMemo(int memono) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).deleteNoticeMemo(memono) == 1;		
	}
	
	
	
	
	
}
