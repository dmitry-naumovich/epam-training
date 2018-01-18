package by.epam.naumovich.task4.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "news-catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsCatalog {

	@XmlElement(name = "category", type = Category.class)
	private List<Category> categories = new ArrayList<Category>();

	public NewsCatalog() {
		
	}
	
	public NewsCatalog(List<Category> categories) {
		this.categories = categories;
	}
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		int hash = 29;
	    hash = 13 * hash + (this.categories != null ? this.categories.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		NewsCatalog cat = (NewsCatalog)obj;
		if ((this.categories == null) ? (cat.categories != null) : !this.categories.equals(cat.categories)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    for (Category category : categories) {
	    	result.append("\n" + category);
	    }
	    result.append("}");

		return result.toString();
	}
	
}
