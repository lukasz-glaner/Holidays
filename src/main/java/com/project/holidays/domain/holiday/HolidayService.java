package com.project.holidays.domain.holiday;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;


    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public Optional<Holiday> findHolidayById(Long id) {
        return holidayRepository.findById(id);
    }
    public List<Holiday> findApproveHolidays(){
        return holidayRepository.findHolidaysByIsApprovedIsTrue();
    }
}
