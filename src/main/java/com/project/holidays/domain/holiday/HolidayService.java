package com.project.holidays.domain.holiday;

import com.project.holidays.domain.employee.EmployeeRepository;
import com.project.holidays.domain.holiday.dto.HolidayDto;
import com.project.holidays.domain.holiday.mapper.HolidayDtoMapper;
import com.project.holidays.exception.ApproverNotCompliantException;
import com.project.holidays.exception.FreeDaysAmountToLowException;
import com.project.holidays.exception.HolidayIsAlreadyApprovedException;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final EmployeeRepository employeeRepository;


    public HolidayService(HolidayRepository holidayRepository, EmployeeRepository employeeRepository) {
        this.holidayRepository = holidayRepository;
        this.employeeRepository = employeeRepository;
    }

    public Optional<Holiday> findHolidayById(Long id) {
        return holidayRepository.findById(id);
    }

    public Optional<HolidayDto> findHolidayByIdReturnDto(Long id) {
        return this.findHolidayById(id).map(HolidayDtoMapper::map);
    }

    public List<HolidayDto> findApprovedHolidays() {
        List<Holiday> holidayList = holidayRepository.findHolidaysByApprovedIsTrue();
        return holidayList
                .stream()
                .map(HolidayDtoMapper::map)
                .toList();
    }

    public HolidayDto createHoliday(Holiday holidayToAdd) {
        Holiday savedHoliday = holidayRepository.save(holidayToAdd);
        employeeRepository.findById(1L).orElseThrow().getHolidays().add(savedHoliday);
        return HolidayDtoMapper.map(savedHoliday);
    }

    public void updateHoliday(Holiday holiday) {
        holidayRepository.save(holiday);
    }

    public void deleteHoliday(Long id) {
        holidayRepository.deleteById(id);
    }

    public void approveHoliday(Long id) {
        Holiday holidayToApprove = holidayRepository.findById(id).orElseThrow();
        Integer availableDays = 5; // here get days from employee from db
        Integer holidayDaysAmount = Period.between(holidayToApprove.getEndDate(), holidayToApprove.getStartDate()).getDays();
        Long holidayApproverId = holidayToApprove.getApproverId();
        Long approverId = 3L; // here get employeeId from credentials
        boolean approverCompliant = checkApprover(holidayApproverId, approverId);
        boolean daysAvailable = checkAvailableDays(availableDays, holidayDaysAmount);
        if (approverCompliant) {
            if (daysAvailable) {
                if (!holidayToApprove.getApproved()) {
                    holidayToApprove.setApproved(true);
                } else {
                    throw new HolidayIsAlreadyApprovedException("This holiday is already approved");
                }
            } else {
                throw new FreeDaysAmountToLowException("Free holiday days number too low");
            }
        } else {
            throw new ApproverNotCompliantException("Employee without permission to accept this holiday");
        }
    }

    private boolean checkApprover(Long holidayApproverId, Long approverId) {
        return holidayApproverId.equals(approverId);
    }

    private boolean checkAvailableDays(Integer availableDays, Integer holidaysDaysAmount) {
        return availableDays >= holidaysDaysAmount;
    }

}
