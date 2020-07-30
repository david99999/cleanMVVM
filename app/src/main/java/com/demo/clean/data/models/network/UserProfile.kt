package com.demo.clean.data.models.network

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class UserProfile(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @Embedded
    val address: Address,
    val phone: String,
    val website: String,
    @Embedded
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