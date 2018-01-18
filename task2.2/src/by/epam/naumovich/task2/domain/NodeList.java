package by.epam.naumovich.task2.domain;

import java.util.ArrayList;
import java.util.List;

public class NodeList {

	private List<Node> nodes;
	
	public NodeList() {
		nodes = new ArrayList<Node>();
	}
	
	public Node item(int index) {
		return nodes.get(index);
	}
	
	public void add(Node n) {
		nodes.add(n);
	}
	
	public int getSize() {
		return nodes.size();
	}
	
	public Text getTextElement() {
		for (Node n: nodes){			
			if (n.getClass().getSimpleName().equals("Text")) {
				return (Text)n;
			}
		}
		return null;
	}
}
