package by.epam.naumovich.task83.domain;

import java.sql.Date;

public class Review {

	private int author;
	private int filmId;
	private Date date;
	private String type;
	private int mark;
	private String text;
	
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public int hashCode() {
		int hash = 47;
	    hash = 7 * hash + this.author;
	    hash = 11 * hash + this.filmId;
	    hash = 19 * hash + this.mark;
	    hash = 17 * hash + (this.date != null ? this.date.hashCode() : 0);
	    hash = 13 * hash + (this.type != null ? this.type.hashCode() : 0);
	    hash = 31 * hash + (this.text != null ? this.text.hashCode() : 0);
	    return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (obj.getClass() != getClass()) { return false; }
		
		Review rev = (Review)obj;
		if (author != rev.author) { return false; }
		if (filmId != rev.filmId) { return false; }
		if (mark != rev.mark) { return false; }
		
		if ((null == date) ? (rev.date != null) : !date.equals(rev.date)) {
			return false;
		}
		if ((null == type) ? (rev.type != null) : !type.equals(rev.type)) {
			return false;
		}
		if ((null == text) ? (rev.text != null) : !text.equals(rev.text)) {
			return false;
		}
		
		return true;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" Author: " + author);
	    result.append(", FilmId: " + filmId);
	    result.append(", Date: " + date);
	    result.append(", Type: " + type);
	    result.append(", Mark: " + mark);
	    result.append(", Text: " + text);
	    result.append("}");

		return result.toString();
	}
	
	
}
