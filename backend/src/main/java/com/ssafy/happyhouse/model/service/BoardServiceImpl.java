package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeQnA(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeQnA(boardDto) == 1;
	}

	@Override
	public boolean writeNotice(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeNotice(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listQnA(BoardParameterDto boardParameterDto) throws Exception {
		// 현재페이지가 0이면 0, 1 이상이면 그거로부터 10 곱한거. (0~10, 11~20, 21~30 ...)
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listQnA(boardParameterDto);
	}

	@Override
	public List<BoardDto> listNotice(BoardParameterDto boardParameterDto) throws Exception {
		// 현재페이지가 0이면 0, 1 이상이면 그거로부터 10 곱한거. (0~10, 11~20, 21~30 ...)
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listNotice(boardParameterDto);
	}
	
	@Override
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 1;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public void updateQnAHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateQnAHit(articleno);
	}
	
	@Override
	public void updateNoticeHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateNoticeHit(articleno);
	}
	
	@Override
	public BoardDto getQnA(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getQnA(articleno);
	}
	@Override
	public BoardDto getNotice(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getNotice(articleno);
	}
	
	@Override
	@Transactional
	public boolean deleteQnA(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteQnAMemo(articleno);
		return sqlSession.getMapper(BoardMapper.class).deleteQnA(articleno) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteNotice(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteNoticeMemo(articleno);
		return sqlSession.getMapper(BoardMapper.class).deleteNotice(articleno) == 1;
	}
	
	@Override
	@Transactional
	public boolean modifyQnA(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyQnA(boardDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean modifyNotice(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyNotice(boardDto) == 1;
	}
}
