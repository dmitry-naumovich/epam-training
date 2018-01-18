package by.epam.task3.domain;

public class MenuPosition { // JavaBean

	private int id;
	private String photoAddress;
	private String title;
	private String description;
	private String portion;
	private int price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhotoAddress() {
		return photoAddress;
	}
	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPortion() {
		return portion;
	}
	public void setPortion(String portion) {
		this.portion = portion;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		int hash = 53;
	    hash = 67 * hash + id;
	    hash = 13 * hash + (photoAddress != null ? photoAddress.hashCode() : 0);
	    hash = 11 * hash + (title != null ? title.hashCode() : 0);
	    hash = 7 * hash + (description != null ? description.hashCode() : 0);
	    hash = 3 * hash + (portion != null ? portion.hashCode() : 0);
	    hash = 67 * hash + price;
	    return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		MenuPosition pos = (MenuPosition)obj;
		if (id != pos.id) {
			return false;
		}
		if (price != pos.price) {
			return false;
		}
		
		if ((null == photoAddress) ? (null != pos.photoAddress) : (!photoAddress.equals(pos.photoAddress))) {
			return false;
		}
		if ((null == title) ? (null != pos.title) : (!title.equals(pos.title))) {
			return false;
		}
		if ((null == description) ? (null != pos.description) : (!description.equals(pos.description))) {
			return false;
		}
		if ((null == portion) ? (null != pos.portion) : (!portion.equals(pos.portion))) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" ID: " + id);
	    result.append(", Photo Address: " + photoAddress);
	    result.append(", Title: " + title);
	    result.append(", Description: " + description);
	    result.append(", Portion: " + portion);
	    result.append(", Price: " + price);
	    result.append("}");

		return result.toString();
	}
}
