package com.demo.clean.data.mappers

import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.domain.models.UserProfile

class UserProfileMapper {
    operator fun invoke(userProfile: UserProfile): UserShortInfo {
        userProfile.apply {
            return UserShortInfo(
                id = id,
                name = name.plus(" (${username})"),
                email = email,
                company = company.name,
                address = address.street.plus(" - ${address.city}")
            )
        }
    }
}