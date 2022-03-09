package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import com.ssafy.happyhouse.model.FavoriteDto;

public interface FavoriteMapper {
	List<FavoriteDto> getFavoriteList(String user_id) throws SQLException;
	public int registerFavorite(FavoriteDto favoriteDto) throws SQLException;
	public int favCheck(String user_id, String sidoCode, String gugunCode) throws SQLException;
	public int deleteFav(String user_id, String sidoCode, String gugunCode) throws SQLException;
}
