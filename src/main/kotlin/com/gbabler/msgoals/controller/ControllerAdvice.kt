package com.gbabler.msgoals.controller

import com.fasterxml.jackson.databind.JsonMappingException
import com.gbabler.msgoals.exception.NotFoundException
import com.gbabler.msgoals.model.dto.ErrorResponse
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*
import java.util.stream.Collectors

@RestControllerAdvice
class ControllerAdvice(val messageSource: MessageSource) {

    @ExceptionHandler(value = [(NotFoundException::class)])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(exception: NotFoundException) {}

    @ExceptionHandler(value = [(HttpMessageNotReadableException::class)])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): List<ErrorResponse> {
        if(exception.cause is JsonMappingException) {
            val errorField = (exception.cause as JsonMappingException).path.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."))

            val message = getMessage("400.001", errorField)
            val errorResponse = ErrorResponse(message, "400.001")
            return Collections.singletonList(errorResponse)
        }
        return defaultBadRequestError()
    }

    private fun defaultBadRequestError(): List<ErrorResponse> {
        val message = getMessage("400.000", null)
        val errorResponse = ErrorResponse(message, "400.000")
        return Collections.singletonList(errorResponse)
    }

    private fun getMessage(code: String, args: String?): String {
        return this.messageSource.getMessage(code, arrayOf(args), LocaleContextHolder.getLocale())
    }
}