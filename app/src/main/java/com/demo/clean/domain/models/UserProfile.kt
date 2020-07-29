package com.demo.clean.domain.models

import java.io.Serializable

data class UserProfile(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) : Serializable

data class Company(

    val name: String,
    val catchPhrase: String,
    val bs: String
) : Serializable

data class Geo(

    val lat: Double,
    val lng: Double
) : Serializable

data class Address(

    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
) : Serializable