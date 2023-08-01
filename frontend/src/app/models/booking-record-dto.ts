import {Car} from "./cars";
import {Client} from "./client";

export interface BookingRecordDto {
  id: number;
  bookedCar: Car; // Make sure to create CarDto as well
  client: Client; // Make sure to create ClientDto as well
  startDate: string; // Use string or Date data type as per your requirements
  endDate: string;   // Use string or Date data type as per your requirements
  fullPriceInEuroCents: number;
}
