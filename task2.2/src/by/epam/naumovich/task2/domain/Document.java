package by.epam.naumovich.task2.domain;

public class Document implements Node {

	private Element documentElement;
	
	public Element getDocumentElement() {
		return documentElement;
	}

	public void setDocumentElement(Element documentElement) {
		this.documentElement = documentElement;
	}

	@Override
	public Node getParentNode() {
		return documentElement;
	}
	
	
	
	
}
