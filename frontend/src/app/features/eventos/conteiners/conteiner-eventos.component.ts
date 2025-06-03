import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of, tap } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Evento } from '../modal/evento';
import { EventosService } from '../services/eventos.service';
import { EventoPage } from '../modal/evento-page';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-conteiner-eventos',
  templateUrl: './conteiner-eventos.component.html',
  styleUrls: ['./conteiner-eventos.component.scss']
})
export class ConteinerEventosComponent {

  eventos$: Observable<EventoPage> | null = null;

  pageIndex = 0;
  pageSize = 10;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private eventosService: EventosService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.refresh();
  }

  refresh(pageEvent: PageEvent = { length: 0, pageIndex: 0, pageSize: 10 }) {
    this.eventos$ = this.eventosService
      .list(this.paginator?.pageIndex, this.paginator?.pageSize)
      .pipe(
        tap(() => {
          this.pageIndex = pageEvent.pageIndex;
          this.pageSize = pageEvent.pageSize;
        }),
        catchError(() => {
          this.onError('Error loading courses.');
          return of({ eventos: [], totalElements: 0 } as EventoPage);
        })
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(course: Evento) {
    this.router.navigate(['edit', course._id], { relativeTo: this.route });
  }

  onRemove(course: Evento) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Are you sure you would like to remove this course?'
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.eventosService.remove(course._id).subscribe({
          next: () => {
            this.refresh();
            this.snackBar.open('Course removed successfully!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center'
            });
          },
          error: () => this.onError('Error trying to remove the course.')
        });
      }
    });
  }
}
