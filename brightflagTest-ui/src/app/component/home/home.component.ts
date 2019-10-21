import { Component, OnInit } from '@angular/core';
import { SubjectService } from '../../services/subject/subject.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public subjects;

  constructor(private subjectService : SubjectService) { }

  ngOnInit() {
    this.getSubjects();
  }

  getSubjects() {
    this.subjectService.getSubjects().subscribe (
      data => {this.subjects = data},
      err => console.error(err),
      () => console.log('subjects loaded')
    );
  }
}
