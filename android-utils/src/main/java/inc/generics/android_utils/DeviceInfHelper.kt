package inc.generics.android_utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings

class DeviceInfHelper(private val context: Context) {
    @SuppressLint("HardwareIds")
    fun getDeviceInfo(): Map<String, String> {
        val uuid =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val name = Build.MODEL
        val os = "Android ${Build.VERSION.RELEASE}"

        return mapOf(
            "uuid" to uuid,
            "name" to name,
            "os" to os
        )
        /* возможно стоит вынести данные в модель ->
        создать модуль "util", задать в нём интерфесы для всех утилитных классов
        реализовать impl здесь на классе DeviceIngHelper [REFACTOR]
        */
    }
}