package com.ssafy.happyhouse.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private UserService userService;
	
	// 2021.11.18 한길 수정
	// 회원정보를 전부 입력한 뒤 로그인 버튼을 눌렀을 때 토큰을 발행해서 return한다.
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println("Post - login 까지 왔다.");
		try {
			System.out.println("try 진입, " + userDto.toString());
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				System.out.println("loginUser 를 잘 얻어왔다! 토큰 얻어올 차례");
				String token = jwtService.create("userid", loginUser.getUser_id(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				System.out.println("loginUser 를 얻어오지 못했다.");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 2021.11.18 한길 추가
	// 처음에 받은 토큰을 decoded 된 상태로 받아와서 그걸 토대로 회원정보 전체를 반환.
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{user_id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("user_id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String user_id,
			HttpServletRequest request) {
		logger.debug("user_id : {} ", user_id);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(user_id);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 2021.11.19 한길 수정
	// 회원정보를 전부 입력한 뒤 회원가입 버튼을 눌렀을 때
	@ApiOperation(value = "회원가입", notes = "회원정보를 입력하고 form에 입력된 내용을 갖다가 회원등록시킴, 성공하면 success, 실패하면 fail 반환", response = Map.class)
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDto userDto) throws Exception {
		logger.debug("registerMember - 호출");
		if (userService.registerMember(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	// 2021.11.21 한길 수정
	// 내 정보 수정 창에서 회원탈퇴를 눌렀을 때
	@ApiOperation(value = "회원탈퇴", notes = "성공시 success, 실패시 fail 반환", response = String.class)
	@DeleteMapping("{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable("user_id") String user_id) throws Exception {
		logger.debug("deleteUser - 호출");
		System.out.println("deleteUser 안에서 user_id 는 = " + user_id);
		if (userService.deleteUser(user_id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	// 2021.11.23 한길 추가
	// 회원가입할때 유효한 아이디인지 체크할때
	@ApiOperation(value = "아이디유효한지 검사", notes = "유효하면 valid, 유효하지않으면 invalid 반환", response = Integer.class)
	@GetMapping("/idcheck/{user_id}")
	public ResponseEntity<String> checkIdVaild(@PathVariable String user_id) throws Exception {
		logger.debug("checkIdValid - 호출 " + user_id);
		int idCount = userService.idCheck(user_id);
		if(idCount == 0) { // 사용 가능한거
			return new ResponseEntity<String>("valid", HttpStatus.OK);
		} else { // 사용 불가능한거
			return new ResponseEntity<String>("invalid", HttpStatus.OK);
		}
	}
	
	// 2021.11.24 한길 추가
	// 회원정보 수정
	@ApiOperation(value = "회원정보 수정", notes = "회원정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody @ApiParam(value="수정할회원정보", required=true) UserDto userDto) throws Exception {
		logger.info("updateUser - 호출" + userDto.toString());
		if(userService.updateUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
