import { Component, OnInit } from '@angular/core';
import { Course } from '../models/course.model';
import { CourseService } from '../services/course.service';
import { EnrollmentService } from '../services/enrollment.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  courses: Course[] = [];
  selectedCourse: Course | null = null;
  isNewCourse = false;
  errorMessage: string | null = null;

  constructor(private courseService: CourseService, private enrollmentService: EnrollmentService) { }

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.courseService.getCourses().subscribe(data => {
      this.courses = data;
    });
  }

  selectCourse(course: Course): void {
    this.selectedCourse = { ...course }; // Crea una copia per l'editing
    this.isNewCourse = false;
  }

  addNewCourse(): void {
    this.selectedCourse = { title: '', description: '' };
    this.isNewCourse = true;
  }

    deleteCourse(id: string): void {
    if (confirm('Sei sicuro di voler eliminare questo corso?')) {
      this.courseService.deleteCourse(id).subscribe({
        next: () => {
          this.loadCourses(); // Ricarica la lista
          this.enrollmentService.notifyEnrollmentsDataUpdated(); // Notifica il cambiamento
        },
        error: (err:any) => {
          console.error('Errore nell\'eliminazione del corso:', err);
          this.errorMessage = 'Errore durante l\'eliminazione del corso.';
        }
      });
    }
  }

  saveCourse(form: NgForm): void {
    if (form.invalid || !this.selectedCourse) {
      return;
    }

    if (this.isNewCourse) {
      this.courseService.createCourse(this.selectedCourse).subscribe(() => {
        this.enrollmentService.notifyEnrollmentsDataUpdated(); // Notifica il cambiamento
        this.reset();
      });
    } else if (this.selectedCourse.id) {
      this.courseService.updateCourse(this.selectedCourse.id, this.selectedCourse).subscribe(() => {
        this.enrollmentService.notifyEnrollmentsDataUpdated(); // Notifica il cambiamento
        this.reset();
      });
    }
  }

  reset(): void {
    this.selectedCourse = null;
    this.isNewCourse = false;
    this.loadCourses();
  }
}