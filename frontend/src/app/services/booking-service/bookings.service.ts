import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CarBookingRequestDto} from "../../models/car-booking-request-dto";
import {BookingRecordDto} from "../../models/booking-record-dto";


@Injectable({
  providedIn: 'root'
})
export class BookingsService {

  constructor(
    private http:HttpClient
  ) {
  }

  bookCar(bookingRequestDto: CarBookingRequestDto): Observable<BookingRecordDto>{
    return this.http.post<BookingRecordDto>('http://localhost:8080/api/booking-records', bookingRequestDto);
  }



}
