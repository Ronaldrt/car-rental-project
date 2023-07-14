import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Car} from "../../models/cars";
import {allCarsUrl, createCarUrl} from "../../models/links";

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(
    private http: HttpClient
  ) {

  }


  getAllCars(): Observable<Array<Car>>{
    return this.http.get<Array<Car>>(allCarsUrl)
  }

  createCar(car: Car): Observable<Car>{
    return this.http.post<Car>(createCarUrl, car);
  }
}
