# adb demo

## start MainActivity

$ adb shell am start -S -n "io.github.jamelouis.adb_demo/.MainActivity"

## start MainActivity with intent

$ adb shell am start -S -n "io.github.jamelouis.adb_demo/.MainAcitivty" --es "key1" "value1" --es "key2" "value2"

# reference

* [How can I start a .love file from my desktop machine](https://bitbucket.org/MartinFelis/love-android-sdl2/wiki/FAQ_-_Frequently_Asked_Questions)
* [command line tools - android studio](https://developer.android.com/studio/command-line/)
