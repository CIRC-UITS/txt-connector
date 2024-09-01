val edcVersion: String by project
val edcGroup: String by project

plugins {
    `java-library`
    `maven-publish`
}

dependencies {
    api("${edcGroup}:auth-spi:${edcVersion}")
    api("${edcGroup}:policy-engine-spi:${edcVersion}")
    api("${edcGroup}:contract-spi:${edcVersion}")
    testImplementation("${edcGroup}:junit:${edcVersion}")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.springframework:spring-web:5.3.15")
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