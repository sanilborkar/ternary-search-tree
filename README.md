# Ternary Search Tree

## Overview

## Creating a JAR Using Gradle in IntelliJ
After any changes to the project, please clean and rebuild the JAR by following the steps given below:
1. Go to **View > Tool Windows > Gradle**.
2. In the Gradle window that opens, expand **Tasks > build**.
3. Double-click on the **clean** option to clean the current build artifacts.
3. Once the "clean" process is done, double-click on the **build** option to start the build process.
4. Once the "build" process is done, the JAR file will be generated and placed under **builds/libs**.

## Importing JAR in Your Project

### IntelliJ
1. Open **File > Project Structure > Project Settings > Modules**.
2. In the right pane, select the "**Dependencies**" tab.
3. Click on the "**+**" and select "**JARs or directories**".
4. Select "**ternary-search-jar-1.0.jar**" in the pop-up dialog box.
5. Click "Apply".
6. Click "OK".
7. You will see the added JAR under your "**PROJ_DIRECTORY/libs**" folder.