package id.haweje.weatherapp.core.utils

data class Resource<out T>(val status: StatusResponse, val data: T? = null, val message: String? = null){

        companion object{
            fun <T> success(data: T): Resource<T> =
                    Resource(StatusResponse.SUCCESS, data, null)

            fun <T> error(data: T?, message: String): Resource<T> =
                    Resource(StatusResponse.ERROR, data, message)

            fun <T> loading(data: T?): Resource<T> =
                    Resource(StatusResponse.LOADING, data, null)
        }
}

enum class StatusResponse{
        SUCCESS,
        ERROR,
        LOADING
}
