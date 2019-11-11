package com.bonsai.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tree_type")
public class TreeType {
	@Id
	public String id;
	
	public String id_type;
	
	public String name;
	
	public String unsigned_name;
	
	public Date created_at;
	
	public Date last_modified;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_type() {
		return id_type;
	}
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnsigned_name() {
		return unsigned_name;
	}
	public void setUnsigned_name(String unsigned_name) {
		this.unsigned_name = unsigned_name;
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
