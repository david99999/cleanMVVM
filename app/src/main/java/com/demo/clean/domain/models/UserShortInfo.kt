package com.demo.clean.domain.models

import java.io.Serializable

data class UserShortInfo(
    val id: Int,
    val name: String,
    val email: String,
    val company: String,
    val address: String
) : Serializable