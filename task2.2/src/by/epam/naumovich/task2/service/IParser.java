package by.epam.naumovich.task2.service;

import by.epam.naumovich.task2.domain.Document;

public interface IParser {

	void parse(String fileAddress);
	Document getDocument();
}
