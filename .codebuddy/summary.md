# Project Summary

## Overview of Languages, Frameworks, and Main Libraries Used
This project is primarily developed using Java, leveraging the Gradle build tool for project management and dependency resolution. The project appears to be related to modding, likely for a game (possibly Minecraft given the directory structure and file names). The usage of `.toml` files suggests that it may utilize the Neoforge modding framework.

### Key Technologies:
- **Language:** Java
- **Build Tool:** Gradle
- **Modding Framework:** Neoforge

## Purpose of the Project
The project seems to be a modding framework or a mod itself, specifically designed to enhance or modify gameplay elements. It includes functionalities for bot management, command handling, and entity definitions, indicating that it may allow users to create or control bots within the game environment.

## List of Build/Configuration/Project Files
- **Build Files:**
  - `/build.gradle`
  
- **Configuration Files:**
  - `/gradle.properties`
  - `/gradle/wrapper/gradle-wrapper.properties`
  
- **Project Files:**
  - `/settings.gradle`
  
## Source File Directories
The source files can be found in the following directory:
- `/src/main/java/dev/greendev/neobots`

### Subdirectories:
- `/src/main/java/dev/greendev/neobots/bot`
- `/src/main/java/dev/greendev/neobots/client`
- `/src/main/java/dev/greendev/neobots/command`
- `/src/main/java/dev/greendev/neobots/entity`
- `/src/main/java/dev/greendev/neobots/event`

## Documentation Files Location
Documentation files are located in the following directory:
- `/run/config`
  - `fml.toml`
  - `neobots-common.toml`
  - `neoforge-client.toml`
  - `neoforge-common.toml`
  - `neoforge-server.toml`
- Additionally, there are README files located in:
  - `/run/saves/New World (1)/serverconfig/readme.txt`
  - `/run/saves/New World (2)/serverconfig/readme.txt`
  - `/run/saves/New World/serverconfig/readme.txt`
  
## Additional Notes
The project contains logs and save files which suggest that it is actively used and tested. The presence of multiple "New World" save directories indicates the capability of creating multiple game instances or sessions.