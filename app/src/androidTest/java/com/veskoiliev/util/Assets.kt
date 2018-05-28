package com.veskoiliev.util

object Assets {

    const val GET_USER_RESPONSE =
            """{
      "username": "some_user",
      "name": "Awesome User 123",
      "honor": 544,
      "clan": "some clan",
      "leaderboardPosition": 134,
      "skills": [
        "ruby",
        "c#",
        ".net",
        "javascript",
        "coffeescript",
        "nodejs",
        "rails"
      ],
      "ranks": {
        "overall": {
          "rank": -3,
          "name": "3 kyu",
          "color": "blue",
          "score": 2116
        },
        "languages": {
          "javascript": {
            "rank": -3,
            "name": "3 kyu",
            "color": "blue",
            "score": 1819
          },
          "ruby": {
            "rank": -4,
            "name": "4 kyu",
            "color": "blue",
            "score": 1005
          },
          "coffeescript": {
            "rank": -4,
            "name": "4 kyu",
            "color": "blue",
            "score": 870
          }
        }
      },
      "codeChallenges": {
        "totalAuthored": 3,
        "totalCompleted": 230
      }
    }
    """

    const val COMPLETED_CHALLENGES_RESPONSE =
            """
                {
                    "totalPages": 1,
                    "totalItems": 3,
                    "data": [{
                        "id": "completedChallengeId1",
                        "name": "Completed Challenge 1",
                        "slug": "completed-challenge-1",
                        "completedLanguages": ["lua"],
                        "completedAt": "completedAtDate1"
                    }, {
                        "id": "completedChallengeId2",
                        "name": "Completed Challenge 2",
                        "slug": "completed-challenge-2",
                        "completedLanguages": ["javascript"],
                        "completedAt": "completedAtDate2"
                    }, {
                        "id": "completedChallengeId3",
                        "name": "Completed Challenge 3",
                        "slug": "completed-challenge-3",
                        "completedLanguages": ["javascript"],
                        "completedAt": "completedAtDate3"
                    }]
                }
            """

    const val EMPTY_COMPLETED_CHALLENGES_RESPONSE =
            """
                {
                    "totalPages": 1,
                    "totalItems": 3,
                    "data": []
                }
            """

    const val AUTHORED_CHALLENGES_RESPONSE =
            """
                {
                    "data": [{
                        "id": "authoredChallengeId1",
                        "name": "Authored Challenge 1",
                        "description": "Description 1",
                        "rank": -6,
                        "rankName": "6 kyu",
                        "tags": ["Fundamentals"],
                        "languages": ["ruby", "kotlin"]
                    }, {
                        "id": "authoredChallengeId2",
                        "name": "Authored Challenge 2",
                        "description": "Description 2",
                        "rank": -5,
                        "rankName": "5 kyu",
                        "tags": ["Fundamentals"],
                        "languages": ["coffeescript", "java"]
                    }, {
                        "id": "authoredChallengeId3",
                        "name": "Authored Challenge 3",
                        "description": "Description 3",
                        "rank": -6,
                        "rankName": "6 kyu",
                        "tags": ["Algorithms", "Mathematics", "Logic", "Numbers"],
                        "languages": ["haskell"]
                    }]
                }
            """
}