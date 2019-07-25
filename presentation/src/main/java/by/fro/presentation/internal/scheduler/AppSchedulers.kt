package by.fro.presentation.internal.scheduler

import by.fro.domain.Schedulers
import io.reactivex.Scheduler

class AppSchedulers : Schedulers {

    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()

    override val observeOn: Scheduler
        get() = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
}