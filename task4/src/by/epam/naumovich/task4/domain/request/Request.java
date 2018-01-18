package by.epam.naumovich.task4.domain.request;

public abstract class Request {

	private String title;
	private String date;
	
	public abstract String getCommandName();
	
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
	public int hashCode() {
		int hash = 7;
	    hash = 13 * hash + (this.title != null ? this.title.hashCode() : 0);
	    hash = 67 * hash + (this.date != null ? this.date.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		Request req = (Request)obj;
		
		if ((this.title == null) ? (req.title != null) : !this.title.equals(req.title)) {
			return false;
		}
		if ((this.date == null) ? (req.date != null) : !this.date.equals(req.date)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" Title: " + title);
	    result.append(", Date: " + date);
	    result.append(", CommandName: " + getCommandName());
	    result.append("}");

		return result.toString();
	}
	
	
	
}
