package com.project.holidays.web;

import com.project.holidays.domain.holiday.Holiday;
import com.project.holidays.domain.holiday.HolidayService;
import com.project.holidays.domain.holiday.dto.HolidayAddDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;

@Controller
public class HolidayController {
    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/holidays")
    public ResponseEntity<Holiday> getHolidayById(@RequestParam Long id)  {
        return holidayService.findHolidayById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/holidays/approved")
    public ResponseEntity<List<Holiday>> getApprovedHoliday()  {
        List<Holiday> approvedHolidays = holidayService.findApproveHolidays();
        return ResponseEntity.ok(approvedHolidays);
    }

    @Transactional
    @PostMapping("/holidays/add")
    public ResponseEntity addHoliday(@RequestBody HolidayAddDto holidayAddDto){
        Holiday holidayToAdd = new Holiday();
        holidayToAdd.setStartDate(holidayAddDto.getStartDate());
        holidayToAdd.setEndDate(holidayAddDto.getEndDate());
        holidayToAdd.setApproverId(holidayAddDto.getApproverId());
        holidayToAdd.setApproved(false);

       Holiday savedHoliday = holidayService.createHoliday(holidayToAdd);

        return ResponseEntity.created(URI.create("/holidays?id="+ savedHoliday.getId()))
                .body(savedHoliday);
    }
}
