package by.epam.naumovich.task83.domain;

import java.sql.Date;

public class News {

	private int id;
	private Date date;
	private String picture;
	private String title;
	private String text;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		int hash = 11;
	    hash = 7 * hash + this.id;
	    hash = 47 * hash + ((null != date) ? date.hashCode() : 0);
	    hash = 13 * hash + ((null != picture) ? picture.hashCode() : 0);
	    hash = 17 * hash + ((null != title) ? title.hashCode() : 0);
	    hash = 31 * hash + ((null != text) ? text.hashCode() : 0);
	    return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (obj.getClass() != getClass()) { return false; }
		
		News news = (News)obj;
		if (id != news.id) { return false; }
		if ((null == date) ? (news.date != null) : !date.equals(news.date)) {
			return false;
		}
		if ((null == picture) ? (news.picture != null) : !picture.equals(news.picture)) {
			return false;
		}
		if ((null == title) ? (news.title != null) : !title.equals(news.title)) {
			return false;
		}
		if ((null == text) ? (news.text != null) : !text.equals(news.text)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" ID: " + id);
	    result.append(", Date: " + date);
	    if (picture != null) { result.append(", Picture: " + picture); }
	    result.append(", Title: " + title);
	    result.append(", Text: " + text);
	    result.append("}");

		return result.toString();
	}
	
	
	
}
