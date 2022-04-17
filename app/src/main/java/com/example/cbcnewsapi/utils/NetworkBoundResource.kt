package com.codinginflow.simplecachingexample.util

import kotlinx.coroutines.flow.*

// will emit the 'resource' class over and over..
// (two-parameters for re-usability)
// sometimes, the data we retrieve from the REST-API is not always the same as that in the ROOM..
// 'inline' for higher-order-function (streamlines the inner logic)..
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,                       // keep getting the updated data
    crossinline fetch: suspend () -> RequestType,                    // one list of restaurant..
    crossinline saveFetchResult: suspend (RequestType) -> Unit,       // stores the data in the d/b
    crossinline shouldFetch: (ResultType) -> Boolean = { true }      // whether to update the stale data in the d/b..
) = flow {

    val data = query().first()              // #1. one list of restaurant from the d/b..

    // #2. Checks if it is time to update the cached-data..
    val flow = if (shouldFetch(data)) {

        // #3. Progress-bar
        emit(Resource.Loading(data))

        // #4. network-reqeust..
        try {
            saveFetchResult(fetch())

            // #5. save in the D/B..
            query().map {
                Resource.Success(it)
            }

        } catch (throwable: Throwable) {
            // if network-request fails, old cached-data.. + error (the user knows the there is some error)
            query().map {
                Resource.Error(throwable, it)
            }
        }

        // #5: provides cached-data (without making any network request)
    } else {
        query().map {
            Resource.Success(it)
        }
    }

    emitAll(flow)

}