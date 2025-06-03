import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ConteinerEventosComponent } from './conteiners/conteiner-eventos.component';
import { EventoResolver } from './resolver/evento.resolver';
import { EventosFormComponent } from './components/eventos-form/eventos-form.component';

/* eslint-disable prettier/prettier */
const routes: Routes = [
  { path: '', component: ConteinerEventosComponent },
  { path: '', component: ConteinerEventosComponent },
  { path: 'new', component: EventosFormComponent, resolve: { evento: EventoResolver } },
  { path: 'edit/:id', component: EventosFormComponent, resolve: { evento: EventoResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventosRoutingModule { }
