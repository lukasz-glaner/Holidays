package com.project.holidays.domain.holiday;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HolidayRepository extends CrudRepository<Holiday, Long> {
    List<Holiday> findHolidaysByApprovedIsTrue();

    List<Holiday> findHolidaysByApprovedIsFalse();
}
