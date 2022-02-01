package com.gbabler.msgoals.model.domain

import com.gbabler.msgoals.enumeration.GoalType
import com.gbabler.msgoals.model.dto.GoalRequest
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "goals")
data class GoalDomain(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    var id: String?,
    var description: String,
    @Enumerated(EnumType.STRING)
    var type: GoalType,
    var isCompleted: Boolean
) {
    companion object {
        fun valueOf(goalRequest: GoalRequest): GoalDomain {
            return GoalDomain(null, goalRequest.description, goalRequest.type, goalRequest.isCompleted)
        }
    }
}