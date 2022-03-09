package com.ssafy.happyhouse.model.controller;

import java.awt.print.Pageable;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/board")
@Api("게시판 컨트롤러  API V1")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/{whatType}")
	public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value="게시글 정보", required=true) BoardDto boardDto, @PathVariable("whatType") String whatType) throws Exception {
		logger.info("writeArticle - 호출");
		// QnA 게시판에 글쓰기
		if ("QnA".equals(whatType)) {
			if(boardService.writeQnA(boardDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		}
		// 공지사항에 게시판에 글쓰기
		else if ("Notice".equals(whatType)){
			if(boardService.writeNotice(boardDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/{whatType}")
	public ResponseEntity<List<BoardDto>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto, @PathVariable("whatType") String whatType) throws Exception {
		logger.info("listArticle - 호출");
		// QnA 게시판 리스트 출력
		if ("QnA".equals(whatType)) {
			return new ResponseEntity<List<BoardDto>>(boardService.listQnA(boardParameterDto), HttpStatus.OK);		
		}
		// 공지사항 게시판 리스트 출력
		else if ("Notice".equals(whatType)){
			//PageNavigation pageNavigation = boardService.makePageNavigation(boardParameterDto);
			return new ResponseEntity<List<BoardDto>>(boardService.listNotice(boardParameterDto), HttpStatus.OK);
		}
		return null;
	}
	
	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{articleno}/{whatType}")
	public ResponseEntity<BoardDto> getArticle(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno, @PathVariable("whatType") String whatType) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		if ("QnA".equals(whatType)) {
			boardService.updateQnAHit(articleno);
			return new ResponseEntity<BoardDto>(boardService.getQnA(articleno), HttpStatus.OK);
		}
		else if ("Notice".equals(whatType)){
			boardService.updateNoticeHit(articleno);
			return new ResponseEntity<BoardDto>(boardService.getNotice(articleno), HttpStatus.OK);
		}
		return null;
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}/{whatType}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @ApiParam(value = "삭제할 글의 글번호.", required = true) int articleno, @PathVariable("whatType") @ApiParam(value = "삭제할것이 Q&A 인지 Notice인지.", required = true) String whatType) throws Exception {
		logger.info("deleteArticle 호출 " + articleno + " " + whatType);
		if ("QnA".equals(whatType)) {
			if(boardService.deleteQnA(articleno)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		else if ("Notice".equals(whatType)){
			if(boardService.deleteNotice(articleno)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return null;
	}
	
	@ApiOperation(value = "게시판 글수정", notes = "새로운 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{whatType}")
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글의 글번호.", required = true) BoardDto boardDto, @PathVariable("whatType") @ApiParam(value = "수정할것이 Q&A 인지 Notice인지.", required = true) String whatType) throws Exception {
		logger.info("modifyArticle 호출 ");
		if ("QnA".equals(whatType)) {
			if(boardService.modifyQnA(boardDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		else if ("Notice".equals(whatType)){
			if(boardService.modifyNotice(boardDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return null;
	}
}
