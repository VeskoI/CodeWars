package com.veskoiliev.rule

import com.veskoiliev.codewars.data.local.db.Database
import org.junit.rules.ExternalResource
import javax.inject.Inject

class ClearAppDataRule @Inject constructor(private val database: Database) : ExternalResource() {

    override fun before() {
        database.clearAllTables()
    }
}
