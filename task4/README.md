# OOP Program (Catalog)
Создайте приложение, позволяющее хранить новости о выпускаемых фильмах, дисках, книгах. Исходные данные для приложения
хранятся в xml-файле. Структура xml-файла следующая:

- CATEGORY NAME
	- SUBCATEGORY NAME
		- NEWS NAME | PROVDER(AUTHOR, AUTHORS) | DATE OF ISSUE | NEWS BODY
	- SUBCATEGORY NAME
    	- NEWS NAME | PROVDER(AUTHOR, AUTHORS) | DATE OF ISSUE | NEWS BODY
- CATEGORY NAME
	- SUBCATEGORY NAME
		- NEWS NAME | PROVDER(AUTHOR, AUTHORS) | DATE OF ISSUE | NEWS BODY
	- SUBCATEGORY NAME
    	- NEWS NAME | PROVDER(AUTHOR, AUTHORS) | DATE OF ISSUE | NEWS BODY
    	  	
    	  	
Читаются, сохраняются данные из/в xml-файла с помощью JAXB. \
Реализуйте функциональность добавления новости и поиска новости по набору поисковых критериев. \
Помните про необходимость соблюдать Code Conventions, организовывать код согласно функциональности, правильно (и к 
месту) переопределять методы класса Object и прочее.