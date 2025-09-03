import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from './models/user.model';
import { UserService } from './services/user.service';
import { EnrollmentService } from './services/enrollment.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  users: User[] = [];
  userForm: FormGroup;
  isEditing = false;
  selectedUserId: string | null = null;
  errorMessage: string | null = null;
  isLoading = false;

  constructor(
    private userService: UserService,
    private enrollmentService: EnrollmentService,
    private fb: FormBuilder
  ) {
    // Inizializza il form con FormBuilder per un controllo migliore
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
    this.loadUsers();
  }

  /**
   * Carica la lista degli utenti dal servizio.
   */
  loadUsers(): void {
    this.isLoading = true;
    this.errorMessage = null;
    this.userService.getAllUsers().subscribe({
      next: (data:any) => {
        this.users = data;
        this.isLoading = false;
      },
      error: (err:any) => {
        console.error('Errore nel caricamento degli utenti:', err);
        this.errorMessage = 'Impossibile caricare la lista degli utenti. Assicurati che il backend sia in esecuzione.';
        this.isLoading = false;
      }
    });
  }

  /**
   * Gestisce il submit del form.
   * Decide se creare un nuovo utente o aggiornarne uno esistente.
   */
  onSubmit(): void {
    if (this.userForm.invalid) {
      return; // Non fare nulla se il form non è valido
    }

    const user: User = {
      username: this.userForm.value.username,
      email: this.userForm.value.email
    };

    if (this.isEditing && this.selectedUserId) {
      // Modalità modifica
      this.userService.updateUser(this.selectedUserId, user).subscribe({
        next: () => {
          this.loadUsers(); // Ricarica la lista
          this.resetForm();
        },
        error: (err:any) => {
          console.error('Errore nell\'aggiornamento dell\'utente:', err);
          this.errorMessage = 'Errore durante l\'aggiornamento dell\'utente.';
        }
      });
    } else {
      // Modalità creazione
      this.userService.createUser(user).subscribe({
        next: () => {
          this.loadUsers(); // Ricarica la lista
          this.enrollmentService.notifyEnrollmentsDataUpdated(); // Notifica il cambiamento
          this.resetForm();
        },
        error: (err:any) => {
          console.error('Errore nella creazione dell\'utente:', err);
          this.errorMessage = 'Errore durante la creazione dell\'utente.';
        }
      });
    }
  }

  /**
   * Prepara il form per la modifica di un utente.
   * @param user L'utente da modificare.
   */
  editUser(user: User): void {
    this.isEditing = true;
    this.selectedUserId = user.id!;
    this.userForm.setValue({
      username: user.username,
      email: user.email
    });
    window.scrollTo(0, 0); // Scrolla in cima alla pagina per vedere il form
  }

  /**
   * Elimina un utente dopo conferma.
   * @param id L'ID dell'utente da eliminare.
   */
  deleteUser(id: string): void {
    if (confirm('Sei sicuro di voler eliminare questo utente?')) {
      this.userService.deleteUser(id).subscribe({
        next: () => {
          this.loadUsers(); // Ricarica la lista
          this.enrollmentService.notifyEnrollmentsDataUpdated(); // Notifica il cambiamento 
        },
        error: (err:any) => {
          console.error('Errore nell\'eliminazione dell\'utente:', err);
          this.errorMessage = 'Errore durante l\'eliminazione dell\'utente.';
        }
      });
    }
  }

  /**
   * Annulla l'operazione di modifica e resetta il form.
   */
  cancelEdit(): void {
    this.resetForm();
  }

  /**
   * Resetta lo stato del form.
   */
  private resetForm(): void {
    this.isEditing = false;
    this.selectedUserId = null;
    this.userForm.reset();
  }
}
