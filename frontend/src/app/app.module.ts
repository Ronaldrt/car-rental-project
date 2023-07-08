import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './components/app-component/app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ClientsComponent} from './components/clients/clients.component';
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {clientPageUrl, homePageUrl, notFoundPageUrl} from "./models/links";
import {MatIconModule} from "@angular/material/icon";

@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
    RouterOutlet,
    RouterModule.forRoot([
      {path: homePageUrl, component: HomePageComponent},
      {path: clientsPageUrl, component: ClientsComponent},
      {path: notFoundPageUrl, component: NotFoundPage}
    ]),
    MatIconModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
