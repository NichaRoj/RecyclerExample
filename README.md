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

Now that we have a class of `User` we want to store and a template for it to be shown, how are we going to tell the app where to show each attribute? Well, we have to create another class for it, and if we have to code everything from the ground up, it's going to be super complicated. Instead, we will have this class extend another pre-made class.
```kotlin
class RecyclerAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
  
}
```
If you wonder what <> does, you can read about it [here](https://docs.oracle.com/javase/tutorial/java/generics/types.html).

Here, we have the class accepts an ArrayList of User as a parameter/argument because we want the adapter to use data inside. Additionally, if you want an item to do something when it's clicked, you can add another argument here as well but we are not going to do that now.

Now, you will notice that there's an error with the class. If you hover over error, it probably says something like "some methods have to be overridden." If you recall, if the class we extend or implement from have functions that are undefined, we have to define them. To do that, click a space inside the class and press `Ctrl + O` It will show you functions that you can overwrite.

We are going to override three functions: `onCreateViewHolder`, `onBindViewHolder`, and `getItemCount` You can type directly into the new window to search for it. You should get these new functions.
```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: int): ViewHolder {

}

override fun onBindViewHolder(holder: ViewHolder, position: Int) {

}

override fun getItemCount(): Int {

}
```
Now you will still have an error in the class you extend. The reason is that `RecyclerAdapter.ViewHolder` does not exist. How do we know that? `RecyclerAdapter.ViewHolder` refers to class `ViewHolder` *inside* the class we are coding, `RecyclerAdapter` But we never created a class called `ViewHolder` though? Well, we are going to create it now.

***Inside*** class `RecyclerAdapter`, create a new class called `ViewHolder`
```kotlin
class ViewHolder(private var v: View): RecyclerView.ViewHolder(v){

}
```
In this class, we are going to tell the app to "bind" certain data inside User class with certain view. Create a new function called `bind` inside class `ViewHolder`
```kotlin
fun bind(user: User){
  
}
```
If you recall `item_user` layout we wrote, we have two TextView with id `user_name` and `user_place` Since we intend to modify the text of those two views with attributes name and place inside User class, we add this piece of code in this function.
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
Now that we have a template on where to put data in, we now can actually bind them together. In function `onCreateViewHolder` we add this line:
```kotlin
val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
```
What's happening is that, even if we create the file layout item_user, it doesn't exist in the app yet. We have to make it exist first, ir "inflate" it. Now that we inflated it, we put it in variable `inflatedView` Next, we add another line
```kotlin
return ViewHolder(inflatedView)
```
which basically means we created an instance of ViewHolder with `inflatedView` as its argument, and return it.

In function `onBindViewHolder` we want to call function `bind` inside the instance of ViewHolder we created. We add this line
```kotlin
return holder.bind(user = users[position])
```
The `user =` is not necessary.

As for function `getItemCount` we want to return the number of item inside `users` ArrayList. Guess what you should add! You only need one line.

### Step 4: MainActivity

After creating a very confusing `RecyclerAdapter` class, what should we do next? Well, even if we created that class, it will not do anything until we specify it to do so. To do this, we return to our `MainActivity`

Now, before we do anything, we should get a set of data to be shown. For the sake of demonstration, let's create an ArrayList of User and add 3 instances of User inside.
```kotlin
var users = ArrayList<User>()
users.add(User())
users.add(User())
users.add(User())
```
This piece of codes should go inside `onCreate` after `setContentView` I said "for the sake of demonstration" because you should ***NEVER*** put your data anywhere inside a class that controls a view, such as `MainActivity`

Now we have to tell our RecyclerView how to represent the data. First, we add this line
```kotlin
user_recycler.layoutManager = LinearLayoutManager(this)
```
This line tells `RecyclerView` to represent data linearly. The default is to show data vertically, from first item in ArrayList to last. It's the same as
```kotlin
user_recycler,layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
```
Second argument determines which direction it should goes, and third argument determines if items are shown in reverse order or not. Next we add the last line (finally!)
```kotlin
user_recycler.adapter = RecyclerAdapter(users)
```
This is basically setting the Adapter for our RecyclerView.

Now that we finished, when our RecyclerView is inflated, it will know which dataset to use, and how data should be shown! If you run your application, the 3 users should be shown properly!

### Now what?

Well, it's time to do your homework of course! The application you should make is a MockTweet. The requirements are:
```
2 Activities(or pages):
  - first activity contains a RecyclerView showing tweets, and a FloatingActionButton to go to the next Activity.
  - second activity contains 2 fields to enter: a name and a text. There should be a submit button as well.
Each tweet should contain:
  - a username
  - a text
  - a date and time
On startup, there should be 3 default tweets. When you submit a new tweet, the second Activity must close by itself and
return to the first activity. The new tweet should be added. If you go on to second Activity and pressed back,
you should return to first activity.
```
![Imgur](https://i.imgur.com/ZJxDCpyl.jpg) ![Imgur](https://i.imgur.com/Q5ienhol.jpg)

You will have to figure out how to use `FloatingActionButton` and how to transfer data across Activities. [This article](https://proandroiddev.com/modern-android-development-with-kotlin-september-2017-part-1-f976483f7bd6) can give you some ideas, but it may be too advanced for you now.
