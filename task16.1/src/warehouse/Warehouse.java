package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
	
	private List<Container> containerList;
	private int size;

	public Warehouse(int size) {
		containerList = new ArrayList<Container>(size);
		this.size = size;
	}

	public boolean addContainer(Container container) {	
		return containerList.add(container);
	}

	public boolean addContainer(List<Container> containers) {
		boolean result = false;
		if(containerList.size() + containers.size() <= size){
			result = containerList.addAll(containers);
		}
		return result;
	}

	public Container getContainer() {
		synchronized (containerList) {
			if (!containerList.isEmpty()) {
				return containerList.remove(0);
			}
		}
		
		return null;
	}

	public List<Container> getContainer(int amount) {
		synchronized (containerList) {
			if (containerList.size() >= amount && !containerList.isEmpty()) {			
				List<Container> cargo = new ArrayList<Container>(containerList.subList(0, amount));
				containerList.removeAll(cargo);
				return cargo;
			}
		}
		return null;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getRealSize(){
		return containerList.size();
	}
	
	public int getFreeSize(){
		return size - containerList.size();
	}
}
