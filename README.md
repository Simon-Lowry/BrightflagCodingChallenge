# BrightflagCodingChallenge

# Brightflag Code Test - Getting Started & Questions

### Running the application
1. Run src/main/com/brightflag/codetest/Application.java as a Spring Boot app
2. Open [http://localhost:8080/](http://localhost:8080/)

## Application requirements
1. Students take multiple subjects and subjects have multiple students taking them
2. Students take multiple exams
3. Students receive grades for each of their exams
4. Add the functionality to the front-end to assign a student to a new subject
5. Create a unit test for Q4

### Extra information
1. Database schema: src/main/resources/schema.sql
2. Database data: src/main/resources/data.sql


### My approach to the Test
I decided to expand on the requirements spec and instead of having a single angular and html page, I created a separate angular project for the front end. The front end makes use of services and a proxy to interact with the backend project. There's routing in place for navigating.

The front end project can be ran with the following command:
ng serve --proxy-config proxy.conf.json

It's run on http://localhost:4200. 

The home page is a list of subjects which are links to individual subject pages. On each of these subject pages is a table which shows all of the students enrolled in that subject as well as their respective grades. There is also functionality to be able to enroll a student in a class which has error checking, data sanitization and response messages to show whether the action was successful or not. The table is updated without requiring a refresh.

I added a couple of extra table to faciliate the requirements of matching subjects to students as well as grades to student by subject. These linking tables make use of composite primary keys which are made up of foreign keys from their respective tables. There are also sample entries in the sql script for these tables for testing purposes.

I added some plugins in maven. One for the logging another for Jacoco. The Jacoco plug-in produce test reports showing which tests have passed and to show percentage of line coverage and branches coverage. The logging is used throughout the project.

Given more time I would have:
- Completed functionality for adding grades for a student
- Added ability to have multiple exams which would sum up to a grade and store exams in a separate table
- Created a student page which would be a students profile which would have their details and subjects they are enrolled for (some of the code is in place for this, didn't get to finish it).
- Styling and images for the front end (chose to prioritize functionality of appearence).
- Increased test coverage across the whole project
	- Unit Tests for all classes on backend
	- Integration for all classes on backend
	- karma testing for angular frontend
- More Logging

Note:
I had some confusion with the requirements and in a real life situation I would have posed the following questions to clear up the ambiguity:
Does a student take multiple exams for each subject? If not, how is the grade  object different from the exam object as the code is almost identical?
