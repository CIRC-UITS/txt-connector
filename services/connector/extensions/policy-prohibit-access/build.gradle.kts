val edcVersion: String by project
val edcGroup: String by project

plugins {
    `java-library`
    `maven-publish`
}

dependencies {
    api("${edcGroup}:auth-spi:${edcVersion}")
    api("${edcGroup}:core-spi:${edcVersion}")
    api("${edcGroup}:policy-engine-spi:${edcVersion}")
    testImplementation("${edcGroup}:junit:${edcVersion}")
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