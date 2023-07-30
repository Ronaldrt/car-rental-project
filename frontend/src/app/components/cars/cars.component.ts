import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Car} from "../../models/cars";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CarService} from "../../services/car/car.service";
import {BookingComponent} from "../booking/booking.component";
import {MatDialog} from "@angular/material/dialog";


@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {

  carsArray: Array<Car> = []

  displayedColumns: Array<string> =
    ['id', 'brand', 'model', 'productionYear', 'color', 'available', 'priceList'];
  dataSource: MatTableDataSource<Car>;
  cars: Array<Car> = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  carForm = new FormGroup({
    brand: new FormControl('',
      [Validators.required, Validators.minLength(2)]),
    model: new FormControl('',
      [Validators.required, Validators.minLength(2)]),
    productionYear: new FormControl('YYYY-MM',
      [Validators.required, Validators.minLength(7)]),
    color: new FormControl('', [
      Validators.required, Validators.minLength(3)]),
    available: new FormControl(false, Validators.required),
    priceList: new FormControl('', Validators.required),
  });

  constructor(
    private carService: CarService
    , private dialog: MatDialog) {
    this.dataSource = new MatTableDataSource(this.cars);
    console.log('inside cars component constructor')
  }

  get brand() {
    return this.carForm.controls.brand;
  }

  get model() {
    return this.carForm.controls.model;
  }

  get productionYear() {
    return this.carForm.controls.productionYear;
  }

  get color() {
    return this.carForm.controls.color;
  }

  get available() {
    return this.carForm.controls.available;
  }

  get priceList() {
    return this.carForm.controls.priceList;
  }

  ngOnInit(): void {
    this.carService.getAllCars()
      .subscribe(value => {
        console.log(JSON.stringify(value, null, 2))
      })
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.fetchCars();
  }

  private fetchCars() {
    this.carService.getAllCars()
      .subscribe(cars => {
        this.cars = cars;
        this.dataSource.data = this.cars;
        console.log(`results: ${JSON.stringify(cars, null, 2)}`);
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onSubmit() {
    console.log(`data to send: ${JSON.stringify(this.carForm.value, null, 2)}`);

    this.carService.createCar(this.carForm.value as Car)
      .subscribe(value => {
        this.carForm.reset();
        this.fetchCars();
      });
  }

  openBookingDialog(selectedCar: Car) {
    const dialogRef = this.dialog.open(BookingComponent, {
      width: '400px',
      data: {
        carToBookId: selectedCar.id, // Pass the car ID to the booking dialog
        // Add other data if needed (e.g., client ID, other car details, etc.)
      }
    });

    // Optionally, you can handle the result from the dialog after it's closed
    dialogRef.afterClosed().subscribe(result => {
      // Handle the result here if needed (e.g., show a confirmation message)
      console.log(result);
    });

  }
}
