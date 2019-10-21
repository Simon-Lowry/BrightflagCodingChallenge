import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class SubjectService {

  constructor(private http:HttpClient) { }

  getSubjects() {
    return this.http.get('/server/api/subjects/getAllSubjects');
  }

  getSubjectBySubjectID(subjectID : number){
    return this.http.get('/server/api/subjects/getSubject/' + subjectID);
  }

  addStudentToClass(addStudentFormValue) {
    let body = JSON.stringify(addStudentFormValue);
    console.log('body: ' + body);
    return this.http.post('/server/api/subjects/assignToSubject/', body, httpOptions);
  }
}
