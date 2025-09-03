import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Enrollment } from '../models/enrollment.model';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {
  
  private apiUrl = 'http://localhost:8080/enrollments';
  private _enrollmentsDataUpdated = new Subject<void>();
  enrollmentsDataUpdated$ = this._enrollmentsDataUpdated.asObservable();

  constructor(private http: HttpClient) { }

  enrollUser(enrollment: { userId: string, courseId: string }): Observable<Enrollment> {
    return this.http.post<Enrollment>(this.apiUrl, enrollment);
  }

   getEnrollments(): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(this.apiUrl);
  }

  deleteEnrollment(id: string) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  notifyEnrollmentsDataUpdated(): void {
    this._enrollmentsDataUpdated.next();
  }

}