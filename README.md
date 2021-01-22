# gank_kotlin
apply plugin: 'maven'
//def mavenCommitId = rootProject.ext.mavenCommitId

uploadArchives {
    repositories.mavenDeployer {
//        if (mavenCommitId != null && !"".equals(mavenCommitId)) {
//            String lastCommitId = mavenCommitId.toString().substring(0, 6)
//        snapshotRepository(url: "http://nexus.os.adc.com/nexus/content/repositories/snapshots/") {
//            authentication(userName: "admin", password: "admin123")
//            android.libraryVariants.all { variant ->
//                def isFlavor = !variant.flavorName.isEmpty()
//                def buildType = "${variant.buildType.name}"
//                println "buildType is = $buildType"
//                def flavors = variant.productFlavors
//                def flavorTmp = flavors[0].name
//                def mavenName = "${flavors[0].name}"
//                println "mavenName is = $mavenName"
//                def aarName = "UserCenter_publicNew-china-green-release.aar"
//                println "aarName is = $aarName"
//                def aarPath = "$buildDir/outputs/aar/$aarName"
//                artifacts {
//                    if (buildType.contains("release")) {
//                        archives file: file(aarPath)
//                    }
//                }
//                addFilter(mavenName) { artifact, file ->
//                    //选取产物
//                    aarPath.contains(file.name)
//                }
//
//                pom(mavenName).withXml {
//                    def root = asNode()
//                    def depsNode = root["dependencies"][0] ?: root.appendNode("dependencies")
//                    def addDep = {
//                        if (it.group == null) return // Avoid empty dependency nodes
//                        def dependencyNode = depsNode.appendNode('dependency')
//                        dependencyNode.appendNode('groupId', it.group)
//                        dependencyNode.appendNode('artifactId', it.name)
//                        dependencyNode.appendNode('version', it.version)
//                        if (it.hasProperty('optional') && it.optional) {
//                            dependencyNode.appendNode('optional', 'true')
//                        }
//                    }
//                    // 添加基本依赖
//                    configurations.api.allDependencies.each addDep
//                    configurations.implementation.allDependencies.each addDep
//                    // 添加特殊依赖
//                    if (isFlavor) {
//                        configurations["${flavorTmp}Implementation"].allDependencies.each addDep
//                        configurations["${flavorTmp}Api"].allDependencies.each addDep
//                    }
//                }
//                pom(mavenName).groupId = "com.cheney.accountsdk"
//                pom(mavenName).artifactId = "cheney-$mavenName"
//                pom(mavenName).version = "8.8.0.8-SNAPSHOT"
//            }
//        }


        snapshotRepository(url: "http://nexus.os.adc.com/nexus/content/repositories/snapshots/") {
            authentication(userName: "admin", password: "admin123")
            android.libraryVariants.all { variant ->
                def isFlavor = !variant.flavorName.isEmpty()
                def buildType = "${variant.buildType.name}"
                println "buildType is = $buildType"
                def flavors = variant.productFlavors
                def flavorTmp = flavors[0].name
                def mavenName = "${flavors[0].name}-${flavors[1].name}"
                println "mavenName is = $mavenName"
                def aarName = "${flavors[0].name}-${flavors[1].name}-${variant.buildType.name}.aar"
                println "aarName is = $aarName"
                def aarPath = "$buildDir/outputs/aar/${project.name}-$aarName"
                artifacts {
                    if (buildType.contains("release")) {
                        println "aarPath is = $aarPath"
                        archives file: file(aarPath)
                    }
                }
                addFilter(mavenName) { artifact, file ->
                    println file.name + "=$aarPath"
                    //选取产物
                    aarPath.contains(file.name)
                }

                pom(mavenName).withXml {
                    def root = asNode()
                    def depsNode = root["dependencies"][0] ?: root.appendNode("dependencies")
                    def addDep = {
                        if (it.group == null) return // Avoid empty dependency nodes
                        def dependencyNode = depsNode.appendNode('dependency')
                        dependencyNode.appendNode('groupId', it.group)
                        dependencyNode.appendNode('artifactId', it.name)
                        dependencyNode.appendNode('version', it.version)
                        if (it.hasProperty('optional') && it.optional) {
                            dependencyNode.appendNode('optional', 'true')
                        }
                    }
                    // 添加基本依赖
                    configurations.api.allDependencies.each addDep
                    configurations.implementation.allDependencies.each addDep
                    // 添加特殊依赖
                    if (isFlavor) {
                        configurations["${flavorTmp}Implementation"].allDependencies.each addDep
                        configurations["${flavorTmp}Api"].allDependencies.each addDep
                    }
                }

                pom(mavenName).groupId = "com.cheney.account"
                pom(mavenName).artifactId = "cheney-$mavenName"
                pom(mavenName).version = "8.8.0.8-SNAPSHOT"
            }
        }
    }
}
//}

task sourcesJar(type: Jar) {
    from android.sourceSets.main.java.srcDirs
    classifier = 'sources'
}
