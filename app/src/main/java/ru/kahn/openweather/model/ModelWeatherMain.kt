package ru.kahn.openweather.model

data class ModelWeatherMain (
    val message : String,
    val cod : String,
    val count : Int,
    val list : MutableList<ModelListCity>
)

data class ModelListCity (
    val name : String,
    val main : ModelMain,
    val weather : List<ModelWeatherCity>
)

data class ModelMain (
    val temp : Double
)

data class ModelWeatherCity (
    val description : String,
    val icon : String
)