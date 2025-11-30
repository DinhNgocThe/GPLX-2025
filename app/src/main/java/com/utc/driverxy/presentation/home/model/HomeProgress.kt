package com.utc.driverxy.presentation.home.model

import com.utc.driverxy.R

enum class HomeProgress(val title: Int, val icon: Int) {
    CRITICAL_QUESTION(R.string.critical_question, R.drawable.ic_fire),
    TRAFFIC_SIGNS(R.string.traffic_signs, R.drawable.ic_stop),
    DRIVING_SCENARIO(R.string.driving_scenario, R.drawable.ic_driving_scenario),
    EXAM(R.string.exam, R.drawable.ic_exam)
}
