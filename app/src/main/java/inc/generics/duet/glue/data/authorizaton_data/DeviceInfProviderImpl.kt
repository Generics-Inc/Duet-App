package inc.generics.duet.glue.data.authorizaton_data

import inc.generics.android_utils.DeviceInfHelper
import inc.generics.authorization_data.util.DeviceInf
import inc.generics.authorization_data.util.DeviceInfProvider

class DeviceInfProviderImpl(private val deviceInfHelper: DeviceInfHelper) : DeviceInfProvider {
    companion object {
        private const val COULD_NOT_DETERMINED = "could not be determined"
    }
    override fun getInformation(): DeviceInf {
        val inf = deviceInfHelper.getDeviceInfo()

        return DeviceInf(
            uuid = inf.getOrDefault("uuid", COULD_NOT_DETERMINED),
            name = inf.getOrDefault("name", COULD_NOT_DETERMINED),
            os = inf.getOrDefault("os", COULD_NOT_DETERMINED),
        )
    }
}