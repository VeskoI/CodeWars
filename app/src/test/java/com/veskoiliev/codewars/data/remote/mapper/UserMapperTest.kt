package com.veskoiliev.codewars.data.remote.mapper

import com.veskoiliev.codewars.data.local.model.User
import com.veskoiliev.codewars.testdata.TestUserModel
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers.samePropertyValuesAs
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserMapperTest {

    private val underTest = UserMapper()

    @Test
    fun willMapUserCorrectlyWhenAllFieldsPresent() {
        val result = underTest.map(TestUserModel.userModel)

        assertThat(result, samePropertyValuesAs(User(
                username = TestUserModel.userName,
                name = TestUserModel.name,
                rank = TestUserModel.overallRank,
                leaderBoardPosition = TestUserModel.leaderBoardPosition,
                bestLanguage = TestUserModel.bestLanguageName,
                bestLanguagePoints = TestUserModel.bestLanguageScore
        )))
    }

    @Test
    fun willMapUserCorrectlyWhenOptionalInformationIsMissing() {
        val result = underTest.map(TestUserModel.userModelWithoutAnyLanguages)

        assertThat(result, samePropertyValuesAs(User(
                username = TestUserModel.userName,
                name = TestUserModel.name,
                rank = TestUserModel.overallRank,
                leaderBoardPosition = TestUserModel.leaderBoardPosition,
                bestLanguage = null,
                bestLanguagePoints = null
        )))
    }

    @Test
    fun willUseUsernameIfNameNotAvailable() {
        val userWithoutName = TestUserModel.userWithoutName

        val result = underTest.map(userWithoutName)

        assertEquals(userWithoutName.username, result.name)
        assertEquals(userWithoutName.username, result.username)
    }
}