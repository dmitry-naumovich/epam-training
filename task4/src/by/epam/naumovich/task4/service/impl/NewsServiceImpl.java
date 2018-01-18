package by.epam.naumovich.task4.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.task4.dao.DAOFactory;
import by.epam.naumovich.task4.dao.INewsDAO;
import by.epam.naumovich.task4.dao.exception.DAOException;
import by.epam.naumovich.task4.domain.Category;
import by.epam.naumovich.task4.domain.Criteria;
import by.epam.naumovich.task4.domain.News;
import by.epam.naumovich.task4.domain.NewsCatalog;
import by.epam.naumovich.task4.domain.Subcategory;
import by.epam.naumovich.task4.service.INewsService;
import by.epam.naumovich.task4.service.exception.ServiceException;

public class NewsServiceImpl implements INewsService {

	@Override
	public void saveNewNews(News news, Category cat, Subcategory subcat) throws ServiceException {
		// validation of input parameters
		if (news == null || cat == null || subcat == null) {
			throw new ServiceException("Null input parameter");
		}
		DAOFactory factory = DAOFactory.getInstance();
		INewsDAO newsDAO = factory.getNewsDAO();
		NewsCatalog catalog = getNewsCatalog();
		
		List<Category> categories = catalog.getCategories();
		
		for (Category category : categories) {
			if (category.equals(cat)) {
				List<Subcategory> subcategories = category.getSubcategories();
				for (Subcategory subcategory : subcategories) {
					if (subcategory.equals(subcat)) {
						subcategory.getNews().add(news);
					}
				}
			}
		}
		
		try {
			newsDAO.saveNews(catalog);
		} catch (DAOException e) {
			throw new ServiceException("DAOException catched", e);
		}
		
	}

	@Override
	public List<News> findNews(Criteria criteria, List<String> fieldsToSearchBy) throws ServiceException {
		if (criteria == null || fieldsToSearchBy == null || fieldsToSearchBy.isEmpty()) 
			throw new ServiceException("Null or empty input parameter", new Exception());
		
		List<News> foundNews = new ArrayList<News>();
		NewsCatalog catalog = new NewsCatalog();
		
		try {
			catalog = getNewsCatalog();
		} catch (ServiceException e1) {
			throw new ServiceException("Could not get catalog", e1);
		}
		List<Category> categories = catalog.getCategories();
		
		switch (criteria) {
		case BY_NAME:
			for (Category category : categories) {
				List<Subcategory> subcategories = category.getSubcategories();
				for (Subcategory subcategory : subcategories) {
						List<News> news = subcategory.getNews();
						for (News n : news) {
							if (n.getNewsName().equals(fieldsToSearchBy.get(0))) {
								foundNews.add(n);
							}
						}
					
				}
				
			}
			break;
		case BY_AUTHOR:
			
			for (Category category : categories) {
				List<Subcategory> subcategories = category.getSubcategories();
				for (Subcategory subcategory : subcategories) {
						List<News> news = subcategory.getNews();
						for (News n : news) {
							if (n.getProvider().equals(fieldsToSearchBy.get(0))) {
								foundNews.add(n);
							}
						}
					
				}
				
			}
			break;
			
		case BY_NAME_AND_AUTHOR:
			for (Category category : categories) {
				List<Subcategory> subcategories = category.getSubcategories();
				for (Subcategory subcategory : subcategories) {
						List<News> news = subcategory.getNews();
						for (News n : news) {
							if (n.getProvider().equals(fieldsToSearchBy.get(1)) && n.getNewsName().equals(fieldsToSearchBy.get(0))) {
								foundNews.add(n);
							}
								
						}
					
				}
				
			}
			break;
			
		case BY_CATEGORY:
			for (Category category : categories) {
				if (category.getName().equals(fieldsToSearchBy.get(0))) {
					List<Subcategory> subcategories = category.getSubcategories();
					for (Subcategory subcategory : subcategories) {
							List<News> news = subcategory.getNews();
							for (News n : news) {
									foundNews.add(n);
							}
					}
				}
			}
			break;
		case BY_SUBCATEGORY:
			for (Category category : categories) {
				List<Subcategory> subcategories = category.getSubcategories();
				for (Subcategory subcategory : subcategories) {
					if (subcategory.getName().equals(fieldsToSearchBy.get(0))) {
						List<News> news = subcategory.getNews();
						for (News n : news) {
								foundNews.add(n);
						}
					}
				}
			}
			break;
		}
		return foundNews;
	}

	@Override
	public NewsCatalog getNewsCatalog() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		INewsDAO newsDAO = factory.getNewsDAO();
		NewsCatalog catalog = null;
		try {
			catalog = newsDAO.getNewsCatalog();
		} catch (DAOException e) {
			throw new ServiceException("DAOException catched", e);
		}
		return catalog;
	}

	@Override
	public void createXMLWithStructure() throws ServiceException {
		NewsCatalog catalog = getStructure();
		DAOFactory factory = DAOFactory.getInstance();
		INewsDAO newsDAO = factory.getNewsDAO();
		
		try {
			newsDAO.saveNews(catalog);
		} catch (DAOException e) {
			throw new ServiceException("DAOException catched", e);
		}
		
	}
	
	private NewsCatalog getStructure() {
		NewsCatalog catalog = new NewsCatalog();
		
		Category musicCategory = new Category("0", "music");
		
		Subcategory newReleases = new Subcategory("0", "new-releases");
		Subcategory starGossip = new Subcategory("1", "star-gossip");
		Subcategory starDeaths = new Subcategory("2", "star-deaths");
		
		musicCategory.getSubcategories().add(newReleases);
		musicCategory.getSubcategories().add(starGossip);
		musicCategory.getSubcategories().add(starDeaths);
		
		Category movieCategory = new Category("1", "movies");
		
		Subcategory boxOffice = new Subcategory("0", "box-office");
		Subcategory newProjects = new Subcategory("1", "new-projects");
		Subcategory newTrailers = new Subcategory("2", "new-trailers");
		
		movieCategory.getSubcategories().add(boxOffice);
		movieCategory.getSubcategories().add(newProjects);
		movieCategory.getSubcategories().add(newTrailers);
	
		Category booksCategory = new Category("2", "books");
		
		Subcategory newBooks = new Subcategory("0", "new-books");
		Subcategory newReviews = new Subcategory("1", "new-reviews");
		Subcategory interestingFacts = new Subcategory("2", "interesting-facts");
		
		booksCategory.getSubcategories().add(newBooks);
		booksCategory.getSubcategories().add(newReviews);
		booksCategory.getSubcategories().add(interestingFacts);
		
		catalog.getCategories().add(musicCategory);
		catalog.getCategories().add(movieCategory);
		catalog.getCategories().add(booksCategory);
		
		return catalog;
	}
	
	

}
