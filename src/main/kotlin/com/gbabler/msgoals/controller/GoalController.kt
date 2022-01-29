package com.gbabler.msgoals.controller

import com.gbabler.msgoals.model.dto.GoalResponse
import com.gbabler.msgoals.service.GoalService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/goals")
class GoalController (private val goalService: GoalService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<GoalResponse> {
        return goalService.listAll();
    }
}