import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class StudentService {

  constructor(private http:HttpClient) { }

  getStudents() {
    return this.http.get('/server/api/students/getStudents');
  }

  getStudentsBySubjectId(subjectID : number) {
    return this.http.get('/server/api/subjects/getAllStudentsForSubject/' + subjectID);
  }
}
