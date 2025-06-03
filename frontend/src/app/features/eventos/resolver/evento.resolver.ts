import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { EventosService } from '../services/eventos.service';
import { Evento } from '../modal/evento';


@Injectable({
  providedIn: 'root'
})
export class EventoResolver  {
  constructor(private service: EventosService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Evento> {
    if (route.params && route.params['id']) {
      return this.service.loadById(route.params['id']);
    }

    return of({ _id: 0, title: '', description: '', eventDate: '', location: '' });
  }
}
