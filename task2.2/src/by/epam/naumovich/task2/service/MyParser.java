package by.epam.naumovich.task2.service;

import java.io.FileInputStream;
import java.io.IOException;

import by.epam.naumovich.task2.domain.Attr;
import by.epam.naumovich.task2.domain.AttrList;
import by.epam.naumovich.task2.domain.Document;
import by.epam.naumovich.task2.domain.Element;
import by.epam.naumovich.task2.domain.NodeList;
import by.epam.naumovich.task2.domain.Text;

public class MyParser implements IParser {

	private Document document;
	private static final String HEADER_START = "<?";
	private static final String HEADER_END = "?>";
	private static final String COMMENT_START = "<!--";
	private static final String COMMENT_END = "-->";
	private static final char OPENING_BRACKET = '<';
	private static final char CLOSING_BRACKET = '>';
	private static final char SPACE = ' ';
	private static final char SLASH = '/';
	private static final char EQUALS = '=';
	private static final String QUOTES = "\"";
	
	public MyParser() {
		document = new Document();
	}
	
	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	public void parse(String fileAddress) {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(fileAddress);
			byte[] bytes = new byte[stream.available()]; // array of file bytes
			stream.read(bytes);
			String wholeFile = new String(bytes); // whole document
			if (wholeFile.contains(HEADER_START)) { // delete header
				wholeFile = wholeFile.substring(0, wholeFile.indexOf(HEADER_START)) + wholeFile.substring(wholeFile.indexOf(HEADER_END) + HEADER_END.length());
			}
			while (wholeFile.contains(COMMENT_START)) { // delete comments
				wholeFile = wholeFile.substring(0, wholeFile.indexOf(COMMENT_START)) + wholeFile.substring(wholeFile.indexOf(COMMENT_END) + COMMENT_END.length());
			}
			NodeList allNodes = findNodes(null, wholeFile); // null is the parent of the first tag
			document.setDocumentElement((Element)allNodes.item(0));
			
		} catch (IOException ex) {
		  ex.printStackTrace();
		} finally {
		   try {
			   stream.close();
		   } catch (Exception ex) {
			   ex.printStackTrace();
		   }
		}
	}
	
	private NodeList findNodes(Element parentNode, String text) {
		NodeList nodes = new NodeList();
		int tagStart = text.indexOf(OPENING_BRACKET);
		int textSize = text.length();
		if (tagStart == -1) {
			nodes.add(new Text(text , parentNode));
		}
		while (tagStart != -1) { // while we can find opening tags
			int numOfOpenedTags = 0;
			char[] chars = text.toCharArray();
			for (int i = tagStart; i < textSize - 1; i++) {
				if (chars[i] == OPENING_BRACKET) {
					if (chars[i + 1] == SLASH) {
						numOfOpenedTags--;
					}
					else {
						numOfOpenedTags++;
					}
				}
				if (numOfOpenedTags == 0) {
					int tagEnd = i;
					while (true) {
						if (chars[i] == CLOSING_BRACKET) {
							String elem = text.substring(tagStart, i + 1);
							String tag = elem.substring(elem.indexOf(OPENING_BRACKET) + 1, elem.indexOf(CLOSING_BRACKET));
							int quotesIndex = tag.indexOf(QUOTES);
							Element element;
							if (quotesIndex == -1) { // no attributes
								element = new Element(tag, parentNode);
							} 
							else { // let's set attributes
								element = new Element(tag.substring(0, tag.indexOf(SPACE)), parentNode);
								String stringToEnd = tag.substring(tag.indexOf(SPACE) + 1);
								AttrList attrs = new AttrList();
								int equals = stringToEnd.indexOf(EQUALS);
								while (equals != -1) {
									Attr attr = new Attr(stringToEnd.substring(0, equals).trim(), element);
									equals = stringToEnd.indexOf(QUOTES);
									if (equals == -1) {
										equals = stringToEnd.indexOf(SLASH);
									}
									stringToEnd = stringToEnd.substring(equals + 1);
									equals = stringToEnd.indexOf(QUOTES);
									if (equals == -1) {
										equals = stringToEnd.indexOf(SLASH);
									}
									attr.setValue(stringToEnd.substring(0, equals));
									attrs.add(attr);
									stringToEnd = stringToEnd.substring(equals + 1);
									equals = stringToEnd.indexOf(EQUALS);
								}
								element.setAttributes(attrs);
							}
							String elemBody = elem.substring(elem.indexOf(CLOSING_BRACKET) + 1, tagEnd - tagStart);
							element.setSubnodeList(findNodes(element, elemBody)); // recursion to find internal elems
							text = text.substring(i + 1); // removing proceeded part
							nodes.add(element);
							break;
						} 
						else {
							i++;
						}
					}
					break;
				}
			}
			tagStart = text.indexOf(OPENING_BRACKET); // searching for the next tag
		}
		return nodes;
	}
}
