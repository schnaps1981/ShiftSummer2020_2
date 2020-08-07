package com.shiftsummer2020_2.global.viewmodel

sealed class ProgressStatus {
    object InProgress: ProgressStatus()
    object Done: ProgressStatus()
    object Fail: ProgressStatus()
}