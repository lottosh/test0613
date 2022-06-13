package com.sh.app.boardbean;

public class BoardDTO {
	private int idx;
	private String id;
	private String name;
	private String title;
	private String content;
	private String img;
	private String regdate;
	private int hit;
	
	// DTO에 주석달기입니다!!!
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoardDTO(String id, String name, String title, String content, String img) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.img = img;
	}



	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
