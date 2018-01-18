package by.epam.naumovich.task83.domain;

import java.sql.Date;

public class Order {

	private int ordNum;
	private int userId;
	private int filmId;
	private Date date;
	private float payment;
	
	public int getOrdNum() {
		return ordNum;
	}
	public void setOrdNum(int ordNum) {
		this.ordNum = ordNum;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public float getPayment() {
		return payment;
	}
	public void setPayment(float payment) {
		this.payment = payment;
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
	    hash = 7 * hash + this.ordNum;
	    hash = 13 * hash + this.userId;
	    hash = 31 * hash + this.filmId;
	    hash = 67 * hash + ((null != this.date) ? this.date.hashCode() : 0);
	    hash = 47 * hash + (int)this.payment;
	    return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (obj.getClass() != getClass()) { return false; }
		
		Order order = (Order)obj;
		if (ordNum != order.ordNum) { return false; }
		if (userId != order.userId) { return false; }
		if (filmId != order.filmId) { return false; }
		if (payment != order.payment) { return false; }
		if ((null == date) ? (order.date != null) : !date.equals(order.date)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" OrdNum: " + ordNum);
	    result.append(", UserID: " + userId);
	    result.append(", FilmID: " + filmId);
	    result.append(", Date: " + date);
	    result.append(", Payment: " + payment);
	    result.append("}");

		return result.toString();
	}
}
