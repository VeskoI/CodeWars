package com.veskoiliev.codewars

import android.app.Activity
import android.app.Application
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import com.veskoiliev.codewars.di.component.AppComponent
import com.veskoiliev.codewars.di.component.DaggerAppComponent
import com.veskoiliev.codewars.di.module.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class CodeWarsApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

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

    override fun supportFragmentInjector() = fragmentInjector
}