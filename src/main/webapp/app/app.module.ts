import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { InoscSharedModule } from 'app/shared/shared.module';
import { InoscCoreModule } from 'app/core/core.module';
import { InoscAppRoutingModule } from './app-routing.module';
import { InoscHomeModule } from './home/home.module';
import { InoscEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    InoscSharedModule,
    InoscCoreModule,
    InoscHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    InoscEntityModule,
    InoscAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class InoscAppModule {}
