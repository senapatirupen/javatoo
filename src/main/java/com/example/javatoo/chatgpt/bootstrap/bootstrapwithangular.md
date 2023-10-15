To integrate Bootstrap with an Angular project, you can follow these steps:

1. Create a new Angular project or open an existing Angular project.

2. Install Bootstrap via npm by running the following command in your project directory:
```
npm install bootstrap
```

3. Open the `angular.json` file in the root directory of your project.

4. Locate the `"styles"` array within the `build` and `test` targets. Add the Bootstrap CSS file path to the array. The path should be relative to the `node_modules` directory. The updated `"styles"` array should look like this:
```json
"styles": [
  "src/styles.css",
  "node_modules/bootstrap/dist/css/bootstrap.min.css"
]
```

5. Save the `angular.json` file.

6. Open the `src/styles.css` file in your project.

7. If there is no `@import` statement in the file, add the following line to import Bootstrap styles:
```css
@import "~bootstrap/dist/css/bootstrap.min.css";
```
Alternatively, if there is already an `@import` statement, append the above line to it.

8. Save the `styles.css` file.

9. Bootstrap is now integrated with your Angular project. You can start using Bootstrap classes and components in your Angular templates.

10. In your Angular components, you can import Bootstrap's JavaScript functionalities as needed. For example, if you want to use Bootstrap's modal component, add the following import statement to your component file:
```typescript
import 'bootstrap/dist/js/bootstrap.min.js';
```
Make sure to import it only where necessary to minimize unnecessary JavaScript in your project.

11. You can now use Bootstrap classes and components in your Angular templates. Refer to the Bootstrap documentation for available classes and components.

With these steps, you have successfully integrated Bootstrap with your Angular project, allowing you to leverage Bootstrap's CSS framework and components to style and enhance your Angular application.