To override Bootstrap's default styles in your Angular application, you can follow these steps:

1. Identify the Bootstrap styles you want to override. You can inspect the elements using browser developer tools to determine the specific CSS classes or styles applied by Bootstrap.

2. Create a custom CSS file or add the styles to your existing CSS file in your Angular project. For example, you can create a file called `custom-styles.css` in your project's `src` directory.

3. Write your custom CSS rules to override the Bootstrap styles. Make sure to use the appropriate CSS selectors to target the specific elements you want to style. Here's an example:

```css
/* Example of overriding Bootstrap's button color */
.btn-primary {
  background-color: red;
  color: white;
}
```

4. Include your custom CSS file in your Angular application. Open the `angular.json` file in the root directory of your project.

5. Locate the `"styles"` array within the `build` and `test` targets. Add the path to your custom CSS file to the array. The path should be relative to the `src` directory. The updated `"styles"` array should look like this:

```json
"styles": [
  "src/styles.css",
  "src/custom-styles.css"
]
```

6. Save the `angular.json` file.

7. Restart your Angular development server if it's already running to apply the changes.

Now, the styles defined in your custom CSS file will override the corresponding Bootstrap styles. Make sure your custom CSS rules have a higher specificity or are defined later in the CSS file to take precedence over the Bootstrap styles.

By following these steps, you can override Bootstrap's default styles with your own custom styles in your Angular application.