package by.epam.naumovich.task4.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

	@XmlAttribute(required = true)
	private String id;
	@XmlAttribute(required = true)
	private String name;
	@XmlElement(name = "subcategory", type = Subcategory.class, required = true)
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	
	public Category() {
		
	}
	
	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Subcategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int hash = 17;
	    hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
	    hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
	    hash = 17 * hash + (this.subcategories != null ? this.subcategories.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		Category cat = (Category)obj;
		
		if ((this.id == null) ? (cat.id != null) : !this.id.equals(cat.id)) {
			return false;
		}
		if ((this.name == null) ? (cat.name != null) : !this.name.equals(cat.name)) {
			return false;
		}
		if ((this.subcategories == null) ? (cat.subcategories != null) : !this.subcategories.equals(cat.subcategories)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" ID: " + id);
	    result.append(", Name: " + name);
	    result.append(", Subcategories:");
	    for (Subcategory s : subcategories) {
	    	result.append("\n" + s);
	    }
	    result.append("}");

		return result.toString();
	}
	
	
	
}
