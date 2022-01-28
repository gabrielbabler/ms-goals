package com.gbabler.msgoals.repository

import com.gbabler.msgoals.model.domain.GoalDomain
import org.springframework.data.jpa.repository.JpaRepository

interface GoalRepository : JpaRepository<GoalDomain, String> {
}