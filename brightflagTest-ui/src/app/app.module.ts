import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentService } from './services/studentService/student.service';
import { SubjectService } from './services/subject/subject.service';
import { HomeComponent } from './component/home/home.component';
import { SubjectComponent } from './component/subject/subject.component';
import { ReactiveFormsModule} from '@angular/forms/';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SubjectComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [ StudentService, SubjectService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
