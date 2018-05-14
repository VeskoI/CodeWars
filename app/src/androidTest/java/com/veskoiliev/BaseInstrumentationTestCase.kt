package com.veskoiliev

import android.support.annotation.CallSuper
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.veskoiliev.codewars.ui.search.SearchUserActivity
import com.veskoiliev.rule.ClearAppDataRule
import com.veskoiliev.rule.mockwebserver.MockWebServerRule
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith

/**
 * Base class for all instrumentation tests.
 *
 * Prepares the app to run BDD-style tests and handles configuration needed before running each test.
 */
@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentationTestCase {

    private val activityRule = ActivityTestRule(SearchUserActivity::class.java, true, false)
    private val serverRule = MockWebServerRule()

    @Rule
    @JvmField
    val rules: TestRule = RuleChain
            .outerRule(ClearAppDataRule())
            .around(serverRule)
            .around(activityRule)

    lateinit var given: Given
    lateinit var `when`: When
    lateinit var then: Then

    @Before
    @CallSuper
    fun setUp() {
        given = Given(serverRule)
        `when` = When(activityRule)
        then = Then()
    }
}
