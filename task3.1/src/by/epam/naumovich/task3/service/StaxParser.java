package by.epam.naumovich.task3.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.naumovich.task3.domain.MenuItem;
import by.epam.naumovich.task3.domain.MenuPosition;

public class StaxParser implements IParser {
	
	public static final String STAX_START_MESSAGE = "StAX Parser working now";
	public static final String ID = "id";
	public static final String ITEM_NAME = "item-name";
	private List<MenuItem> XMLList;
	
	@Override
	public void parse(String fileAddress) throws FileNotFoundException, FactoryConfigurationError {
		System.out.println(STAX_START_MESSAGE);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream(fileAddress);
			XMLStreamReader sReader = factory.createXMLStreamReader(input);
			XMLList = process(sReader);	
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MenuItem> getXMLList() {
		return XMLList;
	}
	
	private static List<MenuItem> process(XMLStreamReader reader) throws XMLStreamException {
		
		List<MenuItem> itemList = new ArrayList<MenuItem>();
		MenuItem menuItem = null;
		MenuPosition menuPosition = null;
		MenuTagName tagName = null;
		
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				tagName = MenuTagName.getElementTagName(reader.getLocalName());
				switch (tagName) {
				case MENU_ITEM:
					menuItem = new MenuItem();
					menuItem.setId(Integer.parseInt(reader.getAttributeValue(null, ID)));
					menuItem.setItemName(reader.getAttributeValue(null, ITEM_NAME));
					break;
				case MENU_POSITION:
					menuPosition = new MenuPosition();
					menuPosition.setId(Integer.parseInt(reader.getAttributeValue(null, ID)));
				default:
					break;
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) { break; }
				
				switch (tagName) {
				case PHOTO_ADDRESS:
					menuPosition.setPhotoAddress(text.toString());
					break;
				case TITLE:
					menuPosition.setTitle(text.toString());
					break;
				case DESCRIPTION:
					menuPosition.setDescription(text.toString());
					break;
				case PORTION:
					menuPosition.setPortion(text.toString());
					break;
				case PRICE:
					menuPosition.setPrice(Integer.parseInt(text.toString()));
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				tagName = MenuTagName.getElementTagName(reader.getLocalName());
				switch (tagName) {
				case MENU_POSITION:
					menuItem.addPosition(menuPosition);
					break;
				case MENU_ITEM:
					itemList.add(menuItem);
					break;
				default:
					break;
				}
			}
		}
		return itemList;
		
	}
}
