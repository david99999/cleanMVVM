package com.demo.clean.data.models.network

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.domain.models.UserShortInfo
import java.io.Serializable

@Entity
data class UserProfile(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @Embedded(prefix = "address_")
    val address: Address,
    val phone: String,
    val website: String,
    @Embedded(prefix = "company_")
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
    @Embedded(prefix = "geo_")
    val geo: Geo
) : Serializable


fun UserProfile.toUserDetailedInfo(): UserDetailedInfo {
    apply {
        return UserDetailedInfo(
            id = id,
            name = name,
            company = company.name,
            address = address.street.plus(" -").plus(address.city)
        )
    }
}

fun UserProfile.toUserShortInfo(): UserShortInfo {
    apply {
        return UserShortInfo(
            id = id,
            name = name.plus(" (${username})"),
            email = email,
            company = company.name,
            address = address.street.plus(" - ${address.city}")
        )
    }
}