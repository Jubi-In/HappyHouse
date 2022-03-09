package com.ssafy.happyhouse.model.controller;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.service.FavoriteService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private FavoriteService favoriteService;
	
	// 관심지역 리스트
	@GetMapping("/list")
	public ResponseEntity<List<FavoriteDto>> list(@RequestParam("user_id") String user_id) throws Exception {
		return new ResponseEntity<List<FavoriteDto>>(favoriteService.getFavoriteList(user_id), HttpStatus.OK);
	}
	
	// 관심지역 등록
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody FavoriteDto favoriteDto) throws Exception {
		logger.debug("register Favorite - 호출");
		if (favoriteService.registerFavorite(favoriteDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	// 관심지역 등록되어있는지 체크
	@GetMapping("/check")
	public String favCheck(@RequestParam("user_id") String user_id, @RequestParam("sidoCode") String sidoCode, @RequestParam("gugunCode") String gugunCode) throws Exception {
		logger.debug("fav check - 호출");
		int favCount = favoriteService.favCheck(user_id, sidoCode, gugunCode);
		JSONObject json = new JSONObject();
		json.put("favcount", favCount);
		return json.toString();
	}
	
	@ApiOperation(value="관심지역 해제")
	@DeleteMapping()
	public ResponseEntity<String> deleteFav(@RequestParam("user_id") String user_id, @RequestParam("sidoCode") String sidoCode, @RequestParam("gugunCode") String gugunCode) throws Exception {
		logger.debug("delete Fav - 호출");
		if (favoriteService.deleteFav(user_id, sidoCode, gugunCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
