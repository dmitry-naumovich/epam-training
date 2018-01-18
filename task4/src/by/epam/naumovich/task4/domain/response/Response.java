package by.epam.naumovich.task4.domain.response;

public abstract class Response {

	private String title;
	private String date;
	private boolean status;
	private String message;
	
	public abstract String getResponseType();
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		Response resp = (Response)obj;
		if (status != resp.status) { 
			return false;
		}
		if ((this.title == null) ? (resp.title != null) : !this.title.equals(resp.title)) {
			return false;
		}
		if ((this.date == null) ? (resp.date != null) : !this.date.equals(resp.date)) {
			return false;
		}
		if ((this.message == null) ? (resp.message != null) : !this.message.equals(resp.message)) {
			return false;
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
	    hash = 53 * hash + (this.title != null ? this.title.hashCode() : 0);
	    hash = 47 * hash + (this.date != null ? this.date.hashCode() : 0);
	    hash = 13 * hash + (this.message != null ? this.message.hashCode() : 0);
	    return hash;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" Response Type: " + getResponseType());
	    result.append(", Title: " + title);
	    result.append(", Date: " + date);
	    result.append(", Status " + status);
	    result.append(", Message: " + message);
	    result.append("}");

		return result.toString();
	}
	
}
