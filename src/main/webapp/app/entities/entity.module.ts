import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'item',
        loadChildren: () => import('./item/item.module').then(m => m.InoscItemModule)
      },
      {
        path: 'bom',
        loadChildren: () => import('./bom/bom.module').then(m => m.InoscBomModule)
      },
      {
        path: 'mvt-stk',
        loadChildren: () => import('./mvt-stk/mvt-stk.module').then(m => m.InoscMvtStkModule)
      },
      {
        path: 'stock',
        loadChildren: () => import('./stock/stock.module').then(m => m.InoscStockModule)
      },
      {
        path: 'parse-history',
        loadChildren: () => import('./parse-history/parse-history.module').then(m => m.InoscParseHistoryModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class InoscEntityModule {}
