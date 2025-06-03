import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { FormUtilsService } from 'src/app/shared/services/form-utils.service';

import { Evento } from '../../modal/evento';
import { EventosService } from '../../services/eventos.service';

@Component({
  selector: 'app-eventos-form',
  templateUrl: './eventos-form.component.html',
  styleUrls: ['./eventos-form.component.scss']
})
export class EventosFormComponent implements OnInit {

  form!: FormGroup;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: EventosService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private location: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) { }

  ngOnInit(): void {
    const evento: Evento = this.route.snapshot.data['evento'];
    this.form = this.formBuilder.group({
      _id: [evento._id],
      title: [
        evento.title,
        [Validators.required, Validators.minLength(5), Validators.maxLength(100)]
      ],
      description: [evento.description, [Validators.required]]
    });
  }

  // private retrieveLessons(course: Evento) {
  //   console.log(course);
  //   const lessons = [];
  //   if (course?.lessons) {
  //     course.lessons.forEach(lesson => lessons.push(this.createLesson(lesson)));
  //   } else {
  //     lessons.push(this.createLesson());
  //   }
  //   return lessons;
  // }

  // private createLesson(lesson: Lesson = { _id: '', name: '', youtubeUrl: '' }) {
  //   return this.formBuilder.group({
  //     _id: [lesson._id],
  //     name: [
  //       lesson.name,
  //       [Validators.required, Validators.minLength(5), Validators.maxLength(100)]
  //     ],
  //     youtubeUrl: [
  //       lesson.youtubeUrl,
  //       [Validators.required, Validators.minLength(10), Validators.maxLength(11)]
  //     ]
  //   });
  // }

  // getLessonFormArray() {
  //   return (<UntypedFormArray>this.form.get('lessons')).controls;
  // }

  getErrorMessage(fieldName: string): string {
    return this.formUtils.getFieldErrorMessage(this.form, fieldName);
  }

  // getLessonErrorMessage(fieldName: string, index: number) {
  //   return this.formUtils.getFieldFormArrayErrorMessage(
  //     this.form,
  //     'lessons',
  //     fieldName,
  //     index
  //   );
  // }

  // addLesson(): void {
  //   const lessons = this.form.get('lessons') as UntypedFormArray;
  //   lessons.push(this.createLesson());
  // }

  // removeLesson(index: number) {
  //   const lessons = this.form.get('lessons') as UntypedFormArray;
  //   lessons.removeAt(index);
  // }

  onSubmit() {
    if (this.form.valid) {
      this.service.save(this.form.value as Evento).subscribe({
        next: () => this.onSuccess(),
        error: () => this.onError()
      });
    } else {
      this.formUtils.validateAllFormFields(this.form);
    }
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Evento saved successfully!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.dialog.open(ErrorDialogComponent, {
      data: 'Error saving course.'
    });
  }
}
