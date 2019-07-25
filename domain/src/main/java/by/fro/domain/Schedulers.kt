package by.fro.domain

import io.reactivex.Scheduler

interface Schedulers {

    val subscribeOn: Scheduler

    val observeOn: Scheduler
}