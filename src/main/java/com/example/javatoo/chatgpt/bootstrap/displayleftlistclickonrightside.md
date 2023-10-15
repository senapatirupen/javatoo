To load content on the left side based on the click of an item in the right list group using Angular, you can follow these steps:

1. Set up your component with the necessary data structures and variables. For example, you might have an array of items for the right list group and a variable to hold the selected item on the left side.

   ```typescript
   // component.ts
   import { Component } from '@angular/core';

   @Component({
     selector: 'app-component',
     templateUrl: 'component.html',
     styleUrls: ['component.css']
   })
   export class YourComponent {
     rightListGroup: any[] = [
       { id: 1, name: 'Item 1', content: 'Content for Item 1' },
       { id: 2, name: 'Item 2', content: 'Content for Item 2' },
       { id: 3, name: 'Item 3', content: 'Content for Item 3' }
     ];

     selectedLeftContent: any;
   }
   ```

2. Implement the HTML template for your component. Use Angular directives to loop through the items in the right list group and display them. Bind a click event handler to each item to capture the selection and load the corresponding content on the left side.

   ```html
   <!-- component.html -->
   <div class="row">
     <div class="col-4">
       <div class="list-group">
         <a
           *ngFor="let item of rightListGroup"
           class="list-group-item"
           (click)="selectRightItem(item)"
         >
           {{ item.name }}
         </a>
       </div>
     </div>
     <div class="col-8">
       <div class="left-content" *ngIf="selectedLeftContent">
         {{ selectedLeftContent.content }}
       </div>
     </div>
   </div>
   ```

3. Implement the click event handler function in your component to handle the selection of an item from the right list group. Set the selectedLeftContent variable to the clicked item, which will trigger the display of the corresponding content on the left side.

   ```typescript
   // component.ts
   selectRightItem(item: any) {
     this.selectedLeftContent = item;
   }
   ```

With this implementation, when you click an item in the right list group, the corresponding content will be loaded and displayed on the left side of the component. Make sure to update the CSS classes and styling as per your specific requirements.

Note: The code provided is a basic example to illustrate the concept. You may need to adapt it to your specific use case and styling preferences.