package com.gbabler.msgoals.exception

import org.springframework.http.HttpStatus

class NotFoundException(httpStatus: HttpStatus = HttpStatus.NOT_FOUND): RuntimeException() {
}