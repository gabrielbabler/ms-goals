package com.gbabler.msgoals.model.dto

import com.gbabler.msgoals.enumeration.GoalType
import com.gbabler.msgoals.model.domain.GoalDomain
import lombok.Builder

@Builder
class GoalResponse(
    val id: String,
    val description: String,
    val isCompleted: Boolean,
    val type: GoalType) {

    companion object {
        fun valueOf(goalDomain: GoalDomain): GoalResponse {
            return GoalResponse(
                id = goalDomain.id,
                description = goalDomain.description,
                isCompleted = goalDomain.isCompleted,
                type = goalDomain.type
            )
        }
    }
}