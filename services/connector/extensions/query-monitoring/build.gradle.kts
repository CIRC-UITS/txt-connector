val edcVersion: String by project
val edcGroup: String by project

plugins {
    `java-library`
    `maven-publish`
}

dependencies {
    api("${edcGroup}:auth-spi:${edcVersion}")
    api("${edcGroup}:core-spi:${edcVersion}")
    implementation("${edcGroup}:sql-core:${edcVersion}")
    api("${edcGroup}:policy-engine-spi:${edcVersion}")
    api("${edcGroup}:control-plane-spi:${edcVersion}")
    implementation("${edcGroup}:api-core:${edcVersion}")
    implementation("org.postgresql:postgresql:42.2.24")
    implementation("${edcGroup}:data-plane-instance-store-sql:${edcVersion}")
    implementation("${edcGroup}:sql-pool-apache-commons:${edcVersion}")
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