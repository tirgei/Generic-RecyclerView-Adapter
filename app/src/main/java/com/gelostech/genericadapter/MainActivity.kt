package com.gelostech.genericadapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gelostech.genericrecycleradapter.AdapterCallback
import com.gelostech.genericrecycleradapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_contacts.view.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AdapterCallback {
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        loadSample()
    }

    /**
     *  Initialize the views for the Activity
     */
    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Generic Adapter"

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.itemAnimator = DefaultItemAnimator()

        adapter = RecyclerViewAdapter(R.layout.item_contacts, this)
        rv.adapter = adapter
    }

    /**
     *  Load some sample contacts and add them to the RecyclerView
     */
    private fun loadSample() {
        val one = Contact("Vincent Tirgei", "+2547212345678")
        adapter.addItem(one)

        val two = Contact("Jane Doe", "+125462466666")
        adapter.addItem(two)

        val three = Contact("James McJiggey", "+45665821654613")
        adapter.addItem(three)
    }

    /**
     *  Override the function to bind the items to the ViewHolder.
     *  You can set the data to the views as you would like
     *  @param view -> The inflated layout. You can use this to get views inside the inflated layout
     *  @param item -> The object to set on the current layout. Cast it to your object type
     *  @param position -> The position of the layout in the RecyclerView
     */
    override fun bindViews(view: View, item: Any, position: Int) {
        val name = view.name
        val pos = view.pos
        val phone = view.phone
        val contact = item as Contact

        pos.text = "#${position+1}"
        name.text = contact.name
        phone.text = contact.phone
    }

    /**
     *  Handle the clicks for the items
     *  @param view -> The layout view
     *  @param item -> The object set on the current layout. Cast it to your object type
     */
    override fun onViewClicked(view: View, item: Any) {
        val contact = item as Contact

        when(view.id) {
            R.id.root -> toast("${contact.name} clicked")
        }
    }
}
