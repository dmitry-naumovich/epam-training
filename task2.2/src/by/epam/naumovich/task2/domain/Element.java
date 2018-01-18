package by.epam.naumovich.task2.domain;

public class Element implements Node {

	private String name;
	private NodeList subnodeList;
	private AttrList attributes;
	private Element parent;
	
	public Element() {
		name = null;
		subnodeList = new NodeList();
		attributes = new AttrList();
	}
	
	public Element(String name, Element parent) {
		this();
		this.name = name;
		this.parent = parent;
	}
	
	public AttrList getAttributes() {
		return attributes;
	}
	
	public void setAttributes(AttrList attributes) {
		this.attributes = attributes;
	}
	
	public NodeList getSubnodeList() {
		return subnodeList;
	}

	public void setSubnodeList(NodeList subnodeList) {
		this.subnodeList = subnodeList;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Element getParent() {
		return parent;
	}
	
	public void setParent(Element parent) {
		this.parent = parent;
	}
	
	public NodeList getElementsByTagName(String tag) {
		NodeList list = new NodeList();
		for (int i = 0; i < subnodeList.getSize(); i++) {
			Node node = subnodeList.item(i);
			if (((Element)node).getName().equals(tag)) {
				list.add(node);
			}
		}
		return list;
	}
	
	public String getAttribute(String name) {
		return attributes.get(name).getValue();
	}
	
	public String getTextContent() {
		return subnodeList.getTextElement().getTxt();
	}
	@Override
	public Node getParentNode() {
		return parent;
	}

}
