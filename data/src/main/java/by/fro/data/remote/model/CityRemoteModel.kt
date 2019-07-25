package by.fro.data.remote.model

data class CityRemoteModel(
    val name: String,
    val countryCode: String,
    var current: Boolean,
    var favorite: Boolean
)