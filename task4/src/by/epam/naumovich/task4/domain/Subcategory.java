package by.epam.naumovich.task4.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subsubcategory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subcategory {

	@XmlAttribute(required = true)
	private String id;
	@XmlAttribute(required = true)
	private String name;
	@XmlElement(name = "news", type = News.class, required = true)
	private List<News> news = new ArrayList<News>();
	
	public Subcategory() {
		
	}
	
	public Subcategory(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public int hashCode() {
		int hash = 7;
	    hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
	    hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
	    hash = 31 * hash + (this.news != null ? this.news.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		Subcategory subcat = (Subcategory)obj;
		
		if ((this.id == null) ? (subcat.id != null) : !this.id.equals(subcat.id)) {
			return false;
		}
		if ((this.name == null) ? (subcat.name != null) : !this.name.equals(subcat.name)) {
			return false;
		}
		if ((this.news == null) ? (subcat.news != null) : !this.news.equals(subcat.news)) {
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
	    result.append(", News: ");
	    for (News n : news) {
	    	result.append("\n" + n);
	    }
	    result.append("}");

		return result.toString();
	}
	
	
}
