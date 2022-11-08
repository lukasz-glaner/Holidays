package com.project.holidays.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.project.holidays.domain.holiday.Holiday;
import com.project.holidays.domain.holiday.HolidayService;
import com.project.holidays.domain.holiday.dto.HolidayAddDto;
import com.project.holidays.domain.holiday.dto.HolidayDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class HolidayController {
    private final HolidayService holidayService;
    private final ObjectMapper objectMapper;

    public HolidayController(HolidayService holidayService, ObjectMapper objectMapper) {
        this.holidayService = holidayService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/holidays")
    public ResponseEntity<HolidayDto> getHolidayById(@RequestParam Long id) {
        return holidayService.findHolidayByIdReturnDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/holidays/approved")
    public ResponseEntity<List<HolidayDto>> getApprovedHoliday() {
        List<HolidayDto> approvedHolidays = holidayService.findApprovedHolidays();
        return ResponseEntity.ok(approvedHolidays);
    }

    @Transactional
    @PostMapping("/holidays/add")
    public ResponseEntity<?> addHoliday(@RequestBody @Valid HolidayAddDto holidayAddDto) {
        Holiday holidayToAdd = prepareHolidayToAdd(holidayAddDto);
        HolidayDto savedHoliday = holidayService.createHoliday(holidayToAdd);
        return ResponseEntity.created(URI.create("/holidays?id=" + savedHoliday.getId()))
                .body(savedHoliday);
    }

    private Holiday prepareHolidayToAdd(HolidayAddDto holidayAddDto) {
        return new Holiday(holidayAddDto.getStartDate(),
                holidayAddDto.getEndDate(),
                holidayAddDto.getApproverId(),
                false);
    }

    @Transactional
    @PatchMapping("/holidays/{id}")
    public ResponseEntity<?> updateHoliday(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
            Holiday holiday = holidayService.findHolidayById(id).orElseThrow();
            Holiday holidayPatched = applyPatch(holiday, patch);
            holidayService.updateHoliday(holidayPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private Holiday applyPatch(Holiday holiday, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode holidayNode = objectMapper.valueToTree(holiday);
        JsonNode holidayPatchedNode = patch.apply(holidayNode);
        return objectMapper.treeToValue(holidayPatchedNode, Holiday.class);
    }

    @DeleteMapping("/holidays/{id}")
    public ResponseEntity<?> deleteHoliday(@PathVariable Long id) {
        holidayService.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
