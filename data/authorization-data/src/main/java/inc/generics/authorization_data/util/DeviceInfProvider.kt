package inc.generics.authorization_data.util

interface DeviceInfProvider {
    fun getInformation(): DeviceInf
}

data class DeviceInf(
    val uuid: String,
    val name: String,
    val os: String
)