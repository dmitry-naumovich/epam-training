package by.epam.naumovich.task2.domain;

import java.util.ArrayList;
import java.util.List;

public class AttrList {

	private List<Attr> attributes;
	
	public AttrList() {
		attributes = new ArrayList<Attr>();
	}
	
	public void add(Attr attr) {
		attributes.add(attr);
	}
	
	public Attr get(String name) {
		for (Attr at: attributes){
			if (name.equals(at.getName())) {
				return at;
			}
		}
		return null;
	}
}
