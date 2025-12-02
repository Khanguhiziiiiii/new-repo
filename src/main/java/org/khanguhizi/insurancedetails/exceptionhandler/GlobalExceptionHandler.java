package org.khanguhizi.insurancedetails.exceptionhandler;

import org.khanguhizi.insurancedetails.dto.ApiResponse;
import org.khanguhizi.insurancedetails.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateCredentialsException.class)
    public ResponseEntity<String> handleDuplicateCredentialsException(DuplicateCredentialsException duplicateCredentialsException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.BAD_REQUEST))
                .message(duplicateCredentialsException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordsFoundException.class)
    public ResponseEntity<String> handleNoRecordsFoundException(NoRecordsFoundException noRecordsFoundException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .message(noRecordsFoundException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse.toString(), HttpStatus.NOT_FOUND);
    }

}
