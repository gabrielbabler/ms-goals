package com.gbabler.msgoals.model.dto

import com.gbabler.msgoals.enumeration.GoalType
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class PartialGoalRequest(
    val description: String?,
    val isCompleted: Boolean?,
    @Enumerated(EnumType.STRING)
    val type: GoalType?
) {}