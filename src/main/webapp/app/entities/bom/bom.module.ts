import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InoscSharedModule } from 'app/shared/shared.module';
import { BomComponent } from './bom.component';
import { BomDetailComponent } from './bom-detail.component';
import { BomUpdateComponent } from './bom-update.component';
import { BomDeleteDialogComponent } from './bom-delete-dialog.component';
import { bomRoute } from './bom.route';

@NgModule({
  imports: [InoscSharedModule, RouterModule.forChild(bomRoute)],
  declarations: [BomComponent, BomDetailComponent, BomUpdateComponent, BomDeleteDialogComponent],
  entryComponents: [BomDeleteDialogComponent]
})
export class InoscBomModule {}
