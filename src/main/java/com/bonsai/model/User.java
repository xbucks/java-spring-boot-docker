package com.bonsai.model;

import java.util.Date;

import com.bonsai.utils.UploadFileResponse;

public class User {
	private String id;
	private String name;
	private String email;
	private String password;
	private String remember_token;
	private Date created_at;
	private Date last_modified;
	private UploadFileResponse uploadFileResponse;

	public UploadFileResponse getUploadFileResponse() {
		return uploadFileResponse;
	}

	public void setUploadFileResponse(UploadFileResponse uploadFileResponse) {
		this.uploadFileResponse = uploadFileResponse;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemember_token() {
		return remember_token;
	}

	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}

}
