import { Evento } from './evento';

export interface EventoPage {
  eventos: Evento[];
  totalElements: number;
  totalPages?: number;
}
