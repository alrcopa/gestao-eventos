import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { CategoryPipe } from 'src/app/shared/pipes/category.pipe';

import { ConteinerEventosComponent } from './conteiners/conteiner-eventos.component';
import { EventosRoutingModule } from './eventos-routing.module';
import { EventosListComponent } from './components/eventos-list/eventos-list.component';
import { EventosFormComponent } from './components/eventos-form/eventos-form.component';

@NgModule({
  declarations: [ConteinerEventosComponent, EventosListComponent, EventosFormComponent],
  imports: [
    CommonModule,
    EventosRoutingModule,

    AppMaterialModule,
    ReactiveFormsModule,
    CategoryPipe,
    ErrorDialogComponent,
    ConfirmationDialogComponent
  ]
})
export class EventosModule {}
