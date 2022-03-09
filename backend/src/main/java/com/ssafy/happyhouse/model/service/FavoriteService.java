package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.FavoriteDto;

public interface FavoriteService {
	List<FavoriteDto> getFavoriteList(String user_id) throws Exception;
	public boolean registerFavorite(FavoriteDto favoriteDto) throws Exception;
	public int favCheck(String user_id, String sidoCode, String gugunCode) throws Exception;
	public boolean deleteFav(String user_id, String sidoCode, String gugunCode) throws Exception;
}
