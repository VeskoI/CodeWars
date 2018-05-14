package com.veskoiliev.codewars

import android.app.Activity
import android.app.Application
import com.veskoiliev.codewars.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CodeWarsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .build()
                .inject(this)

    }

    override fun activityInjector() = activityInjector
}