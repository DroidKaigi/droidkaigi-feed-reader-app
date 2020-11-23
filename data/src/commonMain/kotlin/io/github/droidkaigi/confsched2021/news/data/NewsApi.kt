package io.github.droidkaigi.confsched2021.news.data

import io.github.droidkaigi.confsched2021.news.News
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface NewsApi {
    suspend fun fetch(): List<News>
}

fun fakeNewsApi(): NewsApi = object : NewsApi {
    override suspend fun fetch(): List<News> {
        return list
    }

    // cache for fixing test issue
    @OptIn(ExperimentalStdlibApi::class)
    val list = run {
        val response = """[
  {
    "id": "2020-07-22-droidkaigi2020",
    "date": "2020-07-22",
    "collection": "Blog",
    "image": "/static/artwork-abb7c2bf0354cf0a91da5f6871250650.jpg",
    "media": "BLOG",
    "ja": {
      "title": "DroidKaigi 2020 活動報告 by @mhidaka",
      "link": "https://medium.com/droidkaigi/droidkaigi-2020-report-940391367b4e"
    }
  },
  {
    "id": "2020-07-20-Testing-as-a-Culture",
    "date": "2020-07-20",
    "collection": "DroidKaigi 2020",
    "image": "https://i.ytimg.com/vi/kcSNPS-PVO0/maxresdefault.jpg",
    "media": "YOUTUBE",
    "en": {
      "title": "Testing as a Culture",
      "link": "https://www.youtube.com/watch?v=kcSNPS-PVO0&feature=youtu.be"
    },
    "ja": {
      "title": "Testing as a Culture",
      "link": "https://www.youtube.com/watch?v=kcSNPS-PVO0&feature=youtu.be"
    }
  },
  {
    "id": "2020-07-15-designer",
    "date": "2020-07-15",
    "collection": "Blog",
    "image": "/static/artwork-abb7c2bf0354cf0a91da5f6871250650.jpg",
    "media": "BLOG",
    "ja": {
      "title": "DroidKaigiでデザイナーを募集します by @mutoatu",
      "link": "https://medium.com/droidkaigi/droidkaigi%E3%81%A7%E3%83%87%E3%82%B6%E3%82%A4%E3%83%8A%E3%83%BC%E3%82%92%E5%8B%9F%E9%9B%86%E3%81%97%E3%81%BE%E3%81%99-f4b59715b96c"
    }
  },
  {
    "id": "2020-07-09-droidkaigifm",
    "date": "2020-07-09",
    "collection": "DroidKaigi.fm",
    "image": "/static/artwork-abb7c2bf0354cf0a91da5f6871250650.jpg",
    "media": "PODCAST",
    "ja": {
      "title": "2. Android 11 Talks",
      "link": "https://droidkaigi.jp/fm/episode/2"
    }
  },
  {
    "id": "2020-07-05-droidkaigifm",
    "date": "2020-07-05",
    "collection": "DroidKaigi.fm",
    "image": "/static/artwork-abb7c2bf0354cf0a91da5f6871250650.jpg",
    "media": "PODCAST",
    "ja": {
      "title": "1. Android Studio 4.xとAndroid 11",
      "link": "https://droidkaigi.jp/fm/episode/1"
    }
  },
  {
    "id": "2020-06-11-droidkaigi-on-air",
    "date": "2020-06-11",
    "collection": "DroidKaigi On Air",
    "image": "https://i.ytimg.com/vi/Haghkqh8d3k/maxresdefault.jpg",
    "media": "YOUTUBE",
    "ja": {
      "title": "DroidKaigi On Air: Android 11&Android Studio 4.0",
      "link": "https://youtu.be/Haghkqh8d3k"
    }
  },
  {
    "id": "2020-06-01-Android-UIs-Patterns-Practices-Pitfalls",
    "date": "2020-06-01",
    "collection": "DroidKaigi 2020",
    "image": "https://i.ytimg.com/vi/mUlQAfybAkk/maxresdefault.jpg",
    "media": "YOUTUBE",
    "en": {
      "title": "Android UIs: Patterns, Practices, Pitfalls",
      "link": "https://www.youtube.com/watch?v=mUlQAfybAkk&feature=youtu.be"
    },
    "ja": {
      "title": "Android UIs: Patterns, Practices, Pitfalls",
      "link": "https://www.youtube.com/watch?v=mUlQAfybAkk&feature=youtu.be"
    }
  },
  {
    "id": "2020-05-11-The-revival-of-our-system-that-has-almost-died",
    "date": "2020-05-11",
    "collection": "DroidKaigi 2020",
    "image": "https://i.ytimg.com/vi/hj96FCsavPY/maxresdefault.jpg",
    "media": "YOUTUBE",
    "en": {
      "title": "The revival of our system that has almost died by URI.",
      "link": "https://www.youtube.com/watch?v=hj96FCsavPY&feature=youtu.be"
    },
    "ja": {
      "title": "瀕死のシステムが強くなって復活する話",
      "link": "https://www.youtube.com/watch?v=hj96FCsavPY&feature=youtu.be"
    }
  },
  {
    "id": "2020-05-01-Implement-unit-testing-quickly",
    "date": "2020-05-01",
    "collection": "DroidKaigi 2020",
    "image": "https://i.ytimg.com/vi/OHKCD9-uYLc/maxresdefault.jpg",
    "media": "YOUTUBE",
    "en": {
      "title": "Implement unit testing quickly by automatic generation",
      "link": "https://www.youtube.com/watch?v=OHKCD9-uYLc&feature=youtu.be"
    },
    "ja": {
      "title": "自動生成でさくさく実装するユニットテスト",
      "link": "https://www.youtube.com/watch?v=OHKCD9-uYLc&feature=youtu.be"
    }
  }
]"""
        val articles = Json.decodeFromString<List<KtorNewsApi.ArticleResponse>>(
            response
        )
            .map { response ->
                response.toNews()
            }
        articles
    }
}

