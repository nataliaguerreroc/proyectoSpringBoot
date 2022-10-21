package com.natalia.proyectoSpringBoot.exceptions;

import com.natalia.proyectoSpringBoot.controllers.CourseController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.natalia.proyectoSpringBoot.controllers")
public class RestResponseExceptionHandler {

    @ExceptionHandler(value = {CourseNotRegistered.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage courseNotRegisteredException(CourseNotRegistered courseNotRegistered){
        String errorMessage = "No se encontró el curso";
        return new ExceptionMessage(errorMessage, SeverityError.LOW);
    }

    @ExceptionHandler(value = {CareerNotRegistered.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage careerNotRegisteredException(CareerNotRegistered careerNotRegistered){
        String errorMessage = "No se encontró la carrera";
        return new ExceptionMessage(errorMessage, SeverityError.MEDIUM);
    }

    @ExceptionHandler(value = {UserNotRegistered.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage userNotRegisteredException(UserNotRegistered userNotRegistered){
        String errorMessage = "No se encontró el usuario";
        return new ExceptionMessage(errorMessage, SeverityError.MEDIUM);
    }





}
