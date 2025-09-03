import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = '/api/users';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  /**
   * Recupera tutti gli utenti dal backend.
   * @returns Un Observable contenente un array di User.
   */
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  /**
   * Recupera un singolo utente per ID.
   * @param id L'ID dell'utente da recuperare.
   * @returns Un Observable contenente l'utente trovato.
   */
  getUserById(id: string): Observable<User> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<User>(url);
  }

  /**
   * Crea un nuovo utente.
   * @param user L'oggetto User da creare.
   * @returns Un Observable contenente l'utente creato dal backend (con l'ID).
   */
  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user, this.httpOptions);
  }

  /**
   * Aggiorna un utente esistente.
   * @param id L'ID dell'utente da aggiornare.
   * @param user L'oggetto User con i dati aggiornati.
   * @returns Un Observable contenente l'utente aggiornato.
   */
  updateUser(id: string, user: User): Observable<User> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<User>(url, user, this.httpOptions);
  }

  /**
   * Elimina un utente.
   * @param id L'ID dell'utente da eliminare.
   * @returns Un Observable<void> perché il backend restituisce noContent.
   */
  deleteUser(id: string): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
