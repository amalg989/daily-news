package com.personal.dailynews.util.constants

import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object {
        const val HTTP_BASE_URL = "https://newsapi.org/v2/"
        val DATE_FORMAT =
            SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
    }

}