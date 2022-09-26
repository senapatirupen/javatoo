Install angular cli
* npm install -g @angular/cli
* ng version
     serve: Build and serve an Angular application.
     build: Build an Angular application.
     test: Run the unit tests of an Angular application.
     generate: Generate a new Angular artifact, such as a component or module.
     add: Install a third-party library that is compatible with the Angular framework.
     new: Create a new Angular application.
     help: 
     
Angular tooling in VSCode
•  Nx Console
•  Angular Language Service
•  Angular Snippets
•  Angular Evergreen
•  Material Icon Theme

Angular extensions for VSCode at https://marketplace.visualstudio.com/search?term=angular&target=VSCode

create new angular app called 'my-blog'
* ng new my-blog --routing --style=scss --skip-tests --skip-git

to install bootstrap
* npm install bootstrap

Add the following import statement at the top of the styles.scss file that 
exists in the src folder of our Angular application:
@import "~bootstrap/scss/bootstrap";

* npm install bootstrap-icons
Add the following import statement at the top of the styles.scss file that 
exists in the src folder of our Angular application:
@import "~bootstrap-icons/font/bootstrap-icons";

to create module name 'core'
* ng generate module core
The preceding command will create the module in the src\app\core folder

to create header component in core module
* ng generate component header --path=src/app/core --module=core --export
The previous command will create all necessary component files inside the src\app\
core\header folder.

It will also declare HeaderComponent in the core module and add it to its exports
property so that other modules can use it.

* ng generate module shared
* ng generate component footer --path=src/app/shared --module=shared --export

Open the main module of the application, the app.module.ts file, and add CoreModule and SharedModule.

Open the app.component.html template file of the main component and 
replace its content with the following HTML snippet:
<app-header></app-header>
<app-footer></app-footer>

* ng generate module contact

* ng generate component contact --path=src/app/contact --module=contact --export --flat
We pass the --flat option to the generate command so that the Angular CLI 
will not create a separate folder for our component

Tip: The assets folder in an Angular CLI project is used for static content such as images, fonts, or JSON files.
Tip: When you design your website and need to decide upon the Angular modules that you will use, check out the main menu of the website. Each link of the menu should be a different feature and, thus, a different Angular module.
Important Note: Pipes are a built-in feature of the Angular framework that apply transformations on the view representation of a component property. The underlying value of the property remains intact.
Important Note: Notice that the value of the routerLink directive contains a leading /, whereas the path property of the route configuration object that we defined does not. According to the case, omitting the /would give a different meaning to the route.

* ng generate module articles --route=articles --module=app-routing
--route: Defines the URL path of our feature
--module: Indicates the routing module that will define the route configuration object that activates our feature
It creates a routed component in the src\app\articles folder that will be activated by default from a route navigation object. It is the landing page of our feature, and it will display a list of blog posts, as we will see in the Displaying blog data on the home page section.
It creates a routing module, named articles-routing.module.ts, that contains the routing configuration of the articles module.
It adds a new route configuration object in the route configuration of the main application module that activates the articles module.
CREATE src/app/articles/articles-routing.module.ts (351 bytes)
CREATE src/app/articles/articles.module.ts (371 bytes)
CREATE src/app/articles/articles.component.html (23 bytes)
CREATE src/app/articles/articles.component.ts (284 bytes)
CREATE src/app/articles/articles.component.scss (0 bytes)
UPDATE src/app/app-routing.module.ts (469 bytes)

The forChild method is used in feature modules, whereas the forRoot method should be used only in the main application module.

The route configuration of the articles module contains only one route that 
activates ArticlesComponent. The path of the route is set to an empty string to 
indicate that it is the default route of the routing module. It essentially means that 
ArticlesComponent will be activated whenever that module is loaded. But how is the 
articles module loaded in our application?
The second route of the main routing module contains a route configuration object that 
does not activate a component but rather a module. It uses the loadChildren method 
to load Articles Module dynamically when navigation triggers the articles path.

Important Note: The import function in the loadChildren property accepts the relative path of the TypeScript module file without the extension.

Run the following command to install Http-Server globally on your machine
* npm install -g http-server
* http-server --version

* ng new web-arcade --routing --style=scss --skip-tests --skip-git

Add Progressive Web App Features for Offline Access
* ng add @angular/pwa

* ng run build
then the build is ready then run below command to run the http server on specified port.
* http-server dist/web-arcade -p 4100

* ng g component components/dice
* ng g service services/dice-tool



* ng new SportsStore
cd SportsStore
npm install bootstrap
npm install font-awesome
npm install --save-dev json-server
npm install --save-dev jsonwebtoken
--save-dev argument, which indicates they are used during development and will not be part of the SportsStore application

Add below line on angular.json
"styles": [
              "src/styles.scss",
              "node_modules/bootstrap/dist/css/bootstrap.min.css",
              "node_modules/font-awesome/css/font-awesome.min.css"
            ]
 
 Add below line on package.json 
 "scripts": {
    "ng": "ng",
    "start": "ng serve",
    "build": "ng build",
    "watch": "ng build --watch --configuration development",
    "test": "ng test",
    "json": "json-server data.js -p 3500 -m authMiddleware.js"
  }
  
  To provide the json-server package with data to work with, I added a file called data.jsin the 
  SportsStore folder and added the code shown Listing 7-3, which will ensure that the same data is available 
  whenever the json-server package is started so that I have a fixed point of reference during development.
  
  Create these three folder
  SportsStore/src/app/model This folder will contain the code for the data model.
  SportsStore/src/app/store This folder will contain the functionality for basic shopping.
  SportsStore/src/app/admin This folder will contain the functionality for administration.
  
  * ng serve --port 3000 --open
  
  To start the RESTful web service, open a new command prompt, navigate to the SportsStorefolder, and run 
  the following command:
  
  * npm run json
  
  json server will run on http://localhost:3500/
  
  http://localhost:3500/products/1. The browser will display a JSON 
  representation of one of the products defined in Listing 7-3, as follows:
  {
  "id": 1,
  "name": "Kayak",
  "category": "Watersports",
  "description": "A boat for one person",
  "price": 275
  }
  
  
  npm install 
  This command performs a local install of the packages specified in the package.json file.
  
  npm install package@version
  This command performs a local install of a specific version of a package and 
  updates the package.json file to add the package to the dependencies section.
  
  npm install package@version --save-dev
  This command performs a local install of a specific version of a package and 
  updates the package.json file to add the package to the dev Dependencies
  section.
  
  npm install --global package@version
  This command performs a global install of a specific version of a package.
  npm list This command lists all of the local packages and their dependencies.
  
  npm run <script name> 
  This command executes one of the scripts defined in the package.jsonfile, 
  as described next.
  
  npx package@version This command downloads and executes a package.
  
  ng This command runs the ngcommand, which provides access to the Angular development tools.
  start This command starts the development tools and is equivalent to the ng serve command.
  build This command performs the production build process.
  test This command starts the unit testing tools, which are described in Chapter 29, and is equivalent 
  to the ng test command.
  lint This command starts the TypeScript linter, as described in the “Using the Linter” section, and is 
  equivalent to the ng lint command.
  e2e This command starts the end-to-end testing tools and is equivalent to the ng e2e command.
  
  
  npm -v
  node -v
  
  npm install --global @angular/cli@13.0.3
  ng new todo --routing false --style scss --skip-git --skip-tests
  ng add @angular/material@13.0.2 --defaults
  npm install bootstrap@5.1.3
  npm install @fortawesome/fontawesome-free@6.0.0
  npm install --save-dev json-server@0.17.0
  npm install --save-dev jsonwebtoken@8.5.1
  npm install -g typescript@4.1.3
  npm install @angular/cdk --save
  npm install primeng --save
  npm install primeicons --save
  npm install --save-dev express@4.17.3
  npm install --save-dev connect-history-api-fallback@1.6.0
  npm install --save-dev https@1.0.0
  ng add @angular-eslint/schematics@13.0.1
  ng serve --open
  npx http-server@14.0.0 dist/example --port 5000
  npm run json
   http://localhost:3500/products/1
   
  
  https://fonts.google.com/icons?selected=Material+Icons.

"./node_modules/@angular/material/prebuilt-themes/indigo-pink.css",
"./node_modules/bootstrap/dist/css/bootstrap.min.css",
"./node_modules/primeicons/primeicons.css",
"./node_modules/primeng/resources/themes/arya-purple/theme.css",
"./node_modules/primeng/resources/primeng.min.css",
"./node_modules/font-awesome/css/font-awesome.min.css"

getClasses(): string {
return this.model.getProducts().length == 5 ? "bg-success" : "bg-warning";
}
<div [ngClass]="'text-white p-2 ' + getClasses()" >
Hello, World.
</div>

<div class="form-group m-2">
<label>Name:</label>
<input class="form-control" [value]="model.getProduct(1)?.name ?? 'None'" />
</div>

getClasses(key: number): string {
let product = this.model.getProduct(key);
return "p-2 " + ((product?.price ?? 0) < 50 ? "bg-info" : "bg-warning");
}

<div class="p-2"
[class.bg-success]="(model.getProduct(2)?.price ?? 0) < 50"
[class.bg-info]="(model.getProduct(2)?.price ?? 0) >= 50">
The second product is {{model.getProduct(2)?.name}}
</div>

<table class="table table-sm table-bordered text-dark">
<tr><th></th><th>Name</th><th>Category</th><th>Price</th></tr>
<tr *ngFor="let item of getProducts(); let i = index; let c = count">
<td>{{ i + 1 }} of {{ c }}</td>
<td>{{item.name}}</td>
<td>{{item.category}}</td>
<td>{{item.price}}</td>
</tr>
</table>

getMessages(errs : ValidationErrors | null, name: string) : string[] {
let messages: string[] = [];
for (let errorName in errs) {
switch (errorName) {
case "required":
messages.push(`You must enter a ${name}`);
break;
case "minlength":
messages.push(`A ${name} must be at least
${errs['minlength'].requiredLength}
characters`);
break;
case "pattern":
messages.push(`The ${name} contains
illegal characters`);
break;
}
}
return messages;
}

Command Description
npm install :: 
This command performs a local install of the packages specified in the package.json file.
npm install package@version ::
This command performs a local install of a specific version of a package and 
updates the package.json file to add the package to the dependencies section.
npm install package@version --save-dev ::
This command performs a local install of a specific version of a package and 
updates the package.json file to add the package to the dev Dependencies section.
npm install --global package@version ::
This command performs a global install of a specific version of a package.
npm list :: 
This command lists all of the local packages and their dependencies.
npm run <script name> ::
This command executes one of the scripts defined in the package.json file, as described next.
npx package@version ::
This command downloads and executes a package.

###### run javascript inside angular project.
  under src create a file example.js
  src/example.js
  then add some js code to example.js
  function myFunction(param) {
  let result = param + 100;
  console.log("My result: " + result);
  }
  myFunction(1);
  
  then on console type 
    node example.js
 output on console : My result: 101
 
###### run typescript inside angular project.
  replace the content of main.ts with some typescript code
  function myFunction(param: any) {
  if (typeof(param) == "number") {
  let result = param + 100;
  console.log("My result: " + result);
  } else {
  throw ("Expected a number: " + param)
  }
  }
  myFunction(1);
  myFunction("London");
the out put show on console

When you run ng serve, the project is built so that it can be used by the browser. This is a process that 
requires three important tools: the TypeScript compiler, the Angular compiler, and a package named 
webpack.
Angular applications are created using TypeScript files and HTML templates containing expressions, 
neither of which can be understood by browsers. The TypeScript compiler is responsible for compiling 
the TypeScript files into JavaScript, and the Angular compiler is responsible for transforming templates 
into JavaScript statements that use the browser APIs to create the HTML elements in the template file and 
evaluate the expressions they contain.
The build process is managed through webpack, which is a module bundler, meaning that it takes the 
compiled output and consolidates it into a module that can be sent to the browser. This process is known 
as bundling, which is a bland description for an important function, and it is one of the key tools that you 
will rely on while developing an Angular application, albeit one that you won’t deal with directly since it is 
managed for you by the Angular development tools. 
When you run the ng servecommand, you will see a series of messages as webpack processes the 
application. Webpack starts with the code in the main.tsfile, which is the entry point for the application 
and follows the importstatements it contains to discover its dependencies, repeating this process for each 
file on which there is a dependency. Webpack works its way through the importstatements, compiling each 
TypeScript and template file on which a dependency is declared to produce JavaScript code for the entire 
application.


Table 9-7.  The Bundles Produced by the Angular Build Process
Name Description
main.js This file contains the compiled output produced from the src/appfolder.
polyfills.js This file contains JavaScript polyfills required for features used by the application that are 
not supported by the target browsers.
runtime.js This file contains the code that loads the other modules.
styles.js This file contains JavaScript code that adds the application’s global CSS stylesheets.
vendor.js This file contains the third-party packages the application depends on, including the 
Angular packages.


Using the Linter
A linter is a tool that inspects source code to ensure that it conforms to a set of coding conventions and 
rules. Run the command shown in Listing 9-11in the examplefolder, which installs the popular ESLint linter 
package and uses the schematics API to configure the project. 
Listing 9-11.  Adding the Linter Package
ng add @angular-eslint/schematics@13.0.1
As part of the integration process, the linter package creates a configuration file named .eslintrc.json
in the examplefolder. Two changes are required to configure the linter, as shown in Listing 9-12.
Listing 9-12.  Configuring the Linter in the .eslintrc.json File in the example Folder
{
"root": true,
"ignorePatterns": [
"projects/**/*",
"src/test.ts"
],
"overrides": [
{
"files": [
"*.ts"
],
"parserOptions": {
"project": [
"tsconfig.json"
],
"createDefaultProgram": true
},
"extends": [
"plugin:@angular-eslint/ng-cli-compat",
"plugin:@angular-eslint/recommended",
Chapter 9 ■UnderStanding angUlar projeCtSand toolS
231
"plugin:@angular-eslint/template/process-inline-templates"
],
"rules": {
"@angular-eslint/directive-selector": [
"error",
{
"type": "attribute",
"prefix": "app",
"style": "camelCase"
}
],
"@angular-eslint/component-selector": [
"error",
{
"type": "element",
"prefix": "app",
"style": "kebab-case"
}
]
}
},
{
"files": [
"*.html"
],
"extends": [
"plugin:@angular-eslint/template/recommended"
],
"rules": {}
}
]
}
The first change excludes the test.tsfile from linting. This file is created by the ng newcommand to 
support unit tests, and its contents will produce linting warnings. The second change expands the set of 
rules applied by the linter.
Additional JavaScript packages are required to support the expanded set of linting rules. Install these 
packages by running the command shown in Listing 9-13in the examplefolder.
Listing 9-13.  Installing Additional Packages
npm install eslint-plugin-import eslint-plugin-jsdoc eslint-plugin-prefer-arrow
To demonstrate how the linter works, I made two changes to a TypeScript file, as shown in Listing 9-14.
Chapter 9 ■UnderStanding angUlar projeCtSand toolS
232
Listing 9-14.  Making Changes in the app.component.ts File in the src/app Folder
import { Component } from '@angular/core';
debugger
@Component({
selector: 'approot',
templateUrl: './app.component.html',
styleUrls: ['./app.component.css']
})
export class AppComponent {
title = 'example';
}
I added a debuggerstatement and changed the value of the selector property in the Component
decorator. These changes illustrate the range of issues that can be detected by the linter. The debugger
statement can cause problems when the application is deployed because it can halt code execution.
The change to the selectorvalue breaks the style convention for Angular applications, where the 
selector should be specified in kebab-case, meaning that each word is separated by a hyphen. This is only a 
convention, however, and it doesn’t prevent the application from working. (However, since I have changed 
only the app.component.tsfile and not made a corresponding change in the HTML file, the application will 
build but not run as expected. I explain the relationship between the selector property and the HTML file in 
the next section of this chapter.)
Run the command shown in Listing 9-15in the examplefolder to run the linter.
Listing 9-15.  Running the Linter
ng lint
The linter inspects the files in the project and reports any problems that it encounters. The changes in 
Listing 9-14result in the following messages:
...
Linting "example"...
C:\example\src\app\app.component.ts
3:1  error Unexpected 'debugger' statement no-debugger
6:13 error The selector should be kebab-case
(https://angular.io/guide/styleguide#style-05-02)
@angular-eslint/component-selector
2 problems (2 errors, 0 warnings)
Lint errors found in the listed files.
...
Linting isn’t integrated into the regular build process and is performed manually. The most common 
use for linting is to check for potential problems before committing changes to a version control system, 
although some project teams make broader use of the linting facility by integrating it into other processes.

----
Go to recipes-book-api folder
npm run server:start
The server will be running at http://localhost:3001.

Go to recipes-book-front folder
ng serve --proxy-config proxy.config.json

Generate a Recipe model src/app/core/model
ng g i Recipe

Generate a Recipe service src/app/core/services
ng g s Recipes 
