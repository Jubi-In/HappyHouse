package com.ssafy.happyhouse.model.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemoDto;
import com.ssafy.happyhouse.model.service.MemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/memo")
@Api("게시판 컨트롤러  API V1")
public class MemoController {
	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MemoService memoService;
	
	@ApiOperation(value = "덧글작성", notes = "새로운 덧글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/{whatType}")
	public ResponseEntity<String> writeMemo(@RequestBody @ApiParam(value="덧글 정보", required=true) MemoDto memoDto, @PathVariable("whatType") String whatType) throws Exception {
		logger.info("writeMemo 호출");
		if("QnA".equals(whatType)) {
			if(memoService.writeQnAMemo(memoDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		}
		else if ("Notice".equals(whatType)) {
			if(memoService.writeNoticeMemo(memoDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "덧글목록 가져오기", notes = "해당 articleno의 덧글을 반환한다.", response = List.class)
	@GetMapping("/{articleno}/{whatType}")
	public ResponseEntity<List<MemoDto>> listMemo(@PathVariable("articleno") @ApiParam(value="덧글을 얻기위한 articleno", required=true) int articleno, @PathVariable("whatType") String whatType) throws Exception {
		logger.info("listMemo 호출");
		if ("QnA".equals(whatType)) { 
			return new ResponseEntity<List<MemoDto>>(memoService.listQnAMemo(articleno), HttpStatus.OK);
		}
		else if ("Notice".equals(whatType)){
			return new ResponseEntity<List<MemoDto>>(memoService.listNoticeMemo(articleno), HttpStatus.OK);
		}
		return null;
	}
	
	@ApiOperation(value = "덧글 삭제", notes = "해당 memono에 해당하는 덧글을 삭제 후, 성공여부에 따라 success, fail 반환", response = List.class)
	@DeleteMapping("/{memono}/{whatType}")
	public ResponseEntity<String> deleteMemo(@PathVariable("memono") @ApiParam(value = "삭제할 덧글의 번호.", required = true) int memono, @PathVariable("whatType") @ApiParam(value = "삭제할것이 Q&A 인지 Notice인지.", required = true) String whatType) throws Exception {
		logger.info("deleteMemo 호출 + " + memono + whatType);
		if ("QnA".equals(whatType)) {
			if(memoService.deleteQnAMemo(memono)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		else if ("Notice".equals(whatType)){
			if(memoService.deleteNoticeMemo(memono)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return null;
	}
}
