package com.example.core.data.repository.userdata

import com.example.core.model.DarkThemeConfig
import com.example.core.model.ThemeBrand
import com.example.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserDataRepositoryImpl : UserDataRepository {
    override val userData: Flow<UserData>
        get() = flow {
            emit(
                UserData(
                    themeBrand = ThemeBrand.ANDROID,
                    darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
                    useDynamicColor = true
                )
            )
        }

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
    }
}
