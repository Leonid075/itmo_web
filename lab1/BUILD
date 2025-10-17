load("@rules_java//java:defs.bzl", "java_binary")

package(default_visibility = ["//visibility:public"])

java_binary(
    name = "main",
    main_class = "Main",
    srcs = glob(["src/java/main/Main.java"]),
    deps = [":server",],
)

java_library(
    name = "server",
    srcs = glob(["src/java/p3xi/*.java"]),
    deps = [":fastcgi",],
)

java_library(
    name = "fastcgi",
    srcs = glob(["src/java/com/fastcgi/*.java"]),
    deps = [],
)
