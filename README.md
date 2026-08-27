# Days Counter

A minimal streak counter. Open the app, tap "Reset" to start (or restart) your streak.
Add the 1×1 widget to your home screen — it shows just the number, transparent background, no "days" text.

## How to build the APK (free, ~5 minutes)

1. Install **Android Studio**: https://developer.android.com/studio
2. Unzip this project, then in Android Studio: **File → Open** → select the `DaysCounter` folder.
3. Let it sync. If it asks about the Gradle wrapper, let it use Android Studio's bundled Gradle — click OK/Sync.
4. Once synced: **Build → Build App Bundle(s) / APK(s) → Build APK(s)**.
5. When it finishes, click the "locate" link in the notification, or find the file at:
   `app/build/outputs/apk/debug/app-debug.apk`
6. Copy that file to your phone (USB, email, Drive, etc.) and tap it to install.
   You'll need to allow "install from unknown sources" once, since it's not from the Play Store.

## Adding the widget
Long-press your home screen → Widgets → find "Days Counter" → drag the small 1×1 tile onto your screen.

## Customizing
- Widget text color: edit `app/src/main/res/layout/widget_days.xml`, the `android:textColor` value (currently white — change if your wallpaper is light).
- Widget text size: same file, `android:textSize`.
- App icon / colors: `app/src/main/res/drawable/ic_launcher_foreground.xml` and `colors.xml`.
