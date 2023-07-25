import {Component, OnDestroy, OnInit} from '@angular/core';
import {carsPageUrl, clientsPageUrl, homePageUrl, loginPageUrl} from "../../models/links";
import {Subscription} from "rxjs";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit, OnDestroy {

  protected readonly homePageUrl = homePageUrl;
  protected readonly clientsPageUrl = clientsPageUrl;
  protected readonly carsPageUrl = carsPageUrl;
  protected readonly loginPageUrl = loginPageUrl;

  currentRoute: string = '';
  routerEvents$!: Subscription;

  constructor(
    private router: Router
  ) {
  }

  // constructor(public router: Router) {
  //   router.events.pipe(
  //     filter((e: Event | RouterEvent): e is RouterEvent => e instanceof RouterEvent)
  //   ).subscribe((e: RouterEvent) => {
  //     // Do something
  //   });
  // }

  ngOnDestroy(): void {
    console.log("releasing console listener")
    this.routerEvents$.unsubscribe();
  }

  ngOnInit(): void {

    console.log("registering router listener")
    this.router
      .events
      .subscribe(value =>{
        if(value instanceof NavigationEnd){
          console.log("Navigation url: " + value.url)
          this.currentRoute = value.url
        }
      })
  }

  onValueChange(value: any){
    console.log("selected button: " + value)
    this.currentRoute = value
  }
}
