package com.utc.driverxy.presentation.home.model

import com.utc.driverxy.R

enum class CantMiss(val title: Int, val subTitle: Int, val icon: Int) {
    SCAN_TRAFFIC_SIGNS(R.string.scan_traffic_signs, R.string.scan_traffic_signs_description, R.drawable.ic_camera),
    WRONG_SENTENCE(R.string.wrong_sentence, R.string.wrong_sentence_description, R.drawable.ic_wrong),
    TIPS(R.string.tips, R.string.tips_description, R.drawable.ic_tips),
}