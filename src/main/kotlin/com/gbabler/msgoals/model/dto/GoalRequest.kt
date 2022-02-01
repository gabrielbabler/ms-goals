package com.gbabler.msgoals.model.dto

import com.gbabler.msgoals.enumeration.GoalType
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class GoalRequest(
    @field:NotEmpty
    val description: String,

    @field:NotNull
    val isCompleted: Boolean,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    val type: GoalType
) {}