To integrate Bootstrap icons with Angular, you can follow these steps:

Step 1: Install Bootstrap
If you haven't already installed Bootstrap in your Angular project, you can do so using npm:

```bash
npm install bootstrap
```

Step 2: Import Bootstrap CSS
In the styles.css file (or any other global styles file) of your Angular project, import the Bootstrap CSS:

```css
/* styles.css */
@import '~bootstrap/dist/css/bootstrap.min.css';
```

Step 3: Import Bootstrap Icons
Starting from Bootstrap 5, the icons are included in a separate package called "bootstrap-icons." Install it using npm:

```bash
npm install bootstrap-icons
```

```css
/* styles.css */
@import "~bootstrap-icons/font/bootstrap-icons.css";
```
Step 4: Import Icons in Component
In the component where you want to use the Bootstrap icons, import the icons you need:

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-my-component',
  templateUrl: './my-component.component.html',
})
export class MyComponent {}
```

Step 5: Use Bootstrap Icons in HTML
Now you can use Bootstrap icons in your HTML templates. Bootstrap icons use the "bi" class followed by the specific icon class name. For example, to use the "heart" icon, you can do the following:

```html
<!-- my-component.component.html -->
<i class="bi bi-heart"></i>
```

That's it! You can now use any Bootstrap icon in your Angular project by adding the appropriate "bi" class and the icon class name to any HTML element.

Remember that in Bootstrap 5, the icons are SVG-based, which means they are scalable and can be easily customized using CSS. You can find a list of available Bootstrap icons and their class names in the official Bootstrap Icons documentation: https://icons.getbootstrap.com/