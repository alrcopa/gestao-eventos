import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConteinerEventosComponent } from './conteiners/conteiner-eventos.component';
import { EventosFormComponent } from './components/eventos-form/eventos-form.component';

const routes: Routes = [
  { path: '', component: ConteinerEventosComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventosRoutingModule { }
