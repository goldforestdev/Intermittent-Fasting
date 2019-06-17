package com.goldforest.capdiet.view.dayResult

import com.goldforest.domain.usercase.GetDayResult
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DayResultPresenter (
    private val getDayResult: GetDayResult,
    private val uiContext: CoroutineContext = Dispatchers.Main,
    private val ioContext: CoroutineContext = Dispatchers.IO
): DayResultContract.Presenter, CoroutineScope {

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

    override fun test() {
        view?.test("hello")
    }
}