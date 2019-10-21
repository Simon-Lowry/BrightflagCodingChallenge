INSERT INTO student VALUES(1, 'Mary', 'Smith');
INSERT INTO student VALUES(2, 'Jose', 'Rodriguez');
INSERT INTO student VALUES(3, 'Paul', 'Smith');
INSERT INTO student VALUES(4, 'Mark', 'Taaffe');
INSERT INTO student VALUES(5, 'Tony', 'Albright');
INSERT INTO student VALUES(6, 'Sonja', 'Cullen');
INSERT INTO student VALUES(7, 'Nora', 'Cullen');

INSERT INTO subject VALUES(1, 'Classics');
INSERT INTO subject VALUES(2, 'Math');
INSERT INTO subject VALUES(3, 'English');
INSERT INTO subject VALUES(4, 'Spanish');
INSERT INTO subject VALUES(5, 'Databases');

INSERT INTO faculty VALUES(1, 'Humanities');
INSERT INTO faculty VALUES(2, 'Math');
INSERT INTO faculty VALUES(3, 'Engineering');

INSERT INTO subjectToStudent VALUES	(1, 1);
INSERT INTO subjectToStudent VALUES	(1, 4);
INSERT INTO subjectToStudent VALUES	(1, 5);
INSERT INTO subjectToStudent VALUES	(2, 1);
INSERT INTO subjectToStudent VALUES	(4, 1);


INSERT INTO grade VALUES(1, 'First Class Honours'); 
INSERT INTO gradeToStudent VALUES(1, 1, 1);
INSERT INTO grade VALUES(2, 'Second Class Honours'); 
INSERT INTO gradeToStudent VALUES(2, 1, 4);
INSERT INTO grade VALUES(3, 'Second Class Honours'); 
INSERT INTO gradeToStudent VALUES(3, 1, 5);

INSERT INTO grade VALUES(4, 'Second Class Honours'); 
INSERT INTO gradeToStudent VALUES(4, 2, 1);

INSERT INTO grade VALUES(5, 'Third Class Honours'); 
INSERT INTO gradeToStudent VALUES(5, 4, 1);


