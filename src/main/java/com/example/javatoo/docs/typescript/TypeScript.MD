tsc writeValue("ok)";
tsc --all
tsc --init
tsconfig.json entry
{
"compilerOptions": {
"target": "es5",
"module": "commonjs",
"strict": true,
"esModuleInterop": true,
"skipLibCheck": true,
"forceConsistentCasingInFileNames": true
}
}

1.  Create a new folder and execute the following command in a new terminal 
within it:
tsc --init
2.  Verify that a new tsconfig.jsonfile is created within the folder and that its 
target value is es5.
3.  Create a new file called squares.tsinside it.
4.  In squares.ts, create a function called squares:
function squares(array: number[]) {
5.  Create a new array from the input argument, using the JavaScript mapfunction 
with an arrow function argument:
const result = array.map(x => x * x);
6.  Return the new array from the function:
return result;
}
7.  Save the file and run the following command in the folder:
tsc squares.ts
8.  Verify that there is a new file in the folder called squares.jswith the 
following content:
function squares(array) {
var result = array.map(function (x) { return x * x; });
return result;
}
Here, we can see that the transpilation step did several things:
- It removed the type annotation from the array: number[]parameter, 
transpiling it to array.
- It changed the const result variable declaration to a var 
result declaration.
- It changed the arrow function, x=>x*x, to a regular function, function (x) 
{ return x * x; }.
While the first is TypeScript-specific code, the second and third are examples of 
TypeScript's backward compatibility – both the arrow functions and the const
declarations are JavaScript features that were introduced in the ES6 version of 
the language.
9.  Run the following command in the folder:
tsc --target es6 squares.ts
This will override the setting from the tsconfig.json file and it will transpile 
the TypeScript code to ES6-compatible JavaScript.
10. Verify that the contents of the squares.jsfile are now as follows:
function squares(array) {
const result = array.map(x => x * x);
return result;
}


function writeValue(val: string | null) {
console.log(`Value: ${val ?? "Fallback value"}`)
}
writeValue("London");
writeValue(null);
----- defining an optional parameter
//this is equal to string | undefined
//in the undefined case it will trigger writeVale()
function writeValue(val?: string){
    console.log(`Value: ${val ?? "Fallback value"}`)
}
writeValue("London");
writeValue();
----- defining default parameter
function writeValue(val: string = "default value") {
console.log(`Value: ${val}`)
}
writeValue("London");
writeValue();
----- using rest parameter
function writeValue(val: string, ...extraInfo: string[]) {
console.log(`Value: ${val}, Extras: ${extraInfo}`)
}
writeValue("London", "Raining", "Cold");
writeValue("Paris", "Sunny");
writeValue("New York");
output:
Value: London, Extras: Raining,Cold
Value: Paris, Extras: Sunny
Value: New York, Extras:
----- returning a result
function composeString(val: string) : string {
return `Composed string: ${val}`;
}
function writeValue(val?: string) {
console.log(composeString(val ?? "Fallback value"));
}
writeValue("London");
writeValue();
output:
Composed string: London
Composed string: Fallback value
----- function as an argument
function getUKCapital() : string {
return "London";
}
function writeCity(f: () => string) {
console.log(`City: ${f()}`)
}
writeCity(getUKCapital);
----- defining an arrow function
function getUKCapital() : string {
return "London";
}
function writeCity(f: () => string) {
console.log(`City: ${f()}`)
}
writeCity(getUKCapital);
writeCity(() => "Paris");
----- using a closer
function getUKCapital() : string {
return "London";
}
function writeCity(f: () => string) {
console.log(`City: ${f()}`)
}
writeCity(getUKCapital);
writeCity(() => "Paris");
let myCity = "Rome";
writeCity(() => myCity);
output:
City: London
City: Paris
City: Rome
----- Use of array
let myArray = [];
myArray[0] = 100;
myArray[1] = "Adam";
myArray[2] = true;

----- Using type annotation
let myArray: any[] = [];
myArray[0] = 100;
myArray[1] = "Adam";
myArray[2] = true;

----- Restricting array value types
let myArray: (number | string | boolean)[] = [];
myArray[0] = 100;
myArray[1] = "Adam";
myArray[2] = true;
----- Reading data from an array
let myArray: (number | string | boolean)[] = [100, "Adam", true];
let val = myArray[0];
console.log(`Value: ${val}`);
----- Modifying content of an array
let myArray: (number | string | boolean)[] = [100, "Adam", true];
myArray[0] = "Tuesday";
let val = myArray[0];
console.log(`Value: ${val}`);
----- Enumerating contents of an array
let myArray: (number | string | boolean)[] = [100, "Adam", true];
for (let i = 0; i < myArray.length; i++) {
console.log("Index " + i + ": " + myArray[i]);
}
console.log("---");
myArray.forEach((value, index) => console.log("Index " + index + ": " + value));
----- Using spread operator
let myArray: (number | string | boolean)[] = [100, "Adam", true];
let otherArray = [...myArray, 200, "Bob", false];
otherArray.forEach((value, index) => console.log("Index " + index + ": " + value));
output:
Index 0: 100
Index 1: Adam
Index 2: true
Index 3: 200
Index 4: Bob
Index 5: false
----- Processing a data array
let myArray: (number | string | boolean)[] = [100, "Adam", true];
let otherArray = [...myArray, 200, "Bob", false];
let sum: number = otherArray
.filter(val => typeof(val) == "number")
.reduce((total: number, val) => total + (val as number), 0)
console.log(`Sum: ${sum}`);
output:
Sum: 300
----- creating an object
let hat = {
name: "Hat",
price: 100
};
let boots = {
name: "Boots",
price: 100
}
console.log(`Name: ${hat.name}, Price: ${hat.price}`);
console.log(`Name: ${boots.name}, Price: ${boots.price}`);
----- Describing an object
let hat = {
name: "Hat",
price: 100
};
let boots = {
name: "Boots",
price: 100
}
function printDetails(product : { name: string, price: number}) {
console.log(`Name: ${product.name}, Price: ${product.price}`);
}
printDetails(hat);
printDetails(boots);
----- Defining an optional property
let hat = {
name: "Hat",
price: 100
};
let boots = {
name: "Boots",
price: 100,
category: "Snow Gear"
}
function printDetails(product : { name: string, price: number, category?: string}) {
if (product.category != undefined) {
console.log(`Name: ${product.name}, Price: ${product.price}, ` +
`Category: ${product.category}`);
} else {
console.log(`Name: ${product.name}, Price: ${product.price}`);
}
}
printDetails(hat);
printDetails(boots);
----- Defining a class
class Product {
constructor(name: string, price: number, category?: string) {
this.name = name;
this.price = price;
this.category = category;
}
name: string
price: number
category?: string
}
let hat = new Product("Hat", 100);
let boots = new Product("Boots", 100, "Snow Gear");
function printDetails(product : { name: string, price: number, category?: string}) {
if (product.category != undefined) {
console.log(`Name: ${product.name}, Price: ${product.price}, ` +
`Category: ${product.category}`);
} else {
console.log(`Name: ${product.name}, Price: ${product.price}`);
}
}
printDetails(hat);
printDetails(boots);
----- Defining a method to a class
class Product {
constructor(name: string, price: number, category?: string) {
this.name = name;
this.price = price;
this.category = category;
}
name: string
price: number
category?: string
printDetails() {
if (this.category != undefined) {
console.log(`Name: ${this.name}, Price: ${this.price}, ` +
`Category: ${this.category}`);
} else {
console.log(`Name: ${this.name}, Price: ${this.price}`);
}
}
}
let hat = new Product("Hat", 100);
let boots = new Product("Boots", 100, "Snow Gear");
hat.printDetails();
boots.printDetails();
----- Simplifying a class with access specifier
class Product {
constructor(public name: string, public price: number, public category?: string) {
}
printDetails() {
if (this.category != undefined) {
console.log(`Name: ${this.name}, Price: ${this.price}, ` +
`Category: ${this.category}`);
} else {
console.log(`Name: ${this.name}, Price: ${this.price}`);
}
}
}
let hat = new Product("Hat", 100);
let boots = new Product("Boots", 100, "Snow Gear");
hat.printDetails();
boots.printDetails();
----- Using class Inheritance
class Product {
constructor(public name: string, public price: number, public category?: string) {
}
printDetails() {
if (this.category != undefined) {
console.log(`Name: ${this.name}, Price: ${this.price}, ` +
`Category: ${this.category}`);
} else {
console.log(`Name: ${this.name}, Price: ${this.price}`);
}
}
}
class DiscountProduct extends Product {
constructor(name: string, price: number, private discount: number) {
super(name, price - discount);
}
}
let hat = new DiscountProduct("Hat", 100, 10);
let boots = new Product("Boots", 100, "Snow Gear");
hat.printDetails();
boots.printDetails();
output:
Name: Hat, Price: 90
Name: Boots, Price: 100, Category: Snow Gear
----- Checking object type
class Product {
constructor(public name: string, public price: number, public category?: string) {
}
printDetails() {
if (this.category != undefined) {
console.log(`Name: ${this.name}, Price: ${this.price}, ` +
`Category: ${this.category}`);
} else {
console.log(`Name: ${this.name}, Price: ${this.price}`);
}
}
}
class DiscountProduct extends Product {
constructor(name: string, price: number, private discount: number) {
super(name, price - discount);
}
}
let hat = new DiscountProduct("Hat", 100, 10);
let boots = new Product("Boots", 100, "Snow Gear");
// hat.printDetails();
// boots.printDetails();
console.log(`Hat is a Product? ${hat instanceof Product}`);
console.log(`Hat is a DiscountProduct? ${hat instanceof DiscountProduct}`);
console.log(`Boots is a Product? ${boots instanceof Product}`);
console.log(`Boots is a DiscountProduct? ${boots instanceof DiscountProduct}`);
output:
Hat is a Product? True
Hat is a DiscountProduct? True
Boots is a Product? True
Boots is a DiscountProduct? false
----- Importing specific type
export class Name {
constructor(public first: string, public second: string) {}
get nameMessage() {
return `Hello ${this.first} ${this.second}`;
}
}
export class WeatherLocation {
constructor(public weather: string, public city: string) {}
get weatherMessage() {
return `It is ${this.weather} in ${this.city}`;
}
}
import { Name, WeatherLocation } from "./modules/NameAndWeather";
let name = new Name("Adam", "Freeman");
let loc = new WeatherLocation("raining", "London");
console.log(name.nameMessage);
console.log(loc.weatherMessage);
----- Subscribe arguments
The basic method provided by an Observable is subscribe, which accepts an object whose properties 
are set to functions that respond to the sequence of events. The property names and the purpose of the 
functions are described in Table 4-2. If you only need to specify a function that receives events, then you can 
pass that function as the argument to the subscribe method. 
next This function is invoked when a new event occurs.
error This function is invoked when an error occurs.
complete This function is invoked when the sequence of events ends.
----- Using an Observable
import { Observable } from "rxjs";
function recieveEvents(observable: Observable<string>) {
observable.subscribe({
next: str => {
console.log(`Event received: ${str}`);
},
complete: () => console.log("Sequence ended")
});
}
----- Understanding Observer
The Reactive Extensions Observer<T>class provides the mechanism by which updates are created, using the 
methods described
next(value) This method creates a new event using the specified value.
error(errorObject) This method reports an error, described using the argument, which can be any object.
complete() This method ends the sequence, indicating that no further events will be sent.
----- Using observer
import { Observable, Observer } from "rxjs";
function recieveEvents(observable: Observable<string>) {
observable.subscribe({
next: str => {
console.log(`Event received: ${str}`);
},
complete: () => console.log("Sequence ended")
});
}
function sendEvents(observer: Observer<string>) {
let count = 5;
for (let i = 0; i < count; i++) {
observer.next(`${i + 1} of ${count}`);
}
observer.complete();
}
----- Understanding Subjects
The Reactive Extensions library provides the Subject<T>class, which implements both the Observer and 
Observable functionality. A Subject is useful when you are working with RxJS in your own code, rather 
than using an Observer or Observable provided through the Angular API. In Listing 4-32, I have created a 
Subject<string>and used it as the argument to invoke the functions defined in the previous sections.
----- Example
isting 4-32uses the Subject class, which is the simplest way to create an object that is both an 
Observer and an Observable. its main limitation is that when a new subscriber is created using the 
subscribe method, it won’t receive an event until the next time the next method is called. this can 
be unhelpful if you are creating instances of components or directives dynamically and you want them 
to have some context data as soon as they are created.
the reactive extensions library includes some specialized implementations of the Subject class 
that can be used to work around this problem. the BehaviorSubject class keeps track of the last 
event it processed and sends it to new subscribers as soon as they call the subscribe method. the 
ReplaySubject class does something similar, except that it keeps track of all of its events and sends 
them all to new subscribers, allowing them to catch up with any events that were sent before they 
subscribed. 
Listing 4-32.  Using a Subject in the main.ts File in the src Folder
import { Observable, Observer, Subject } from "rxjs";
function recieveEvents(observable: Observable<string>) {
observable.subscribe({
next: str => {
console.log(`Event received: ${str}`);
},
complete: () => console.log("Sequence ended")
});
}
function sendEvents(observer: Observer<string>) {
let count = 5;
for (let i = 0; i < count; i++) {
observer.next(`${i + 1} of ${count}`);
}
observer.complete();
}
let subject = new Subject<string>();
recieveEvents(subject);
sendEvents(subject);
The new statements connect together the functions, and the Subject<string>acts as the conduit 
that carries the events between functions, producing the following output in the browser’s JavaScript 
console:
Event received: 1 of 5
Event received: 2 of 5
Event received: 3 of 5
Event received: 4 of 5
Event received: 5 of 5
Sequence ended