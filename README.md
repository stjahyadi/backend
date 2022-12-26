# Backend

Backend Service

### Building and running tests locally

To build and test the project, run the command `mvn clean install`.
This will create a container for the DB and other resources, run the unit and integration tests, and then stop the container.

### Starting the app for local dev

Firstly, you should know that our app relies on a PostgreSQL DB and a PubSub connection.
Therefore, to start the app for local dev, you need to connect to local instances of these resources. \
To do so, you can start local Docker containers in 2 ways:

* via the terminal, run the command: `mvn docker:start -Plocal`
* via IntelliJ Maven sidebar with `local` profile enabled

After the resources are up, you can start the app locally in 2 ways:

* via the terminal, run the command: `mvn spring-boot:run -f backend -Dspring-boot.run.profiles=local`
* via IntelliJ, run: `com.bol.prominence.BackendApplicationKt.main`

The application will start at http://localhost:8080

The swagger UI will start at http://localhost:8080/swagger-ui.html

**NOTE:** Remember ***not*** to commit the changes in `application-dev.yml` !

### Code Style

`ktlint` is used to ensure that our code follows the Kotlin coding conventions.
For more information, go to https://github.com/pinterest/ktlint  \
The `ktlint:check` goal is run by default at the `verify` phase of the Maven default lifecycle. It will list the violations. \
Before you commit your changes, make sure to run `mvn ktlint:format` to format the code.

### Code Quality

Our code is checked for quality issues and test coverage through Sonar. \
Under the hood, detekt analyzes the code for smells and jacoco reports the coverage. \
Before you commit your changes, make sure to run `mvn detekt:check` to locally analyze the code.

### Patching

In certain situations, we might need to deploy a hotfix from the commit that is deployed on pro. Consider this scenario:

1. `main` branch contains more change-lists that are on `stg` but not on `pro`
2. Now, a bug appears on `pro` and needs to be fixed ASAP!
3. We check out the commit from which we deployed to `pro`
4. We create a branch with a name that matches `patch/EXC-xyz_hotfix`
5. We implement the fix.
6. We commit and push the changes to the remote repository.
7. The CI/CD pipeline that will start should have `deploy-to-stg` and `deploy-to-pro` manual jobs.

### Tasks
1. Create CRUD from API to database. Check the table is not created
2. Create PubSub with Emulator
3. Create public API
4. Create cache for public API
5. Build CICD
