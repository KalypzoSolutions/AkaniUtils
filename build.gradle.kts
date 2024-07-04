plugins {
    id("java-library")
}

group = "it.einjojo.akani"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://mvn.lumine.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://libraries.minecraft.net/")
}

dependencies {
    compileOnly(libs.paper)
    compileOnly(libs.jbannotations)
    api(libs.obliviateinvcore)
    api(libs.obliviateinvpagination)
    api(libs.obliviateinvadvancedslot)
    api(libs.acf)
    api(libs.caffeine)
    api(libs.gson)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}