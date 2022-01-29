package com.gbabler.msgoals.model.domain

import com.gbabler.msgoals.enumeration.GoalType
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "goals")
class GoalDomain(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String,
    val description: String,
    @Enumerated(EnumType.STRING)
    val type: GoalType,
    val isCompleted: Boolean) {

}
