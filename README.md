# CommentAppFinal
--------------------------------------------
		Requirements:
---------------------------------------------

Intellij IDEA 2021.2.3 (Ultimate Edition)
Java 8
MySQL WorkBench 8.0
Tomcat 9
Maven

------------------------------------------
		Table Creation in MYSQL
------------------------------------------

Table Name : comments_data

Columns: 
username - Varchar(50)
comments-Varchar(250)
timestamps-BIGINT(20)

Table Name: user_credentials

Columns:
username - Varchar(50)
password - Varchar(100)
secretcode - Varchar(50)


--------------------------------------------------------
		Execution Screenshot
-----------------------------------------------------

Execution Screenshot document attached in the folder Execution Screenshot ( CommentApp\ExecutionScreenshot)


-------------------------------------------
		Execution Steps
-------------------------------------------

1. Open the Project in Intellij
2. Open "pom.xml" and Run for dependency installation
3. In \CommentApp\src\main\webapp path - JSP files are created. Filename with index.jsp is the Starting file of the project
4. Open index.jsp and Go to RUn->Run (or) Alt+Shift+F10. From run dialog, select Tomcat 9.
5. Once deployment completed, website will be opened in the browser displaying Sign In page.


-----------------------------------------------
		Servlet Files
----------------------------------------------

All the Servlet Files are created in the package "com.vishnu.commentapp" (src/main/java/com/vishnu/commentapp)

Database Codes are in the packages CommentDatabase

Other files such as Password encryption, constants, Validation are createed in "extras" package.
