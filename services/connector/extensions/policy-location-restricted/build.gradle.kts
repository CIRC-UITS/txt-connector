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
    api("${edcGroup}:contract-spi:${edcVersion}")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
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
