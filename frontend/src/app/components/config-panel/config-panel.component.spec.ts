import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigPanelComponent } from './config-panel.component';
import { provideHttpClient } from '@angular/common/http';

describe('ConfigPanelComponent', () => {
  let component: ConfigPanelComponent;
  let fixture: ComponentFixture<ConfigPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfigPanelComponent],
      providers: [provideHttpClient()],
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfigPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
