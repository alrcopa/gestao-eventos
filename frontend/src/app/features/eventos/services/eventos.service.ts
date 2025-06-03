import { Injectable } from '@angular/core';
import { Evento } from '../modal/evento';
import { HttpClient } from '@angular/common/http';
import { EventoPage } from '../modal/evento-page';
import { first, map, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventosService {
  private readonly API = '/api/events';

  private cache: Evento[] = [];

  constructor(private http: HttpClient) {}

  // list() {
  //   return this.http.get<EventoPage>(this.API).pipe(
  //     first(),
  //     map(data => data.eventos),
  //     tap(data => (this.cache = data))
  //   );
  // }

  list(page = 0, pageSize = 10) {
    return this.http.get<EventoPage>(this.API, { params: { page, pageSize } }).pipe(
      first(),
      // map(data => data.courses),
      tap(data => (this.cache = data.eventos))
    );
  }

  loadById(id: number) {
    if (this.cache.length > 0) {
      const record = this.cache.find(evento => `${evento._id}` === `${id}`);
      return record != null ? of(record) : this.getById(id);
    }
    return this.getById(id);
  }

  private getById(id: number) {
    return this.http.get<Evento>(`${this.API}/${id}`).pipe(first());
  }

  save(record: Partial<Evento>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  private update(record: Partial<Evento>) {
    return this.http.put<Evento>(`${this.API}/${record._id}`, record).pipe(first());
  }

  private create(record: Partial<Evento>) {
    return this.http.post<Evento>(this.API, record).pipe(first());
  }

  remove(id: number) {
    return this.http.delete<Evento>(`${this.API}/${id}`).pipe(first());
  }
}
