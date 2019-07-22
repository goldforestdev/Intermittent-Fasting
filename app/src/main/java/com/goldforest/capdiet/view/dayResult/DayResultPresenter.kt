package com.goldforest.capdiet.view.dayResult

import android.util.Log
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.usercase.CreateDayResult
import com.goldforest.domain.usercase.GetDayResult
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DayResultPresenter (
    private val getDayResult: GetDayResult,
    private val createDayResult: CreateDayResult,
    private val uiContext: CoroutineContext = Dispatchers.Main,
    private val ioContext: CoroutineContext = Dispatchers.IO
): DayResultContract.Presenter, CoroutineScope {

    private val TAG = DayResultPresenter::class.java.simpleName

    override val coroutineContext: CoroutineContext = Job() + ioContext

    private var view: DayResultContract.View? = null

    override fun subscribe(v: DayResultContract.View) {
        view = v
    }

    override fun unsubscribe() {
        view = null
    }

    override fun getDayResult(id: Long) {
        launch {
            val dayResult = withContext(ioContext) {
                getDayResult.get(id)
            }

            withContext(uiContext) {
                view?.onDayResultLoaded(dayResult)
            }
        }
    }

    override fun save(dayResult: DayResult) {
        Log.d(TAG, "[GF] save1 - $dayResult")
        launch {
            withContext(ioContext) {
                Log.d(TAG, "[GF] save - $dayResult")
                createDayResult.save(dayResult)
            }

            withContext(uiContext) {
                view?.onDayResultLoaded(dayResult)
            }
        }
    }

    override fun update(dayResult: DayResult) {

    }
}