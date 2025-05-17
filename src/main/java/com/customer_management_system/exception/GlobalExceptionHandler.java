package com.customer_management_system.exception;

import com.customer_management_system.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex) {
        log.error("RecordNotFoundException: {}", ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Record not found");
        details.add(ex.getMessage());
        ApiErrors errors = new ApiErrors(ex.getMessage(), details, HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        log.error("BadRequestException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new CommonResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null)
        );
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<Object> handleBaseException(BaseException ex) {
        log.error("BaseException: {}", ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Base exception");
        details.add(ex.getMessage());
        ApiErrors errors = new ApiErrors(ex.getMessage(), details, HttpStatus.resolve(ex.getErrorCode()), LocalDateTime.now());
        return ResponseEntity.status(ex.getErrorCode()).body(errors);
    }
}
