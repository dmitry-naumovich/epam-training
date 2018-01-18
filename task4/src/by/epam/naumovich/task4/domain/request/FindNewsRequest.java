package by.epam.naumovich.task4.domain.request;

import java.util.List;

import by.epam.naumovich.task4.domain.Criteria;

public class FindNewsRequest extends Request {

	private static final String commandName = "FIND_NEWS";
	private Criteria criteria;
	private List<String> fieldsSearchBy;
	
	public List<String> getFieldsSearchBy() {
		return fieldsSearchBy;
	}

	public void setFieldsSearchBy(List<String> fieldsSearchBy) {
		this.fieldsSearchBy = fieldsSearchBy;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public String getCommandName() {
		return commandName;
	}
}
