# Hotbar Keys Contributing Guide

## Repository Setup

To develop locally, fork the `Hdoc1509/hotbar-keys` repository and clone it in
your local machine:

```bash
git clone https://github.com/Hdoc1509/hotbar-keys.git --depth=1
```

If you have [Github CLI](https://cli.github.com/) installed, you can fork and
clone the repository with a single command:

```bash
gh repo fork Hdoc1509/hotbar-keys --clone=true -- --depth=1
```

## Required Tools

-   [`Eclipse Temurin JKD 21.0.5 `](https://adoptium.net/temurin/releases/?package=jdk)

### Editor / IDE Setup

#### IntelliJ IDEA

1. Open the template's root folder as a new project in IDEA. This is the folder
   that contains this README.md file and the gradlew executable.
2. If your default JVM/JDK is not Java 21 you will encounter an error when
   opening the project. This error is fixed by going to
   `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`
   and changing the value to a valid Java 21 JVM. You will also need to set the
   Project SDK to Java 21. This can be done by going to
   `File > Project Structure > Project SDK`. Once both have been set open the
   Gradle tab in IDEA and click the refresh button to reload the project.
3. Open your Run/Debug Configurations. Under the `Application` category there
   should now be options to run Fabric and NeoForge projects. Select one of the
   client options and try to run it.
4. Assuming you were able to run the game in step 5 your workspace should now be
   set up.
5. Install [Google Java Format plugin](https://plugins.jetbrains.com/plugin/8527-google-java-format)

#### Neovim

1. LSP integration with [nvim-jdtls](https://github.com/mfussenegger/nvim-jdtls)
   or [nvim-java](https://github.com/nvim-java/nvim-java)
2. Syntax highlighting with
   [nvim-treesitter](https://github.com/nvim-treesitter/nvim-treesitter),
   [tree-sitter-groovy](https://github.com/murtaza64/tree-sitter-groovy) and
   [tree-sitter-java](https://github.com/tree-sitter/tree-sitter-java)
3. Formatting with [formatter.nvim](https://github.com/mhartington/formatter.nvim)
   or [conform.nvim](https://github.com/stevearc/conform.nvim) and
   [google-java-format](https://github.com/google/google-java-format) as
   formatter
4. Be sure to set up the correct JDK Runtime according to `nvim-jdtls` or
   `nvim-java`
5. Run `./gradlew build` to build the project
6. If the build succeeds, your workspace should now be set up

## Development Guide

The majority of the mod should be developed in the`common` project. The `common`
project is compiled against the vanilla game and is used to hold code that is
shared between the different loader-specific versions of your mod. The `common`
project has no knowledge or access to ModLoader specific code, apis, or
concepts. Code that requires something from a specific loader must be done
through the project that is specific to that loader, such as the `fabric` or
`neoforge` projects.

Loader specific projects such as the `fabric` and `neoforge` project are used to
load the `common` project into the game. These projects also define code that is
specific to that loader. Loader specific projects can access all the code in the
`common` project. It is important to remember that the `common` project can not
access code from loader specific projects.

## Troubleshooting

### Neovim

#### LSP Start

If you have trouble with Neovim, you can try the following:

-   Delete folders:
    -   `bin`
    -   `.settings`
    -   `.gradle`
    -   `build`
    -   `.factorypath`
    -   Any other `cache` folders that are created for the project
-   Delete files:
    -   `.classpath`
    -   `.project`

Once you have done this, you can try to open the project again.
