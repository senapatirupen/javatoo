To add Google Fonts to an Angular project, follow these steps:

Step 1: Choose Google Font
Visit the Google Fonts website (https://fonts.google.com/) and browse through the collection of fonts available. Select the font(s) you want to use in your Angular project.

Step 2: Link Google Fonts in index.html
Open the `index.html` file located in the `src` folder of your Angular project. Add the link to the selected Google Fonts in the `<head>` section of the HTML file. You can do this by copying the link provided by Google Fonts and pasting it inside the `<head>` tag.

For example, if you want to use the "Roboto" font, add the following link to `index.html`:

```html
<!-- index.html -->
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>My Angular App</title>
  <base href="/">

  <!-- Add Google Font link -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

  <!-- ... other meta tags and stylesheets ... -->

</head>
<body>
  <app-root></app-root>
</body>
</html>
```

Step 3: Use Google Font in CSS
Now that the Google Font is linked to your Angular project, you can use it in your CSS files. Open the desired CSS file (e.g., `styles.css` for global styles) and apply the Google Font to the elements you want.

For example, to use the "Roboto" font for all paragraphs (`<p>` elements), add the following CSS rule:

```css
/* styles.css */
p {
  font-family: 'Roboto', sans-serif;
}
```

Step 4: Apply Google Font in Component Templates (Optional)
If you want to use the Google Font in specific component templates, you can do so by adding inline styles or custom CSS classes.

For example, to apply the "Roboto" font to a specific component's title, you can use inline styles in the component's template:

```html
<!-- my-component.component.html -->
<h1 style="font-family: 'Roboto', sans-serif;">Hello, Angular!</h1>
```

That's it! After following these steps, your Angular project will have the selected Google Font applied to the specified elements throughout the application. Remember that Google Fonts are served from Google's servers, so an internet connection is required for the fonts to load and display correctly in your application.