import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { EventosService } from '../services/eventos.service';
import { EventoResolver } from './evento.resolver';
import { Evento } from '../modal/evento';


describe('EventoResolver', () => {
  let resolver: EventoResolver;
  let eventoServiceSpy: jasmine.SpyObj<EventosService>;

  beforeEach(() => {
    eventoServiceSpy = jasmine.createSpyObj('EventosService', ['loadById']);
    TestBed.configureTestingModule({
      providers: [{ provide: EventosService, useValue: eventoServiceSpy }]
    });
    resolver = TestBed.inject(EventoResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });

  it('should return evento', () => {
    const evento = {
      _id: '1',
      title: 'Event 1',
      description: 'Description 1'
    };
    eventoServiceSpy.loadById.and.returnValue(of(evento));
    const result = resolver.resolve({ params: { id: 1 } } as any, {} as any);
    result.subscribe((res: Evento) => expect(res).toEqual(evento));
  });

  it('should return empty evento if new', () => {
    const evento = { _id: '', title: '', description: '' };
    eventoServiceSpy.loadById.and.returnValue(of(evento));
    const result = resolver.resolve({ params: {} } as any, {} as any);
    result.subscribe((res: Evento) => expect(res).toEqual(evento));
  });
});
