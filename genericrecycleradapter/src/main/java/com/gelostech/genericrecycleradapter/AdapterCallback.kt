package com.gelostech.genericrecycleradapter

import android.view.View

/**
 *  This interface is used for handling the layout items.
 *  Provides functions to inflate the views and to handle view clicks
 */
interface AdapterCallback {

    /**
     *  Function to set the data to the views in the layout item
     *  @param view -> The layout item view. You can use this to get single widget in the layout item
     *  @param item -> The data item to be set to the layout. Cast this to your object type
     *  @param position -> The position of the item in the RecyclerView
     */
    fun bindViews(view: View, item: Any, position: Int)

    /**
     * Function to handle clicks on the layout item
     *  @param view -> The layout item view. You can use this to get single widget in the layout item
     *  @param item -> The data item to be set to the layout. Cast this to your object type
     */
    fun onViewClicked(view: View, item: Any)

}