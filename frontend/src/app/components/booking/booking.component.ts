import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BookingsService} from "../../services/booking-service/bookings.service";
import {BookingRecordDto} from "../../models/booking-record-dto";


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {

  bookingForm: FormGroup;
  totalPrice: number = 0; // Initialize with 0

  constructor(
    private dialogRef: MatDialogRef<BookingComponent>,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private bookingService: BookingsService
  ) {
    this.bookingForm = this.formBuilder.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      // Add other form controls (car selection, client details) if needed
    });
  }

  calculateTotalPrice() {
    const pricePerDayInEuroCents = this.data.carToBook.priceList.pricePerDayInEuroCents;
    const startDate = this.bookingForm.value.startDate;
    const endDate = this.bookingForm.value.endDate;

    const durationInDays = Math.max(1, Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24)));
    this.totalPrice = pricePerDayInEuroCents * durationInDays;

    // Implement your logic to calculate the total price based on the selected dates and any other data
    // For example, you can get the selected start and end dates from this.bookingForm.value
    // and apply your pricing logic accordingly.
    // Assign the calculated total price to this.totalPrice.
  }

  onSubmit() {
    const bookingRequestDto = {
      carToBookId: 1,
      clientId: 1,
      startDate: this.bookingForm.value.startDate,
      endDate: this.bookingForm.value.endDate
    };

    // Call the booking service to create a new booking
    this.bookingService.bookCar(bookingRequestDto).subscribe(
      (bookingRecord: BookingRecordDto) => {
        // Booking successful, close the dialog and pass the booking record back to the parent component
        this.dialogRef.close(bookingRecord);
      },
      (error) => {
        // Handle error if needed
        console.error('Error booking car:', error);
      }
    );
  }

  onCancel() {
    // Close the dialog without any data
    this.dialogRef.close();
  }
}

