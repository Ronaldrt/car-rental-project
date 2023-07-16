package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.domain.BookingRecord;
import com.sda.carrentalproject.dto.BookingRecordDto;
import com.sda.carrentalproject.dto.CarBookingRequestDto;
import com.sda.carrentalproject.mapper.BookingRecordMapper;
import com.sda.carrentalproject.service.BookingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class BookingRecordController {

    private final BookingRecordService bookingRecordService;

    private final BookingRecordMapper mapper;



    public BookingRecordController(BookingRecordService bookingRecordService, BookingRecordMapper bookingRecordMapper) {
        this.bookingRecordService = bookingRecordService;
        this.mapper = bookingRecordMapper;
    }

    @GetMapping("/booking-records")
    public List<BookingRecordDto> allBookingRecords(){
        log.info("getting all booking records");
        return bookingRecordService.findAllBookingRecords()
                .stream()
                .map(bookingRecord -> mapper.fromEntityToDto(bookingRecord))
                .toList();
    }

    @PostMapping("/booking-records")
    public ResponseEntity<BookingRecordDto> bookCar(@RequestBody CarBookingRequestDto carBookingRequest,
                                                    UriComponentsBuilder ucb) {
        log.info("trying to book car with arguments: [{}]", carBookingRequest);
        BookingRecord saved = bookingRecordService.createNewBooking(carBookingRequest);
        URI path = ucb.path("/api/booking-records/{id}")
                .buildAndExpand(Map.of("id", saved.getId()))
                .toUri();
        return ResponseEntity.created(path)
                .body(mapper.fromEntityToDto(saved));
    }
}
