package by.epam.naumovich.task4.domain.response;

import java.util.List;

import by.epam.naumovich.task4.domain.News;

public class FindNewsResponse extends Response {

	private static final String RESPONSE_TYPE = "findNews";
	private List<News> news;
	
	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public String getResponseType() {
		return RESPONSE_TYPE;
	}
	

}
