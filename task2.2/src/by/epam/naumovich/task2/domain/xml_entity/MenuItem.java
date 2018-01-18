package by.epam.naumovich.task2.domain.xml_entity;

import java.util.List;

public class MenuItem { // JavaBean

	private int id;
	private String itemName;
	private List<MenuPosition> positions;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public List<MenuPosition> getPositions() {
		return positions;
	}
	public void setPositions(List<MenuPosition> positions) {
		this.positions = positions;
	}

	@Override
	public int hashCode() {
		int hash = 13;
	    hash = 11 * hash + id;
	    hash = 31 * hash + (itemName != null ? itemName.hashCode() : 0);
	    hash = 47 * hash + (positions != null ? positions.hashCode() : 0);
	    return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		MenuItem item = (MenuItem)obj;
		if (id != item.id) {
			return false;
		}
		if ((null == itemName) ? (null != item.itemName) : (!itemName.equals(item.itemName))) {
			return false;
		}
		if ((null == positions) ? (null != item.positions) : (!positions.equals(item.positions))) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append(" ID: " + id);
	    result.append(", itemName: " + itemName);
	    result.append(", Positions: ");
	    for (MenuPosition mPos : positions) {
	    	result.append("\n" + mPos);
	    }
	    result.append("}");

		return result.toString();
	}
}
