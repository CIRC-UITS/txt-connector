plugins {
    `java-library`
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}


val edcVersion: String by project
val edcGroup: String by project


dependencies {
    // Control-Plane
    implementation("${edcGroup}:control-plane-core:${edcVersion}")
    implementation("${edcGroup}:management-api:${edcVersion}")
    implementation("${edcGroup}:api-observability:${edcVersion}")
    implementation("${edcGroup}:configuration-filesystem:${edcVersion}")
    implementation("${edcGroup}:control-plane-aggregate-services:${edcVersion}")
    implementation("${edcGroup}:http:${edcVersion}")
    implementation("${edcGroup}:dsp:${edcVersion}")
    implementation("${edcGroup}:json-ld:${edcVersion}")
    implementation("org.eclipse.edc.aws:provision-aws-s3:${edcVersion}")

    // Data Management API Key
    api("${edcGroup}:auth-tokenbased:${edcVersion}")

    // OAuth2 IAM
    api("${edcGroup}:oauth2-core:${edcVersion}")
    api("${edcGroup}:vault-filesystem:0.6.0")

    //Extensions
    api(project(":extensions:policy-location-restricted"))
    //api(project(":extensions:policy-n-times-usage"))
    api(project(":extensions:policy-prohibit-access"))
    api(project(":extensions:policy-time-interval"))
    api(project(":extensions:query-monitoring"))
    api(project(":extensions:flyway-postgres"))
    api(project(":extensions:participant-location"))


    // Control-plane to Data-plane
    implementation("${edcGroup}:transfer-data-plane-spi:${edcVersion}")
    implementation("${edcGroup}:transfer-data-plane-signaling:${edcVersion}")
    //api("${edcGroup}:transfer-pull-http-receiver:${edcVersion}")
    implementation("${edcGroup}:data-plane-selector-core:${edcVersion}")
    implementation("${edcGroup}:data-plane-selector-client:${edcVersion}")
    implementation("${edcGroup}:data-plane-selector-api:${edcVersion}")
    implementation("${edcGroup}:data-plane-signaling-transform:${edcVersion}")
    implementation("${edcGroup}:control-plane-api-client:${edcVersion}")
    implementation("org.eclipse.edc.aws:data-plane-aws-s3:${edcVersion}")
    implementation("${edcGroup}:control-plane-api:${edcVersion}")
    implementation("${edcGroup}:control-plane-spi:${edcVersion}")
    implementation("${edcGroup}:control-plane-core:${edcVersion}")
    implementation("${edcGroup}:control-plane-api-client-spi:${edcVersion}")
    implementation("${edcGroup}:transfer-spi:${edcVersion}")
    implementation("${edcGroup}:data-plane-control-api:${edcVersion}")

    implementation("${edcGroup}:transfer-pull-http-receiver:${edcVersion}")


    // Data-plane
    api("${edcGroup}:data-plane-http:${edcVersion}")
    api("${edcGroup}:data-plane-public-api:${edcVersion}")
    //api("${edcGroup}:data-plane-framework:${edcVersion}")
    implementation("${edcGroup}:data-plane-core:${edcVersion}")
    implementation("${edcGroup}:data-plane-util:${edcVersion}")


    implementation("${edcGroup}:data-plane-selector-control-api:${edcVersion}")
    implementation("${edcGroup}:data-plane-signaling-api:${edcVersion}")
    implementation("${edcGroup}:data-plane-signaling-client:${edcVersion}")




}

application {
    mainClass.set("org.eclipse.edc.boot.system.runtime.BaseRuntime")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveFileName.set("app.jar")
}