package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;

@Mapper
public interface BoardMapper {
	public int writeQnA(BoardDto boardDto) throws SQLException;
	public int writeNotice(BoardDto boardDto) throws SQLException;
	public List<BoardDto> listQnA(BoardParameterDto boardParameterDto) throws SQLException;
	public List<BoardDto> listNotice(BoardParameterDto boardParameterDto) throws SQLException;
	public void updateQnAHit(int articleno) throws SQLException;
	public void updateNoticeHit(int articleno) throws SQLException; 
	public BoardDto getQnA(int articleno) throws SQLException;
	public BoardDto getNotice(int articleno) throws SQLException;
	public int deleteQnA(int articleno) throws Exception;
	public int deleteNotice(int articleno) throws Exception;
	public void deleteQnAMemo(int articleno) throws Exception;
	public void deleteNoticeMemo(int articleno) throws Exception;
	public int modifyQnA(BoardDto boardDto) throws Exception;
	public int modifyNotice(BoardDto boardDto) throws Exception;
	public int getTotalCount(BoardParameterDto boardParameterDto);
}
