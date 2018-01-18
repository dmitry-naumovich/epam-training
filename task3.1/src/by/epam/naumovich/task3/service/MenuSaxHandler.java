package by.epam.naumovich.task3.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.naumovich.task3.domain.MenuItem;
import by.epam.naumovich.task3.domain.MenuPosition;

public class MenuSaxHandler extends DefaultHandler {
	
	public static final char HYPHEN = '-';
	public static final char UNDERSCORE = '_';
	public static final char COLON = ':';
	public static final String ID = "id";
	public static final String ITEM_NAME = "item-name";
	public static final String SAX_START_MESSAGE = "SAX Parsing has started";
	public static final String SAX_END_MESSAGE = "SAX Parsing has ended";
	public static final String MENU_ITEM_TAG = "menu-item";
	public static final String MENU_POSITION_TAG = "menu-position";
	
	private List<MenuItem> itemList = new ArrayList<MenuItem>();
	private MenuItem menuItem;
	private MenuPosition menuPosition;
	private StringBuilder text;
	
	public List<MenuItem> getItemList() {
		return itemList;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println(SAX_START_MESSAGE);
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println(SAX_END_MESSAGE);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("startElement -> uri = " + uri + ", localName = " + localName + ", qName = " + qName);
		text = new StringBuilder();
		if (qName.equals(MENU_ITEM_TAG)) {
			menuItem = new MenuItem();
			menuItem.setId(Integer.parseInt(attributes.getValue(ID)));
			menuItem.setItemName(attributes.getValue(ITEM_NAME));
		}
		if (qName.equals(MENU_POSITION_TAG)) {
			menuPosition = new MenuPosition();
			menuPosition.setId(Integer.parseInt(attributes.getValue(ID)));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE).replace(COLON, UNDERSCORE));
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
		case MENU_POSITION:
			menuItem.addPosition(menuPosition);
			menuPosition = null;
			break;
		case MENU_ITEM:
			itemList.add(menuItem);
			menuItem = null;
			break;
		default:
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		text.append(ch, start, length);
	}	
}
