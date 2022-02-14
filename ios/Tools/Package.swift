// swift-tools-version:5.3

import PackageDescription

let package = Package(
    name: "Tools",
    products: [
    ],
    dependencies: [
        .package(url: "https://github.com/mono0926/LicensePlist.git", .exact("3.17.0")),
        .package(url: "https://github.com/SwiftGen/SwiftGen.git", .exact("6.5.1")),
        .package(url: "https://github.com/realm/SwiftLint.git", .exact("0.46.2")),
        .package(url: "https://github.com/tuist/xcbeautify.git", .exact("0.11.0")),
    ],
    targets: [
    ]
)
