package by.epam.naumovich.task2.DOMParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class DOMMenuParser {

	public static final String FILE_NAME = "src/resources/menuXML.xml";
	public static final String MENU_ITEM_TAG = "menu-item";
	public static final String ID_ATTRIBUTE = "id";
	public static final String ITEM_NAME_ATTRIBUTE = "item-name";
	public static final String MENU_POSITION_TAG = "menu-position";
	public static final String PHOTO_ADDRESS_TAG = "photo-address";
	public static final String TITLE_TAG = "title";
	public static final String DESCRIPTION_TAG = "description";
	public static final String PORTION_TAG = "portion";
	public static final String PRICE_TAG = "price";
	public static void main(String[] args) throws SAXException, IOException {
		
		DOMParser parser = new DOMParser();
		parser.parse(FILE_NAME);
		Document doc = parser.getDocument();
		Element root = doc.getDocumentElement();
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		NodeList menuItemNodes = root.getElementsByTagName(MENU_ITEM_TAG);
		MenuItem item = null;
		
		for (int i = 0; i < menuItemNodes.getLength(); i++) {
			item = new MenuItem();
			Element menuItemElement = (Element) menuItemNodes.item(i);
			item.setId(Integer.parseInt(menuItemElement.getAttribute(ID_ATTRIBUTE)));
			item.setItemName(menuItemElement.getAttribute(ITEM_NAME_ATTRIBUTE));
			
			List<MenuPosition> menuPositions = new ArrayList<MenuPosition>();
			NodeList menuPositionNodes = menuItemElement.getElementsByTagName(MENU_POSITION_TAG);
			MenuPosition pos = null;
			
			for (int j = 0; j < menuPositionNodes.getLength(); j++) {
				pos = new MenuPosition();
				Element menuPosElement = (Element) menuPositionNodes.item(j);
				pos.setId(Integer.parseInt(menuPosElement.getAttribute(ID_ATTRIBUTE)));
				
				pos.setPhotoAddress(getSingleChild(menuPosElement, PHOTO_ADDRESS_TAG).getTextContent());
				pos.setTitle(getSingleChild(menuPosElement, TITLE_TAG).getTextContent());
				pos.setDescription(getSingleChild(menuPosElement, DESCRIPTION_TAG).getTextContent());
				pos.setPortion(getSingleChild(menuPosElement, PORTION_TAG).getTextContent());
				pos.setPrice(Integer.parseInt(getSingleChild(menuPosElement, PRICE_TAG).getTextContent()));
				menuPositions.add(pos);
			}
			
			item.setPositions(menuPositions);
			menuItems.add(item);
			
		}
		
		for (MenuItem mItem : menuItems) {
			System.out.println(mItem);
		}
	}
	
	private static Element getSingleChild(Element element, String childName) {
		NodeList nList = element.getElementsByTagName(childName);
		Element child = (Element) nList.item(0);
		return child;
	}

}
