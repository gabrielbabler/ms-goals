package com.gbabler.msgoals.model.domain

import com.gbabler.msgoals.enumeration.GoalType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class GoalDomain(
    @Id
    @GeneratedValue
    val id: String,
    val description: String,
    val type: GoalType,
    val isCompleted: Boolean) {

}
