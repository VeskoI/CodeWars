package com.veskoiliev.codewars

import android.app.Activity
import android.app.Application
import android.support.annotation.VisibleForTesting
import com.veskoiliev.codewars.di.component.DaggerAppComponent
import com.veskoiliev.codewars.di.module.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class CodeWarsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .networkModule(getNetworkModule())
                .app(this)
                .build()
                .inject(this)

    }

    @VisibleForTesting
    protected open fun getNetworkModule() = NetworkModule(BuildConfig.API_BASE_URL)

    override fun activityInjector() = activityInjector
}