# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#请避免混淆Bugly，在Progua
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#ｏｋｈｔｔｐ的文件  只有不使用R8编译 器时才会有问题 去官网
-keep class com.zhu.cactus.POJO.** { *; }

-keep class com.zhu.cactus.ONE.**{*;}
 -keep class com.gyf.cactus.entity.* {*;}
-keepnames class androidx.navigation.fragment.NavHostFragment
#　ｇｌｉｄ文件
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

