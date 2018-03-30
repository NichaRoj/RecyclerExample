# RecyclerExample
***FOR THINC USE ONLY***

This is a tutorial on how to use RecyclerView in Kotlin. The code files are the same as what I wrote here. I also commented in the files to explain the syntax or what each line does in details.

**DO NOT copy and paste the code. Type each line character by character so you would be more accustomed to the new syntax.

### Step 0
This is the most important step as we are going to add dependencies to our project.
In project navigation tab on left side, go to Gradle Scripts > build.gradle(Module:app) and add these lines in dependencies.
```
compile 'com.android.support:recyclerview-v7:26.1.0'
implementation 'com.android.support:cardview-v7:26.1.0'
```
Your file should look something like this.
```
dependencies{
  ...
  implementation 'com.android.support:appcompat-v7:26.1.0'
  ...
  compile 'com.android.support:recyclerview-v7:26.1.0'
  implementation 'com.android.support:cardview-v7:26.1.0'
}
```
**HOWEVER** look at the line with "appcompat." The end of two lines we added should have the same number i.e. if appcompat ends with 27.1.0, then the two new lines should also end in 27.1.0

If you are finished, then you can click "Gradle Sync"

### Step 1: User class
Firstly, we are going to create a class for our custom object. We are going to create a new class called User, which consists of two attributes: name and place. Right click the same folder that your MainActivity.kt is in, and click New... > Kotlin File/Class
```kotlin
class User (u: String = "DefaultUser", p: String = "DefaultPlace"){
    var name: String = u
    var place: String = p
}
```
This code is written in a way to look similar to Java class. However, because it is Kotlin, it can be shortened.
```kotlin
class User (var name: String = "DefaultUser", var place: String = "DefaultPlace")
```

### Step 2: item_user Layout
Now we are going to create a new layout to represent each item. If you wonder why we have to create a new layout, let's look at an example of RecyclerView from the official Android Developer site.

![alt text](https://developer.android.com/design/material/images/card_travel.png)

As you can see, each item in the list doesn't only contain a TextView. It has an image, a title, a description, and 2 buttons. This layout of each item is reused, or *recycled*, over and over. To use it over and over, we have to create a template for it first, and we are going to do it right now.
