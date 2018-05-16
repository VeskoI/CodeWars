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
}