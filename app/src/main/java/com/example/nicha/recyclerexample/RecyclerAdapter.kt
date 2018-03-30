package com.example.nicha.recyclerexample

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by Nicha on 3/30/2018.
 */
class RecyclerAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    //What is this class? This class is what ties the User list we have and RecyclerView together
    //BE CAREFUL OF SYNTAX. THERE IS A LOT GOING ON
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //override means that this function defines the "interface" function inside the class that we extended.
        //(I kinda forgot to tell you that you can create "interface" function but it's called abstract function instead)
        //the ViewHolder after colon(:) means that this function returns a ViewHolder instance.
        //this function basically creates an instance of ViewHolder class (specified below)

        //val is a variable that cannot be changed. In Java it's the same as final Object o. There's no equivalent in Python.
        //The line below creates a variable (that cannot be changed) that contains item_user.
        //We cannot call R.layout.item_user directly because item_user is not linked to anything(it doesn't have tools:context in its file)
        //so we have to inflate it first. (Imagine a layout as a balloon. If you want to make it appear, you have to inflate it first.)
        val inflatedView = parent.inflate(R.layout.item_user, false)
        //because this function
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(user = users[position]) //the user = is not necessary.

    override fun getItemCount(): Int = users.size //this function basically returns how many items we have in our list (or users in list of users)

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        //Recall .inflate in onCreateViewHolder
        //The thing is inflate function was not in class ViewGroup, so if you write that line first
        //before writing this function, it will be red. So we cheat by creating a new function for ViewGroup directly
        //See ViewGroup.inflate? It means that we are telling the gradle that
        //"Hey, ViewGroup is gonna have a new function called inflate, okay? Okay."
        //This is unique in Kotlin. In Java, you would have to make a new class and make it extends from ViewGroup.

        //basically the long way of making it inflate.
        //so if you don't want to write val inflatedView = parent.inflate(R.layout.item_user, false)
        //then you can write LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false) instead
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    class ViewHolder(private var v: View): RecyclerView.ViewHolder(v){
        //SURPRISE class can be inside another class.
        //This class tells Adapter where each attribute in User should be shown on each view in item_user layout.
        //i.e. the text of view user_name is name of an User instance.
        //function onBind can be any name, but we call it onBind or bind just for easy understanding.
        fun onBind(user: User){
            //instead of writing v.user_name.text and v.user_place.text
            //we can use with block to omit v in front instead.
            //it's just a way to shorten the code.
            with(v){
                user_name.text = user.name
                user_place.text = user.place
            }
        }
    }
}