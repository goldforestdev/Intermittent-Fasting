package com.goldforest.domain.usercase

import com.goldforest.domain.model.Plan


interface HasActivePlan {

    suspend fun get(): Plan

}