plugins {
    kotlin("jvm") version "1.3.72"
    id("org.openapi.generator") version "4.3.1"
}

repositories {
    mavenCentral()
}

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/src/main/resources/test-api.yaml")
    outputDir.set("$buildDir/generated/openapi")
    modelPackage.set("com.parmet.test.api")
    logToStderr.set(true)
    ignoreFileOverride.set("$projectDir/src/main/resources/.openapi-generator-ignore")
    languageSpecificPrimitives.add("Feature")
    typeMappings.put("Feature", "Feature")
    additionalProperties.putAll(
        mapOf(
            "serializationLibrary" to "jackson",
            "collectionType" to "list",
            "enumPropertyNaming" to "UPPERCASE"
        )
    )
}

sourceSets["main"].java {
    srcDir("$buildDir/generated/openapi/src/main/kotlin")
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.2")
    implementation(kotlin("stdlib"))
}

tasks["compileKotlin"].dependsOn("openApiGenerate")
