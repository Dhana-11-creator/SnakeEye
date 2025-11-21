package com.example.snakeeye

data class SnakeActivity(
    val id: String,
    val title: String,
    val activityType: ActivityType,
    val venomousCategory: VenomousCategory,
    val location: String,
    val reporter: String,
    val timeAgo: String,
    val icon: String
)

enum class ActivityType(val displayName: String, val color: String) {
    SNAKE_BITING("Snake Biting", "#FF5722"),
    SNAKE_CATCHING("Snake Catching", "#4CAF50"),
    SNAKE_RESEARCHING("Snake Researching", "#2196F3"),
    SNAKE_EXPLORING("Snake Exploring", "#FF9800"),
    SNAKE_SIGHTING("Snake Sighting", "#9C27B0"),
    SNAKE_RESCUE("Snake Rescue", "#00BCD4")
}

enum class VenomousCategory(val displayName: String, val color: String) {
    HIGHLY_VENOMOUS("Highly Venomous", "#D32F2F"),
    MODERATELY_VENOMOUS("Moderately Venomous", "#F57C00"),
    MILDLY_VENOMOUS("Mildly Venomous", "#FBC02D"),
    NON_VENOMOUS("Non-Venomous", "#388E3C"),
    UNKNOWN("Unknown", "#757575")
}