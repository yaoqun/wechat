package com.wh.wechat;

/**
 * Created by Whiker on 2016/1/23.
 */
public class Command {

	private int    id;
	private String name;
	private String comment;
	private String content;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public String getContent() {
		return content;
	}
}
