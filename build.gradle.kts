plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.shadow)
}

group = "it.einjojo.akani"
version = "1.1.0"


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
    implementation(libs.obliviateinvpagination)
    implementation(libs.obliviateinvadvancedslot)
    implementation(libs.obliviateinvcore)
    implementation(libs.acf)
    implementation(libs.caffeine)
    implementation(libs.gson)
    implementation(libs.jedis)
    implementation(libs.nbtapi)

    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = rootProject.name
            version = version
            artifact(tasks.shadowJar)
        }
    }
    repositories {
        maven {
            name = "akani-repo"
            url = uri("https://repo.akani.dev/releases")
            credentials {
                username = findProperty("AKANI_REPO_USER") as String? ?: System.getenv("AKANI_REPO_USER")
                password = findProperty("AKANI_REPO_PASS") as String? ?: System.getenv("AKANI_REPO_PASS")
            }
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
        archiveFileName.set("${project.name}.jar")
        relocate("mc.obliviate", "it.einjojo.akani.util")
        relocate("co.aikar", "it.einjojo.akani.util")
        relocate("com.google.gson", "it.einjojo.akani.util.gson")
        relocate("com.github.ben-manes.caffeine", "it.einjojo.akani.util.caffeine")
        relocate("com.github.Anon8281.universalScheduler", "it.einjojo.akani.util.scheduler")
        relocate("de.tr7zw.nbtapi", "it.einjojo.akani.util.nbtapi")
        relocate("redis.clients", "it.einjojo.akani.util.jedis")
    }
}

