package com.veskoiliev.codewars

import android.app.Activity
import android.app.Application
import android.support.annotation.VisibleForTesting
import com.veskoiliev.codewars.di.component.AppComponent
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
        initAppComponent().inject(this)
    }

    @VisibleForTesting
    protected open fun initAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .networkModule(NetworkModule(BuildConfig.API_BASE_URL))
                .app(this)
                .build()
    }

    override fun activityInjector() = activityInjector
}