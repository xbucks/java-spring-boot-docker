package com.bonsai.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	public Image(String id, String title, String unsigned_title, String summary, String content, String link,
			String id_tree_type, String author_name) {
		this.id = id;
		this.title = title;
		this.unsigned_title = unsigned_title;
		this.summary = summary;
		this.content = content;
		this.link = link;
		this.id_tree_type = id_tree_type;
		this.author_name = author_name;
	}
	
	public Image(String id, String title, String unsigned_title, String summary, String content, String link,
			boolean highlights, Double views, String id_tree_type, String author_name) {
		this.id = id;
		this.title = title;
		this.unsigned_title = unsigned_title;
		this.summary = summary;
		this.content = content;
		this.link = link;
		this.highlights = highlights;
		this.views = views;
		this.id_tree_type = id_tree_type;
		this.author_name = author_name;
	}
	
	public Image() {}
	
	@Id
	private String id;

	private String title;

	private String unsigned_title;

	private String summary;

	private String content;

	private String link;

	private boolean highlights;

	private Double views;

	private String id_tree_type;

	private String author_name;

	private Date created_at;
	
	private Date last_modified;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUnsigned_title() {
		return unsigned_title;
	}

	public void setUnsigned_title(String unsigned_title) {
		this.unsigned_title = unsigned_title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isHighlights() {
		return highlights;
	}

	public void setHighlights(boolean highlights) {
		this.highlights = highlights;
	}

	public double getViews() {
		return views;
	}

	public void setViews(double views) {
		this.views = views;
	}

	public String getId_tree_type() {
		return id_tree_type;
	}

	public void setId_tree_type(String id_tree_type) {
		this.id_tree_type = id_tree_type;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
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
