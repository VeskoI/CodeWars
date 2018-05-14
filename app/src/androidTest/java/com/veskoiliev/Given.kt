package com.veskoiliev

import com.veskoiliev.arrangements.CodeWarsServer
import com.veskoiliev.rule.mockwebserver.MockWebServerRule

class Given(serverRule: MockWebServerRule) {
    val codeWarsServer: CodeWarsServer = CodeWarsServer(serverRule)
}