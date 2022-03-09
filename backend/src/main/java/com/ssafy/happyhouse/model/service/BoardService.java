package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.util.PageNavigation;

public interface BoardService {
	public boolean writeQnA(BoardDto boardDto) throws Exception;
	public boolean writeNotice(BoardDto boardDto) throws Exception;
	public List<BoardDto> listQnA(BoardParameterDto boardParameterDto) throws Exception;
	public List<BoardDto> listNotice(BoardParameterDto boardParameterDto) throws Exception;
	public void updateQnAHit(int articleno) throws Exception;
	public void updateNoticeHit(int articleno) throws Exception;
	public BoardDto getQnA(int articleno) throws Exception;
	public BoardDto getNotice(int articleno) throws Exception;
	public boolean deleteQnA(int articleno) throws Exception;
	public boolean deleteNotice(int articleno) throws Exception;
	public boolean modifyQnA(BoardDto boardDto) throws Exception;
	public boolean modifyNotice(BoardDto boardDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
}
