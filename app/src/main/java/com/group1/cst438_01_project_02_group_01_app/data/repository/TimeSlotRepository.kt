package com.group1.cst438_01_project_02_group_01_app.data.repository

import com.group1.cst438_01_project_02_group_01_app.data.api.RetrofitInstance
import com.group1.cst438_01_project_02_group_01_app.data.api.SchedulerApi
import com.group1.cst438_01_project_02_group_01_app.data.model.TimeSlotDto

class TimeSlotRepository(
    private val schedulerApi: SchedulerApi = RetrofitInstance.schedulerApi
) {
    suspend fun getTimeSlots(): List<TimeSlotDto> = schedulerApi.getTimeSlots()
}
