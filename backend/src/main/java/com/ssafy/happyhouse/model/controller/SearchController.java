package com.ssafy.happyhouse.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.service.SearchService;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
//	@GetMapping(value = "/listSearchWord")
//	public ResponseEntity<List<HouseInfoDto>> listSearchWord(@RequestParam("word") String word) throws Exception {
//		List<HouseInfoDto> list = searchService.listSearchWord(word);
//		if(list != null && !list.isEmpty()) {
//			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
//		} else {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//	}
	
	@GetMapping(value = "/listSearchWord")
	public ResponseEntity<List<String>> listSearchWord(@RequestParam("word") String word) throws Exception {
		List<String> list = searchService.listSearchWord(word);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/searchWithName/{word}")
	public ResponseEntity<List<HouseInfoDto>> searchWithName (@PathVariable("word") String word) throws Exception {
		System.out.println("service 들어가기 전에 " + word.toString());
		List<HouseInfoDto> list = searchService.searchWithName(word);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
}
