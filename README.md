cassandra-trainings
==============

Summary
-------
Application Stub for the Cassandra Workshop. This is a simple Cassandra and Play Framework web application "HeapOverflow", which was inspired by http://stackoverflow.com.

HeapOverflow enables:
- register and manage your account
- ask any question
- rank questions
- answer questions
- vote questions 
- comment questions and answers

Question ranks are the key feature of the application:
- Recent - ordered descending by creation date
- Popular - ordered descending by number of votes
- Active - ordered descending by last modification date (time of last comment, answer etc.)
- Unanswered - list of recent questions without any answer
- Asked - list of questions created by the logged-in user, ordered descending by creation date
- Followed - list of questions followed-by the logged-in user, ordered descending by creation date

Implementation
--------------
The goal of this project is to build Cassandra model for all use cassess and next implement all required structures and access methods. The main parts which need to be implemented are:
- */app/dao/* - Cassandra DAOs
- */app/model/* - Cassandra model

The application contains three examples of different approach of data access:
- DataStax driver PreparedStatement - *dao.UserDAO*
- DataStax mapper - *dao.QuestionDAO*
- Spring Data Cassandra - *dao.spring.ActiveQuestionsDAO*

You can use the method which you prefere.

Running
-------
1. Install TypeSave activator https://www.playframework.com/download
2. Change working directory to the project root (cassandra-trainings)
3. Run: **activator**, and next **run**
4. Open http://localhost:9000/ with your browser.

IntelliJ IDEA setup
--------------------
1. Run: activator idea
2. Open the project with IDEA
3. Fix source roots:
   - app
   - conf
   - target/scala-2.10/src_managed/main
