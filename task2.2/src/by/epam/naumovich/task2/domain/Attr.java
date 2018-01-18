package by.epam.naumovich.task2.domain;

public class Attr implements Node {

	private String name;
	private String value;
	private Element parent;

	public Attr() {
		name = new String();
		value = new String();
		parent = new Element();
	}
	public Attr(String name, Element owner) {
		this.name = name;
		this.parent = owner;
		value = new String();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element owner) {
		this.parent = owner;
	}

	@Override
	public Node getParentNode() {
		return parent;
	}
	
	
}
