package com.example.tmobiledemo.util.extensions

import androidx.compose.ui.graphics.Color

val String.color
    get() = Color(android.graphics.Color.parseColor(this))