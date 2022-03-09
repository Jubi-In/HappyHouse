package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemoDto : 덧글정보", description = "덧글의 상세 정보를 나타낸다.")
public class MemoDto {
	@ApiModelProperty(value = "덧글번호")
	private int memono;
	@ApiModelProperty(value = "작성자 아이디")
	private String user_id;
	@ApiModelProperty(value = "덧글 내용")
	private String comment;
	@ApiModelProperty(value = "작성일")
	private String memotime;
	@ApiModelProperty(value = "어느게시글에달린 덧글인지")
	private int articleno;
	@ApiModelProperty(value = "작성자 닉네임")
	private String user_name;
	
	public int getMemono() {
		return memono;
	}
	public void setMemono(int memono) {
		this.memono = memono;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMemotime() {
		return memotime;
	}
	public void setMemotime(String memotime) {
		this.memotime = memotime;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
