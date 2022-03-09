package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;

public interface SearchMapper {
//	List<HouseInfoDto> listSearchWord(String word) throws SQLException;
	List<String> listSearchWord(String word) throws SQLException;
	List<HouseInfoDto> searchWithName(String word) throws SQLException;

}
