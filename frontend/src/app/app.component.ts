import { Component } from '@angular/core';
import { ShellComponent } from './components/shell/shell.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ShellComponent],
  template: '<app-shell />'
})
export class AppComponent {}
