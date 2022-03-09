package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemoDto;

@Mapper
public interface MemoMapper {
	public int writeQnAMemo(MemoDto memoDto) throws SQLException;
	public int writeNoticeMemo(MemoDto memoDto) throws SQLException;
	public List<MemoDto> listQnAMemo(int articleno) throws SQLException;
	public List<MemoDto> listNoticeMemo(int articleno) throws SQLException;
	public int deleteQnAMemo(int memono) throws Exception;
	public int deleteNoticeMemo(int memono) throws Exception;
}
