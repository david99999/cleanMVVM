package com.demo.clean.data.mappers

import com.demo.clean.data.models.network.UserProfile
import com.demo.clean.domain.models.UserDetailedInfo

class UserDetailedInfoMapper : Mapper<UserProfile, UserDetailedInfo> {
    override fun map(input: UserProfile): UserDetailedInfo {
        input.apply {
            return UserDetailedInfo(
                id = id,
                name = name,
                company = company.name,
                address = address.street.plus(" -").plus(address.city)
            )
        }
    }

}
