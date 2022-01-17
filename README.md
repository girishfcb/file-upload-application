# Full stack application with Angular frontend and Spring boot backend.

Sample full stack application with Angular, TypeScript on frontend and Spring Boot and Java 8+ backend communicating using REST services. Liquibase was used for database migration scripts.

Various features of Angular like routing, security, component, module, services, pipes have been implemented. File upload and display feature have been implemented intially. Other features can be extended as per requirement later. Jhipster was used to get the initial barebone application running.

## Project Structure

Node is required for generation and recommended for development. `package.json` is always generated for a better development experience with prettier, commit hooks, scripts and so on.

`/src/*` structure follows default Java structure.

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

We use npm scripts and [Angular CLI][] with [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

```
./gradlew -x webapp
npm start
```

Npm is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `npm update` and `npm install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `npm help update`.

The `npm run` command will list all of the scripts available to run for this project.

### Using Angular CLI

Angular CLI was used to generate component, module, routing module, services.

For example, the following command:

```
ng generate component my-component
```
