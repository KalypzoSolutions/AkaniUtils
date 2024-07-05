plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.shadow)
}

group = "it.einjojo.akani"
version = "1.0.1"


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://mvn.lumine.io/repository/maven-public/")
    maven {
        url = uri("https://jitpack.io")
        content {
            includeGroupByRegex("com\\.github\\.(ben-manes|hamza-cskn|retrooper|KalypzoSolutions|Anon8281).*")
        }
    }
    maven("https://libraries.minecraft.net/")
}

dependencies {
    implementation(libs.jbannotations)
    compileOnly(libs.paper)
    compileOnly(libs.mojangauthlib)
    api(libs.obliviateinvcore)
    api(libs.obliviateinvpagination)
    api(libs.obliviateinvadvancedslot)
    api(libs.acf)
    api(libs.caffeine)
    api(libs.gson)
    api(libs.nbtapi)
    implementation(platform(libs.fawe))
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Core")
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit") { isTransitive = false }
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = rootProject.name
            version = version
            from(components["java"])
        }
    }
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(mapOf("version" to version))
        }

    }
    assemble {
        dependsOn("shadowJar")
    }
    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.jar")
        relocate("mc.obliviate", "it.einjojo.akani.util")
        relocate("co.aikar", "it.einjojo.akani.util.commands")
        relocate("com.google.gson", "it.einjojo.akani.util.gson")
        relocate("com.github.ben-manes.caffeine", "it.einjojo.akani.util.caffeine")
        relocate("com.github.Anon8281.universalScheduler", "it.einjojo.akani.util.scheduler")
    }
}

