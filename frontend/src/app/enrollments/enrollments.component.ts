import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { Course } from '../models/course.model';
import { UserService } from '../services/user.service';
import { CourseService } from '../services/course.service';
import { EnrollmentService } from '../services/enrollment.service';
import { Enrollment } from '../models/enrollment.model';
import { EnrollmentView } from '../models/enrollment-view.model';
import { NgForm } from '@angular/forms';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-enrollments',
  templateUrl: './enrollments.component.html',
  styleUrls: ['./enrollments.component.css']
})
export class EnrollmentsComponent implements OnInit {
  users: User[] = [];
  courses: Course[] = [];
  enrollments: EnrollmentView[] = [];
  selectedEnrollment: EnrollmentView | null = null;
  selectedUserId: string = '';
  selectedCourseId: string = '';
  errorMessage: string | null = null;

  constructor(
    private userService: UserService,
    private courseService: CourseService,
    private enrollmentService: EnrollmentService
  ) { }

  ngOnInit(): void {
    this.loadUsers();
    this.loadCourses();
    this.loadInitialData();
    this.enrollmentService.enrollmentsDataUpdated$.subscribe(() => {
      this.loadInitialData();
    });
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe(data => this.users = data);
  }

  loadCourses(): void {
    this.courseService.getCourses().subscribe(data => this.courses = data);
  }

  loadInitialData(): void {

    forkJoin({
      users: this.userService.getAllUsers(),
      courses: this.courseService.getCourses(),
      enrollments: this.enrollmentService.getEnrollments()
    }).subscribe(({ users, courses, enrollments }) => {
      this.users = users;
      this.courses = courses;
      this.buildEnrollmentView(enrollments, users, courses);
    });
  }

  // Costruisce la vista arricchita per la tabella delle iscrizioni
  buildEnrollmentView(enrollments: Enrollment[], users: User[], courses: Course[]): void {
    this.enrollments = enrollments.map(enrollment => {
      const user = users.find(u => u.id === enrollment.userId);
      const course = courses.find(c => c.id === enrollment.courseId);
      return {
        id: enrollment.id,
        username: user ? user.username : 'N/A',
        courseTitle: course ? course.title : 'N/A'
      };
    });
  }

  enrollUser(form: NgForm): void {
    if (form.invalid) {
      return;
    }
    const enrollment = {
      userId: this.selectedUserId,
      courseId: this.selectedCourseId
    };
    this.enrollmentService.enrollUser(enrollment).subscribe(() => {
      alert('Utente iscritto con successo!');
      form.resetForm();
      this.loadInitialData(); // Ricarica i dati per aggiornare la tabella
    });
  }

  selectEnrollment(enrollment: EnrollmentView): void {
    this.selectedEnrollment = { ...enrollment }; // Crea una copia per l'editing
  }

  deleteEnrollment(id: string): void {
    if (confirm('Sei sicuro di voler eliminare questa iscrizione?')) {
      this.enrollmentService.deleteEnrollment(id).subscribe({
        next: () => {
          this.loadInitialData(); // Ricarica la lista
        },
        error: (err:any) => {
          console.error('Errore nell\'eliminazione dell\'iscrizione:', err);
          this.errorMessage = 'Errore durante l\'eliminazione dell\'iscrizione.';
        }
      });
    }
  }

}
