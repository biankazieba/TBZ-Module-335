// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id ("checkstyle")
}

checkstyle {
    toolVersion = ("10.12.7")
    configFile = rootProject.file("checkstyle.xml")
}
