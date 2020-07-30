package com.demo.clean.data.mappers

import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.data.models.network.UserProfile

class UserShortInfoMapper : Mapper<UserProfile, UserShortInfo> {
    override fun map(input: UserProfile): UserShortInfo {
        input.apply {
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