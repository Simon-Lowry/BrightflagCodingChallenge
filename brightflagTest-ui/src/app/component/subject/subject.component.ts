import { Component, OnInit } from '@angular/core';
import { StudentService } from '../../services/studentService/student.service';
import { SubjectService } from '../../services/subject/subject.service';
import { ActivatedRoute} from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {
  public students;
  public subject;
  public addStudentForm : FormGroup;
  public validMessage: string = "";
  public errorMessage: string = "";

  constructor(private studentService : StudentService, private subjectService : SubjectService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getSubject(this.route.snapshot.params.subjectID);
    this.getStudents(this.route.snapshot.params.subjectID);

    this.addStudentForm = new FormGroup ( {
      name: new FormControl ('', Validators.required),
      subjectID: new FormControl()
    } );
  }

  getStudents(subjectID : number) {
    this.studentService.getStudentsBySubjectId(subjectID).subscribe (
      data => {this.students = data},
      err => console.error(err),
      () => console.log('Students loaded')
    );
  }

  getSubject(subjectID : number) {
    this.subjectService.getSubjectBySubjectID(subjectID).subscribe (
      data => {this.subject = data},
      err => console.error(err),
      () => console.log('Subject data loaded')
    );
  }

  addStudentToClass() {
    if (this.addStudentForm.valid) {
      this.addStudentForm.controls['subjectID'].setValue(this.subject.subjectID);

      this.subjectService.addStudentToClass(this.addStudentForm.value).subscribe(
          data => {
            this.addStudentForm.reset();
            this.ngOnInit();
            this.validMessage = "Successfully added student to class";
            this.errorMessage = "";
          },
          error => {
            this.errorMessage = error.error.message;
            this.validMessage = "";
          }
        )
    } else {
      this.errorMessage = "Please fill out the form before submitting!";
      this.validMessage = "";
    }
  }
}
