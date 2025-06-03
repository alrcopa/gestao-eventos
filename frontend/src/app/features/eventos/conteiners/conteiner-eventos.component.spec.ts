import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConteinerEventosComponent } from './conteiner-eventos.component';

describe('ConteinerEventosComponent', () => {
  let component: ConteinerEventosComponent;
  let fixture: ComponentFixture<ConteinerEventosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConteinerEventosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConteinerEventosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
