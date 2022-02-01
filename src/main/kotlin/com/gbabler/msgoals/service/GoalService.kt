package com.gbabler.msgoals.service

import com.gbabler.msgoals.exception.NotFoundException
import com.gbabler.msgoals.model.domain.GoalDomain
import com.gbabler.msgoals.model.dto.GoalRequest
import com.gbabler.msgoals.model.dto.GoalResponse
import com.gbabler.msgoals.model.dto.PartialGoalRequest
import com.gbabler.msgoals.repository.GoalRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GoalService(private val goalRepository: GoalRepository) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun listAll(): List<GoalResponse> {
        log.info("Getting all goals...")
        return goalRepository.findAll()
            .map((GoalResponse)::valueOf)
            .toList()
    }

    fun getById(id: String): GoalResponse {
        log.info("Getting the goal $id info...")
        return goalRepository.findById(id)
            .map((GoalResponse)::valueOf)
            .orElseThrow { NotFoundException() }
    }

    fun create(goalRequest: GoalRequest): String {
        log.info("Creating new goal with values \n $goalRequest")
        val newGoalDomain = GoalDomain.valueOf(goalRequest)
        val savedDomain = goalRepository.save(newGoalDomain)
        log.info("Created successfully.")
        return savedDomain.id!!
    }

    fun update(goalRequest: GoalRequest, id: String) {
        log.info("Updating goal $id...")
        val domain = goalRepository.findById(id)
            .orElseThrow { NotFoundException() }

        val updatedDomain = domain.copy(
            description = goalRequest.description,
            type = goalRequest.type,
            isCompleted = goalRequest.isCompleted
        )
        goalRepository.save(updatedDomain)
        log.info("Updated successfully.")
    }

    fun partialUpdate(partialGoalRequest: PartialGoalRequest, id: String) {
        log.info("Partial updating goal $id...")
        val domain = goalRepository.findById(id)
            .orElseThrow { NotFoundException() }

        partialGoalRequest.type?.let { domain.type = it }
        partialGoalRequest.description?.let { domain.description = it }
        partialGoalRequest.isCompleted?.let { domain.isCompleted = it }

        goalRepository.save(domain)
        log.info("Partial updated successfully.")
    }

    fun delete(id: String) {
        log.info("Deleting goal $id...")
        goalRepository.findById(id)
            .ifPresent{ goalRepository.deleteById(id) }
    }
}