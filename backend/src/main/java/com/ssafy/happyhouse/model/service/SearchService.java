package com.ssafy.happyhouse.model.service;

import java.util.List;
import com.ssafy.happyhouse.model.HouseInfoDto;

public interface SearchService {
//	List<HouseInfoDto> listSearchWord(String word) throws Exception;
	List<String> listSearchWord(String word) throws Exception;
	List<HouseInfoDto> searchWithName(String word) throws Exception;
}
