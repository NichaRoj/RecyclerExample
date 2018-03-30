package com.example.nicha.recyclerexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is the ArrayList of User instances that we want to put in our RecyclerView
        //I created it here only to demonstrate.
        var users = ArrayList<User>()
        users.add(User()) //Add a default instance of User into ArrayList
        users.add(User())
        users.add(User())
        //This is basically choosing the layout of recycler. LinearLayout means items go only one direction.
        user_recycler.layoutManager = LinearLayoutManager(this)
        //This is basically telling the RecyclerView which Adapter to use.
        user_recycler.adapter = RecyclerAdapter(users)
    }
}
