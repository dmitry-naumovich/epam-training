package by.epam.naumovich.task4.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import by.epam.naumovich.task4.controller.Controller;
import by.epam.naumovich.task4.domain.Category;
import by.epam.naumovich.task4.domain.Criteria;
import by.epam.naumovich.task4.domain.News;
import by.epam.naumovich.task4.domain.NewsCatalog;
import by.epam.naumovich.task4.domain.Subcategory;
import by.epam.naumovich.task4.domain.request.CreateXMLWithStructureRequest;
import by.epam.naumovich.task4.domain.request.FindNewsRequest;
import by.epam.naumovich.task4.domain.request.GetCatalogRequest;
import by.epam.naumovich.task4.domain.request.SaveNewNewsRequest;
import by.epam.naumovich.task4.domain.response.FindNewsResponse;
import by.epam.naumovich.task4.domain.response.GetCatalogResponse;
import by.epam.naumovich.task4.domain.response.Response;

public class View {
	
	private static final int CREATE_XML_WITH_STRUCTURE = 0;
	private static final int GET_CATALOG = 1;
	private static final int SAVE_NEW_NEWS = 2;
	private static final int FIND_NEWS = 3;
	private static final int QUIT = 4;
	private NewsCatalog catalog;

	public void doUserAction() throws IOException {
		Controller controller = new Controller();
		printStartInfo();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		boolean whileCond = true;
		while (whileCond) {
			s = br.readLine();
			Integer whatAction = Integer.parseInt(s);
			
			switch (whatAction) {
			
			case CREATE_XML_WITH_STRUCTURE:
				CreateXMLWithStructureRequest createReq = new CreateXMLWithStructureRequest();
				createReq.setTitle("Create XML");
				createReq.setDate("01/06/2016");
				Response createResponse = controller.doAction(createReq);
				println("XML created");
				printResponse(createResponse);
				break;
				
			case GET_CATALOG:
				GetCatalogRequest getCatReq = new GetCatalogRequest();
				Response getCatResponse = controller.doAction(getCatReq);
				catalog = ((GetCatalogResponse)getCatResponse).getCatalog();
				println("Catalog received. Do you want to print it ? Type 'y' for yes, 'n' for no");
				s = br.readLine();
				switch (s) {
				case "y":
					printCatalog();
					break;
				case "n":
					break;
				default:
					println("Wrong choice");
					break;
				}
				break;
				
			case SAVE_NEW_NEWS:
				GetCatalogRequest getCatReq1 = new GetCatalogRequest();
				Response getCatResponse1 = controller.doAction(getCatReq1);
				catalog = ((GetCatalogResponse)getCatResponse1).getCatalog();
				SaveNewNewsRequest request = new SaveNewNewsRequest();
				request.setTitle("New News");
				List<Category> cats = catalog.getCategories();
				
				println("Choose category 0 - " + (cats.size() - 1));
				s = br.readLine();
				List<Subcategory> subcats = cats.get(Integer.parseInt(s)).getSubcategories();
				request.setCategory(cats.get(Integer.parseInt(s)));
				
				println("Choose subcategory 0 - " + (subcats.size() - 1));
				s = br.readLine();
				int subcatId = Integer.parseInt(s);
				request.setSubcategory(subcats.get(subcatId));
				
				News news = new News();
				
				println("Set date of issue, template = \" Day/Month/Year \" ");
				s = br.readLine();
				news.setDateOfIssue(s);
				
				println("Set author's name and surname");
				s = br.readLine();
				news.setProvider(s);
				
				news.setId(String.valueOf(subcats.get(subcatId).getNews().size()));
				
				println("Set news' name");
				s = br.readLine();
				news.setNewsName(s);
				
				println("Set new's body");
				s = br.readLine();
				news.setNewsBody(s);
				
				request.setNewsToSave(news);
				Response saveResponse = controller.doAction(request);
				printResponse(saveResponse);
				println("News saved");
				break;
				
			case FIND_NEWS:
				GetCatalogRequest getCatReq2 = new GetCatalogRequest();
				Response getCatResponse2 = controller.doAction(getCatReq2);
				catalog = ((GetCatalogResponse)getCatResponse2).getCatalog();
				FindNewsRequest findReq = new FindNewsRequest();
				findReq.setTitle("Find News");
				findReq.setDate("01/06/2016");
				printOptionsForSearch();
				s = br.readLine();
				Criteria criteria = null;
				List<String> fieldsSearchBy = new ArrayList<String>();
				
				switch (Integer.parseInt(s)) {
				case 0:
					criteria = Criteria.BY_NAME;
					println("Enter news name:");
					s = br.readLine();
					fieldsSearchBy.add(s);
					break;
				case 1:
					criteria = Criteria.BY_AUTHOR;
					println("Enter author's name:");
					s = br.readLine();
					fieldsSearchBy.add(s);
					break;
				case 2:
					criteria = Criteria.BY_NAME_AND_AUTHOR;
					println("Enter news name:");
					s = br.readLine();
					fieldsSearchBy.add(s);
					println("Enter author's name:");
					s = br.readLine();
					fieldsSearchBy.add(s);
					break;
				case 3:
					criteria = Criteria.BY_CATEGORY;
					println("Enter category name:");
					s = br.readLine();
					fieldsSearchBy.add(s);
					break;
				case 4:
					criteria = Criteria.BY_SUBCATEGORY;
					println("Enter subcategory name:");
					s = br.readLine();
					fieldsSearchBy.add(s);
					break;
					
				}

				findReq.setFieldsSearchBy(fieldsSearchBy);
				findReq.setCriteria(criteria);
				Response findResponse = controller.doAction(findReq);
				println(((FindNewsResponse)findResponse).getNews().size() + " news found");
				List<News> newsFound = ((FindNewsResponse)findResponse).getNews();
				println(newsFound.toString());
				break;
				
			case QUIT:
				System.exit(0);
				whileCond = false;
				break;
			default:
				println("Wrong number! Again");	
			}
			println("What else?");
		}
		
	}
	
	public void printStartInfo() {
		println("Welcome to the news catalog. First enter what do you want to do:");
		println("0 - create XML File with structure");
		println("1 - return catalog");
		println("2 - save new news");
		println("3 - find news");
		println("4 - quit");
	}
	
	public void printOptionsForSearch() {
		println("Choose the criteria to find new by:");
		println("0 - by name");
		println("1 - by author");
		println("2 - by name and author");
		println("3 - by category");
		println("4 - by subcategory");
	}
	public void printResponse(Response response) {
		println(response.toString());
	}
	
	public void printCatalog() {
		println(catalog.toString());
	}
	
	public void println(String s) {
		System.out.println(s);
	}
	
	public void print(String s) {
		System.out.print(s);
	}
}
