package inc.generics.duet_local.sp

import android.content.Context
import android.content.SharedPreferences

class SPHelper(context: Context) {
    companion object {
        const val NAME_SHARED_PREFERENCE = "DUET_SHARED_PREFERENCE"

        const val DUET_ACCESS_TOKE = "DUET_ACCESS_TOKE"
        const val DUET_REFRESH_TOKE = "DUET_REFRESH_TOKE"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE)

    var accessToken: String?
        set(value) = sharedPreferences.edit()
            .putString(DUET_ACCESS_TOKE, value)
            .apply()
        get() =
            sharedPreferences.getString(DUET_ACCESS_TOKE, null)

    var refreshToken: String?
        set(value) = sharedPreferences.edit()
            .putString(DUET_REFRESH_TOKE, value)
            .apply()
        get() =
            sharedPreferences.getString(DUET_REFRESH_TOKE, null)
}