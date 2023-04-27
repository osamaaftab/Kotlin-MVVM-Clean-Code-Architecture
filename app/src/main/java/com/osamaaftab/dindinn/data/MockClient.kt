package com.osamaaftab.dindinn.data

import android.content.Context
import com.osamaaftab.dindinn.BuildConfig
import okhttp3.*
import java.io.IOException
import java.io.InputStream

class MockClient(context: Context) : Interceptor {
    var context: Context = context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.endsWith("/getMenu") -> context.readJsonAsset("menu")
                else -> context.readJsonAsset("banner")
            }

            return Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }


    private fun Context.readJsonAsset(type: String): String {
        var inputStream = if (type.contentEquals("menu")) {
            assets.open("menu.json")
        } else {
            assets.open("banner.json")
        }
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }
}
