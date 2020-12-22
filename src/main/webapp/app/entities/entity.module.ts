import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'operation-log',
        loadChildren: () => import('./operation-log/operation-log.module').then(m => m.FrameworkOperationLogModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FrameworkEntityModule {}
