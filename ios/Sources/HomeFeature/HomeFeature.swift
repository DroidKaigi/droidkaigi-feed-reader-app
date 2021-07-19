import Component
import ComposableArchitecture
import Model
import Repository

public struct HomeState: Equatable {
    public var feedContents: [FeedContent]

    var message: String {
        "Finished! 🤖"
    }

    var topic: FeedContent? {
        feedContents.first
    }

    var listFeedContents: [FeedContent] {
        Array(feedContents.dropFirst())
    }

    public init(feedContents: [FeedContent]) {
        self.feedContents = feedContents
    }
}

public enum HomeAction {
    case selectFeedContent
    case tapFavorite(isFavorited: Bool, id: String)
    case answerQuestionnaire
}

public struct HomeEnvironment {
    public init() {}
}

public let homeReducer = Reducer<HomeState, HomeAction, HomeEnvironment> { _, action, _ in
    switch action {
    case .selectFeedContent:
        return .none
    case .tapFavorite(let isFavorited, let id):
        return .none
    case .answerQuestionnaire:
        return .none
    }
}
