package com.example.core.data.repository.userdata

import com.example.core.model.DarkThemeConfig
import com.example.core.model.ThemeBrand
import com.example.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>
    suspend fun setThemeBrand(themeBrand: ThemeBrand)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}
