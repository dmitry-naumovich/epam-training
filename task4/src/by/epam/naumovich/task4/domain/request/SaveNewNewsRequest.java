package by.epam.naumovich.task4.domain.request;

import by.epam.naumovich.task4.domain.Category;
import by.epam.naumovich.task4.domain.News;
import by.epam.naumovich.task4.domain.Subcategory;

public class SaveNewNewsRequest extends Request {

	private static final String commandName = "SAVE_NEW_NEWS";
	private News newsToSave;
	private Category category;
	private Subcategory subcategory;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public static String getCommandname() {
		return commandName;
	}

	public News getNewsToSave() {
		return newsToSave;
	}

	public void setNewsToSave(News newsToSave) {
		this.newsToSave = newsToSave;
	}

	@Override
	public String getCommandName() {
		return commandName;
	}
}
