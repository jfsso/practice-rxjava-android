practice-rxjava-android
=======================

Playing with RxJava on Android.


A listener registers for changes on the default SharedPreferences and publishes those changes to RxJava's observers.
It uses the BehaviorSubject to emit the last update (or default update) when an Observer is subscribed and then emits updates when the checkbox preference is enabled/disabled.

