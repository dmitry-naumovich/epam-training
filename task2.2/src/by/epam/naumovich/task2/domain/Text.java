package by.epam.naumovich.task2.domain;

public class Text extends Document implements Node {

	private String txt;
	private Element element;
	
	public Text(String txt, Element element) {
		this.txt = txt;
		this.element = element;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	@Override
	public Node getParentNode() {
		return element;
	}
}
