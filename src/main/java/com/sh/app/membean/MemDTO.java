package com.sh.app.membean;

public class MemDTO {
	private int idx;
	private String id;
	private String pw;
	private String email;
	private String name;
	private String phone;
	private String birth;
	private String regdate;
	private int level;
	
	public MemDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemDTO(String id, String pw, String email, String name, String phone, String birth) {
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	

}
