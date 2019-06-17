package com.github.abdallahabdelfattah13.data.util


/**
 * Created by AbdAllah AbdElfattah on 13/06/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 *
 * Based on: [Goolge Samples](https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Resource.kt)
 */
/**
 * A generic class that holds a value with its loading status.
 * @param <T>The type of the data to be delivered</T>
 */
sealed class Resource<out T> {

    class InitLoadingState<T> : Resource<T>()
    class LocalSuccess<T>(val data: T) : Resource<T>()
    class LocalUpdate<T>(val data: T) : Resource<T>()
    class RemoteSuccess<T>(val data: T) : Resource<T>()
    class RemoteFail<T>(val data: T, val throwable: Throwable, val message: String) : Resource<T>()

    companion object {

        fun <T> loading(): Resource<T> = InitLoadingState()

        fun <T> localSuccess(data: T): Resource<T> = LocalSuccess(data)

        fun <T> localUpdate(data: T): Resource<T> = LocalUpdate(data)

        fun <T> remoteSuccess(data: T): Resource<T> = RemoteSuccess(data)

        fun <T> error(data: T, throwable: Throwable, message: String): Resource<T> =
            RemoteFail(data, throwable, message)
    }
}

