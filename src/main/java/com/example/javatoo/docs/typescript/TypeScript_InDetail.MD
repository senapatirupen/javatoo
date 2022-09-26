let count = 3;
There is nothing that prevents us from setting that variable to a value that is a string, 
a date, an array, or basically any object. All of the following assignments are valid:
count = "string";
count = new Date();
count = false;
count = [1, 2, 3];
count = { key: "value" };
In almost all scenarios, this is not a behavior we actually want. Moreover, since 
JavaScript does not know when we are writing the code whether a variable contains 
a string or a number, it cannot stop us from trying to, for example, convert it to 
lowercase. We cannot know whether that operation will succeed or fail until the 
moment we actually try it, when running the code.
Let's take the following example:
let variable;
if (Math.random()>0.5) {
variable = 3;
} else {
variable = "String";
}
console.log(variable.toLowerCase());