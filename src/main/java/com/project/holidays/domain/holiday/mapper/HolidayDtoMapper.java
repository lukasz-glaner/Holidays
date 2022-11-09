package com.project.holidays.domain.holiday.mapper;

import com.project.holidays.domain.holiday.Holiday;
import com.project.holidays.domain.holiday.dto.HolidayDto;

public class HolidayDtoMapper {
    public static HolidayDto map(Holiday holiday) {
        HolidayDto holidayDto = new HolidayDto();
        holidayDto.setId(holiday.getId());
        holidayDto.setStartDate(holiday.getStartDate());
        holidayDto.setEndDate(holiday.getEndDate());
        holidayDto.setApproverId(holiday.getApproverId());
        holidayDto.setApproved(holiday.getApproved());
        return holidayDto;
    }
}
