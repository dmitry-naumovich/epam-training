package port;

import java.util.List;

import warehouse.Container;
import warehouse.Warehouse;

public class Berth {

	private int id;
	private Warehouse portWarehouse;

	public Berth(int id, Warehouse warehouse) {
		this.id = id;
		portWarehouse = warehouse;
	}

	public int getId() {
		return id;
	}

	public boolean add(Warehouse shipWarehouse, int numberOfContainers) throws InterruptedException {
		boolean result = false;
		List<Container> containers = shipWarehouse.getContainer(numberOfContainers);
		synchronized (portWarehouse) {
			result = portWarehouse.addContainer(containers);
		}
		return result;
	}

	public boolean get(Warehouse shipWarehouse, int numberOfContainers) throws InterruptedException {
		boolean result = false;
		List<Container> containers = null;
		
		synchronized (portWarehouse) {
			containers = portWarehouse.getContainer(numberOfContainers);
		}

		if (containers != null) {
			shipWarehouse.addContainer(containers);
			result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (null == obj) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		Berth berth = (Berth)obj;
		if (id != berth.id) {
			return false;
		}
		if ((null == portWarehouse) ? (null != berth.portWarehouse) : (!portWarehouse.equals(berth.portWarehouse))) {
			return false;
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 13 + 17 * id;
	    hash = 29 * hash + (portWarehouse != null ? portWarehouse.hashCode() : 0);
	    return hash;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append(this.getClass().getSimpleName() + " Object {");
	    result.append("ID: " + id);
	    result.append(", portWarehouse: " + portWarehouse);
	    result.append("}");

		return result.toString();
	}
	
}
