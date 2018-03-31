# RecyclerExample
***FOR THINC USE ONLY***

This is a tutorial on how to use RecyclerView in Kotlin. The code files are the same as what I wrote here. I also commented in the files to explain the syntax or what each line does in details.

**DO NOT copy and paste the code. Type each line character by character so you would be more accustomed to the new syntax.

Also names of classes/files/variables are optional. Meaning, you can name it whatever you want. However, the names I chose are most commonly used to avoid confusion.

#### Naming Convension: how you should name things

Classes begin with capital letters, and each separate word are signalled with capital letters e.g. `UserInterface`

Variables and functions use camel cases e.g. `variableNumberOne, writeProgram, onCreate`

Layout file names and ids use snake cases e.g. `item_for_recycler, main_activity, sub_activity`

Now that we got that out of the way, create a new project with an empty activity (don't forget to check `Include Kotlin Support` box!), and let's get started.

### Step 0: Dependencies
This is the most important step as we are going to add dependencies to our project.
In project navigation tab on left side, go to `Gradle Scripts > build.gradle(Module:app)` and add these lines in dependencies.
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
**HOWEVER** look at the line with `appcompat` The end of two lines we added should have the same number i.e. if appcompat ends with `27.1.0` then the two new lines should also end in `27.1.0`

If you are finished, then you can click `Gradle Sync`

### Step 1: User class
Firstly, we are going to create a class for our custom object. We are going to create a new class called `User`, which consists of two attributes: `name` and `place` Right click the same folder that `MainActivity.kt` is in, and click `New > Kotlin File/Class`
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

Now we are going to create a new layout to represent each item. If you wonder why we have to create a new layout, let's look at an example of `RecyclerView` from the official Android Developer site.

![alt text](https://developer.android.com/design/material/images/card_travel.png)

As you can see, each item in the list doesn't only contain a `TextView` It has an image, a title, a description, and 2 buttons. This layout of each item is reused, or *recycled*, over and over. To use it over and over, we have to create a template for it first, and we are going to do it right now.

To do so, on the left side, go to folder `app > res > layout` Right click the layout folder and click `New > Layout resource file` Name it `item_user`

In this code, we are going to use `CardView` because why not, it's beautiful. Here's the code:
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_height="wrap_content"
    android:layout_width="match_parent">
    
    <android.support.constraint.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:id="@+id/user_name"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            android:textColor="@android:color/black"
            android:textSize="20sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <TextView
            android:id="@+id/user_place"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginBottom="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="4dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/user_name" />
    </android.support.constraint.ConstraintLayout>
</android.support.v7.widget.CardView>
```

If you look at the code, you will notice that `ConstraintLayout` is inside `CardView` The reason why is that our CardView acts like a frame for us to put something in. If you don't want to use CardView, you can use FrameLayout instead. Now you can create the layout for your item normally.

### Step 3: RecyclerAdapter

Now that we have a class of User we want to store and a template for it to be shown, how are we going to tell the app where to show each attribute? Well, we have to create another class for it, and if we have to code everything from the ground up, it's going to be super complicated. Instead, we will have this class extend another pre-made class.
```kotlin
class RecyclerAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
  ...
}
```
If you wonder what <> does, you can read about it [here](https://docs.oracle.com/javase/tutorial/java/generics/types.html).

Here, we have the class accepts an ArrayList of User as a parameter/argument because we want the adapter to use data inside. Additionally, if you want an item to do something when it's clicked, you can add another argument here as well but we are not going to do that now.

Now, you will notice that there's an error with the class. If you hover over error, it probably says something like "some methods have to be overridden." If you recall, if the class we extend or implement from have functions that are undefined, we have to define them. To do that, click a space inside the class and press Ctrl+O. It will show you functions that you can overwrite.

We are going to override three functions: "onCreateViewHolder", "onBindViewHolder", and "getItemCount." You can type directly into the new window to search for it. You should get these new functions.
```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: int): ViewHolder {

}

override fun onBindViewHolder(holder: ViewHolder, position: Int) {

}

override fun getItemCount(): Int {

}
```
Now you will still have an error in the class you extend. The reason is that `RecyclerAdapter.ViewHolder` does not exist. How do we know that? `RecyclerAdapter.ViewHolder` refers to class ViewHolder *inside* the class we are coding, RecyclerAdapter. But we never created a class called ViewHolder though? Well, we are going to create it now.

***Inside*** class RecyclerAdapter, create a new class called ViewHolder
```kotlin
class ViewHolder(private var v: View): RecyclerView.ViewHolder(v){

}
```
In this class, we are going to tell the app to "bind" certain data inside User class with certain view. Create a new function called bind inside class ViewHolder.
```kotlin
fun bind(user: User){
  
}
```
If you recall item_user layout we wrote, we have two TextView with id user_name and user_place. Since we intend to modify the text of those two views with attributes name and place inside User class, we add this piece of code in this function.
```kotlin
with(v){
  user_name.text = user.name
  user_place.text = user.place
}
```
We use `with` block to shorten the code. Normally, we can write
```kotlin
v.user_name.text = user.name
v.user_place.text = user.place
```
