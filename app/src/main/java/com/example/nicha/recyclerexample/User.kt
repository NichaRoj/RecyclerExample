package com.example.nicha.recyclerexample

/**
 * Created by Nicha on 3/30/2018.
 */
class User (u: String = "DefaultUser", p: String = "DefaultPlace"){
    //This is the class where we specify what User should have
    //Its variables are called "attributes" and its functions are functions.

    //(u: String = "DefaultUser", p: String = "DefaultPlace")
    //is how we specify the constructor, or "how to create this class"
    //In constructor, we accept two string parameters which will be
    //temporarily called u and p.
    //However, when creating a User instance, you don't have to define
    // these parameters as we specify the default values to be
    //"DefaultUser" and "DefaultPlace"
    //e.g.  if var a = User() then a.name = "DefaultUser" and a.place = "DefaultPlace"
    //      if var b = User("Mary") then b.name = "Mary" and b.place = "DefaultPlace"
    //      if var c = User("Mary","Bangkok") then c.name = "Mary" and c.place = "Bangkok"
    //      if var d = User(place="Bangkok") then d.name = "DefaultUser" and d.place = "Bangkok"
    //This syntax also applies with functions.

    //Here, User will have 2 attributes called name and place.
    var name: String = u
    var place: String = p

    //This is the long way of writing this class
    //You can just write:
    //class User (var name: String = "DefaultUser", var place: String = "DefaultPlace")
}