package com.sda.carrentalproject.repository;

import com.sda.carrentalproject.domain.BookingRecord;
import com.sda.carrentalproject.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long>{

    // just an example of query
    @Query("""
    select br from BookingRecord br
    where br.client.name = :name and br.bookedCar.color = :color
""")
    Optional<BookingRecord> findByClientNameAndBookedCarColor(@Param("name") String name,
                                                              @Param("color") String color);
}
