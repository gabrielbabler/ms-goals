package com.gbabler.msgoals.controller

import com.gbabler.msgoals.model.dto.GoalRequest
import com.gbabler.msgoals.model.dto.GoalResponse
import com.gbabler.msgoals.model.dto.PartialGoalRequest
import com.gbabler.msgoals.service.GoalService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/goals")
class GoalController(private val goalService: GoalService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<GoalResponse> {
        return goalService.listAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: String): GoalResponse {
        return goalService.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody @Valid goalRequest: GoalRequest,
        httpServletResponse: HttpServletResponse,
        uriComponentsBuilder: UriComponentsBuilder
    ) {
        val idCreated = goalService.create(goalRequest)
        val uri = uriComponentsBuilder.path("/goals/{id}").buildAndExpand(idCreated).toUriString()
        httpServletResponse.setHeader("location", uri)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateGoal(@PathVariable id: String, @RequestBody @Valid goalRequest: GoalRequest) {
        goalService.update(goalRequest, id)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun partialUpdateGoal(@PathVariable id: String, @RequestBody partialGoalRequest: PartialGoalRequest) {
        goalService.partialUpdate(partialGoalRequest, id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteGoal(@PathVariable id: String) {
        goalService.delete(id)
    }
}