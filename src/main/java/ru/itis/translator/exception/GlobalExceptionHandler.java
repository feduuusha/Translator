package ru.itis.translator.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    protected ModelAndView handleServerException(Exception ex) {
        log.debug("Processing the HTTP 500 status code: " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/500");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    protected ModelAndView handleNoResourceFoundException(Exception ex) {
        log.debug("Processing the HTTP 404 status code: " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/404");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    protected ModelAndView handleOtherExceptions(Exception ex) {
        log.debug("Processing the HTTP 400 status code: " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/400");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }
}