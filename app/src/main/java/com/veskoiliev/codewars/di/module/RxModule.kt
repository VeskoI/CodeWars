package com.veskoiliev.codewars.di.module

import com.veskoiliev.codewars.di.component.NamedParams
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class RxModule {

    @Provides
    @Named(NamedParams.RX_OBSERVE_THREAD)
    fun provideRxObserveThread(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(NamedParams.RX_WORKER_THREAD)
    fun provideRxWorkerThread(): Scheduler = Schedulers.io()
}