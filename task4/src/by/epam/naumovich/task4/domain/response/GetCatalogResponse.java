package by.epam.naumovich.task4.domain.response;

import by.epam.naumovich.task4.domain.NewsCatalog;

public class GetCatalogResponse extends Response {

	private static final String RESPONSE_TYPE = "getCatalog";
	private NewsCatalog catalog;
	
	public NewsCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(NewsCatalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public String getResponseType() {
		return RESPONSE_TYPE;
	}

}
