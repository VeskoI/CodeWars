package com.veskoiliev

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class TestInstrumentationRunner: AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestCodeWarsApplication::class.java.name, context)
    }
}