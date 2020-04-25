import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InoscSharedModule } from 'app/shared/shared.module';
import { MvtStkComponent } from './mvt-stk.component';
import { MvtStkDetailComponent } from './mvt-stk-detail.component';
import { MvtStkUpdateComponent } from './mvt-stk-update.component';
import { MvtStkDeleteDialogComponent } from './mvt-stk-delete-dialog.component';
import { mvtStkRoute } from './mvt-stk.route';

@NgModule({
  imports: [InoscSharedModule, RouterModule.forChild(mvtStkRoute)],
  declarations: [MvtStkComponent, MvtStkDetailComponent, MvtStkUpdateComponent, MvtStkDeleteDialogComponent],
  entryComponents: [MvtStkDeleteDialogComponent]
})
export class InoscMvtStkModule {}
