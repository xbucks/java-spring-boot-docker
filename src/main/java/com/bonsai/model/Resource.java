package com.bonsai.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "resource")
public class Resource {
	
	public Resource(int link_id, String link, String user_id, Date created_at, Date updated_at) {
		this.link_id = link_id;
		this.link = link;
		this.user_id = user_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Resource() {
		this.created_at = new Date();
		this.updated_at = null;
	}

	public int getLink_id() {
		return link_id;
	}

	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int link_id;
	private String link;
	private String user_id;
	private Date created_at;
	private Date updated_at;
}
