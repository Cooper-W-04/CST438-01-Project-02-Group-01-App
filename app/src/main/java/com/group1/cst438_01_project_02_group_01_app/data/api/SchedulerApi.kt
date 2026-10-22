package com.group1.cst438_01_project_02_group_01_app.data.api

import com.group1.cst438_01_project_02_group_01_app.data.model.TimeSlotDto
import retrofit2.http.GET

interface SchedulerApi {
    @GET("api/v1/time-slots")
    suspend fun getTimeSlots(): List<TimeSlotDto>
}
