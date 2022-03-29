package com.reza.appnikestore.common

import com.reza.appnikestore.R
import org.json.JSONObject
import retrofit2.HttpException

class NikeExceptionMapper {
    companion object {
        fun map(throwable: Throwable): NikeException {
            if (throwable is HttpException) {
                try {
                    val errorJsonObject = JSONObject(throwable.response()?.errorBody()!!.string())
                    val errorMessage = errorJsonObject.getString("message")
                    return NikeException(
                        if (throwable.code() == 401) NikeException.Type.AUTH else NikeException.Type.SIMPLE,
                        serverMessage = errorMessage
                    )
                } catch (exception: Exception) {

                }
            }
            return NikeException(NikeException.Type.SIMPLE, R.string.unknown_error)
        }
    }
}