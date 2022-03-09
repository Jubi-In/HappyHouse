package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.MemoDto;

public interface MemoService {
	public boolean writeQnAMemo(MemoDto memoDto) throws Exception;
	public boolean writeNoticeMemo(MemoDto memoDto) throws Exception;
	public List<MemoDto> listQnAMemo(int articleno) throws Exception;
	public List<MemoDto> listNoticeMemo(int articleno) throws Exception;
	public boolean deleteQnAMemo(int memono) throws Exception;
	public boolean deleteNoticeMemo(int memono) throws Exception;
	
}
