package by.epam.naumovich.task4.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="news")
@XmlAccessorType(XmlAccessType.FIELD)
public class News {
	
	@XmlAttribute(required = true)
	private String id;
	@XmlElement(name = "news-name", required = true)
	private String newsName;
	@XmlElement(required = true)
	private String provider;
	@XmlElement(name = "date-of-issue", required = true)
	private String dateOfIssue;
	@XmlElement(name = "news-body", required = true)
	private String newsBody;
	
	public News() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getNewsName() {
		return newsName;
	}

	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getNewsBody() {
		return newsBody;
	}

	public void setNewsBody(String newsBody) {
		this.newsBody = newsBody;
	}

	@Override
	public int hashCode() {
		int hash = 3;
	    hash = 7 * hash + (this.id != null ? this.id.hashCode() : 0);
	    hash = 11 * hash + (this.newsName != null ? this.newsName.hashCode() : 0);
	    hash = 13 * hash + (this.provider != null ? this.provider.hashCode() : 0);
	    hash = 17 * hash + (this.dateOfIssue != null ? this.dateOfIssue.hashCode() : 0);
	    hash = 19 * hash + (this.newsBody != null ? this.newsBody.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		News news = (News)obj;
		
		if ((this.id == null) ? (news.id != null) : !this.id.equals(news.id)) {
			return false;
		}
		if ((this.newsName == null) ? (news.newsName != null) : !this.newsName.equals(news.newsName)) {
			return false;
		}
		if ((this.provider == null) ? (news.provider != null) : !this.provider.equals(news.provider)) {
			return false;
		}
		if ((this.dateOfIssue == null) ? (news.dateOfIssue != null) : !this.dateOfIssue.equals(news.dateOfIssue)) {
			return false;
		}
		if ((this.newsBody == null) ? (news.newsBody != null) : !this.newsBody.equals(news.newsBody)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" ID: " + id);
	    result.append(", News Name: " + newsName);
	    result.append(", Prodiver: " + provider);
	    result.append(", Date Of Issue: " + dateOfIssue);
	    result.append(", News Body: " + newsBody);
	    result.append("}");

		return result.toString();
	}

	
}
