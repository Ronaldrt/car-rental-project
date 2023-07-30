import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup} from "@angular/forms";

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
    @Inject(MAT_DIALOG_DATA) public data: any // You can pass any data to the dialog if needed
  ) {
    this.bookingForm = this.formBuilder.group({
      startDate: [''],
      endDate: [''],
      // Add other form controls (car selection, client details) if needed
    });
  }

  calculateTotalPrice() {

    // Implement your logic to calculate the total price based on the selected dates and any other data
    // For example, you can get the selected start and end dates from this.bookingForm.value
    // and apply your pricing logic accordingly.
    // Assign the calculated total price to this.totalPrice.
  }

  onSubmit() {
    // Implement the logic for submitting the form and closing the dialog
    // You can get the form data from this.bookingForm.value and use it as needed
    // Close the dialog with any data you want to pass back to the parent component
    this.dialogRef.close(/* data to pass back, if any */);
  }

  onCancel() {
    // Implement the logic for handling cancellation and closing the dialog without any data
    this.dialogRef.close();
  }
}

