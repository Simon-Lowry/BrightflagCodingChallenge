create table student (
   studentID integer auto_increment not null,
   firstName varchar(255) not null,
   lastName varchar(255) not null,
   primary key(studentID)
);

create table subject (
   subjectID integer auto_increment not null,
   subjectName varchar(255) not null,
   primary key(subjectID)
);

create table faculty (
   facultyID integer auto_increment not null,
   facultyName varchar(255) not null,
   primary key(facultyID)
);

create table subjectToStudent (
	studentID integer not null,
	subjectID integer not null,
	primary key (studentID, subjectID),
	foreign key (subjectID) references subject (subjectID),
	foreign key (studentID) references student (studentID)	
);

create table grade (
	gradeID integer auto_increment not null,
	gradeName varchar(255) not null,
	primary key(gradeID)
);

create table gradeToStudent (
	gradeID integer not null,
	studentID integer not null,
	subjectID integer not null,
	primary key(gradeID, studentID, gradeID ),
	foreign key (gradeID) references grade (gradeID),
	foreign key (subjectID) references subject (subjectID),
	foreign key (studentID) references student (studentID)
);


