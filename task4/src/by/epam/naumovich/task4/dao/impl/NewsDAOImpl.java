package by.epam.naumovich.task4.dao.impl;

import javax.xml.bind.JAXBException;

import by.epam.naumovich.task4.dao.INewsDAO;
import by.epam.naumovich.task4.dao.exception.DAOException;
import by.epam.naumovich.task4.domain.NewsCatalog;
import by.epam.naumovich.task4.util.JAXBXMLHandler;

public class NewsDAOImpl implements INewsDAO {

	public static final String FILE_NAME = "src/resources/catalog.xml";
	
	@Override
	public void saveNews(NewsCatalog catalog) throws DAOException {
		try {
			JAXBXMLHandler.marshal(catalog, FILE_NAME);
		} catch (JAXBException e) {
			throw new DAOException("JAXBException happened", e);
		}
	}

	@Override
	public NewsCatalog getNewsCatalog() throws DAOException {
		NewsCatalog cat = null;
		try {
			cat = JAXBXMLHandler.unmarshal(FILE_NAME);
		} catch (JAXBException e) {
			throw new DAOException("Could not marshal", e);
		}
		return cat;
	}
}
