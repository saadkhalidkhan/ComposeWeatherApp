package com.droidgeeks.coreui.util

fun String.extractTime(): String {
    val parts = this.split(" ")
    return if (parts.size > 1) {
        parts[1]
    } else {
        "00:00" // Default value
    }
}

fun String.isSameTime(timeSlot: String): Boolean {
    // Convert time A to minutes since midnight
    val aParts = this.split(":")
    val aHours = aParts[0].toInt()
    val aMinutesSinceMidnight = aHours * 60

    val slotParts = timeSlot.split(":")
    val slotHours = slotParts[0].toInt()
    val slotMinutesSinceMidnight = slotHours * 60

    return aMinutesSinceMidnight == slotMinutesSinceMidnight
}
