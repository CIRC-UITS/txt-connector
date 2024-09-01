val edcVersion: String by project
val edcGroup: String by project
val ipGroup: String by project
val ipVersion: String by project

plugins {
    `java-library`
    `maven-publish`
}

repositories{
    mavenCentral()
}

dependencies {
    api("${edcGroup}:auth-spi:${edcVersion}")
    api("${edcGroup}:policy-engine-spi:${edcVersion}")
}

val txtEdcExtensionGroup: String by project
group = txtEdcExtensionGroup

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
        }
    }
}
