package com.gbabler.msgoals.service

import com.gbabler.msgoals.model.dto.GoalResponse
import com.gbabler.msgoals.repository.GoalRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class GoalService(private val goalRepository: GoalRepository) {

    fun listAll(): List<GoalResponse> {
        return goalRepository.findAll()
            .stream()
            .map((GoalResponse)::valueOf)
            .collect(Collectors.toList())
    }
}