# Functional Programming in Scala

## Scala & Sbt Environment

**~/.sbt/repositories**

```shell
[repositories]
#local
public: https://maven.aliyun.com/nexus/content/groups/public/
typesafe:https://dl.bintray.com/typesafe/ivy-releases/ , [organization]/[module]/(scala_[scalaVersion]/)(sbt_[sbtVersion]/)[revision]/[type]s/[artifact](-[classifier]).[ext], bootOnly
ivy-sbt-plugin:https://dl.bintray.com/sbt/sbt-plugin-releases/, [organization]/[module]/(scala_[scalaVersion]/)(sbt_[sbtVersion]/)[revision]/[type]s/[artifact](-[classifier]).[ext]
sonatype-oss-releases

sonatype-oss-snapshots
```
**Intellij IDEA Configure**

Setting - Build - Build Tools - sbt - Launcher(sbt-launch.jar) - Custom :
```shell
/usr/share/sbt/bin/sbt-launch.jar
```
## Materials


- [笔记：Functional Programming in Scala](https://facaiy.com/tech/2017/04/23/Functional-Programming-in-Scala.html)

**Source Code**
- https://github.com/fpinscala/fpinscala
- https://github.com/afonsograca/Functional-Programming-Principles-in-Scala
- https://github.com/robertberry/Functional-Programming-in-Scala-Exercises

**Scala Online Exercise**
- https://scastie.scala-lang.org/
