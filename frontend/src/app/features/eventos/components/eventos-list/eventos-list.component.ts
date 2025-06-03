import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Evento } from '../../modal/evento';

@Component({
  selector: 'app-eventos-list',
  templateUrl: './eventos-list.component.html',
  styleUrls: ['./eventos-list.component.scss']
})
export class EventosListComponent {

  @Input() eventos: Evento[] = [];
  @Output() details: EventEmitter<Evento> = new EventEmitter(false);
  @Output() edit: EventEmitter<Evento> = new EventEmitter(false);
  @Output() remove: EventEmitter<Evento> = new EventEmitter(false);
  @Output() add: EventEmitter<boolean> = new EventEmitter(false);

  readonly displayedColumns = ['title', 'description', 'actions'];

  onDetails(record: Evento) {
    this.details.emit(record);
  }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(record: Evento) {
    this.edit.emit(record);
  }

  onRemove(record: Evento) {
    this.remove.emit(record);
  }
}
