#! /bin/bash

FILE_PATH=`dirname $0`
FLAVOR=$1

cd "$FILE_PATH/../../"
if [[ $FLAVOR = "dev" ]]; then
    ./gradlew ios-framework:createXCFramework -Pbuildkonfig.flavor=dev
elif [[ $FLAVOR = "release" ]]; then
    ./gradlew ios-framework:createReleaseXCFramework -Pbuildkonfig.flavor=release
else
    echo "The flavor $FLAVOR not found."
    exit 1
fi

