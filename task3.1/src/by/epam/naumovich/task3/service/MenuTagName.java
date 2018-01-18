package by.epam.naumovich.task3.service;

public enum MenuTagName {
	PHOTO_ADDRESS, TITLE, DESCRIPTION, PORTION, PRICE, MENU_POSITION, MENU_ITEM, NS_MENU;
	
	public static MenuTagName getElementTagName(String tag) {
		switch(tag) {
		case "photo-address":
			return PHOTO_ADDRESS;
		case "title":
			return TITLE;
		case "description":
			return DESCRIPTION;
		case "portion":
			return PORTION;
		case "price":
			return PRICE;
		case "menu-position":
			return MENU_POSITION;
		case "menu-item":
			return MENU_ITEM;
		case "ns:menu": // for SAX Parser
			return NS_MENU;
		case "menu": // for StAX Parser
			return NS_MENU;
		default:
			throw new EnumConstantNotPresentException(MenuTagName.class, tag);
		}
	}

}
