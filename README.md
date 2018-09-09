## Generic-RecyclerView-Adapter

An android library to simplify and remove the boilerplate of creating RecyclerView adapters

### Download

You can add the library to your project via gradle

Step 1: Add in your root build.gradle of your project
```
    allprojects {
	repositories {
	...
	maven { url 'https://jitpack.io' }
	}
    }
```

Step 2: Add the dependency to your app gradle in the app folder
```

    dependencies {
    	...
	implementation 'com.github.tirgei:Generic-RecyclerView-Adapter:1.0.0'
	...
    }
    
   
```

### Usage

Initialize the RecyclerViewAdapter class in your activity/ fragment. It takes in 2 parameters, the layout of the RecyclerView item to be inflated and the callback for the RecyclerView items. Then set it to your RecyclerView

```kotlin

    private fun initViews() {
        // rv is an instance of the RecyclerView

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.itemAnimator = DefaultItemAnimator()

        // Initialise the adapter and set it to the ReyclerView
        val adapter = RecyclerViewAdapter(R.layout.item_contacts, this)
        rv.adapter = adapter
    }

```

Implement AdapterCallback interface and override the bindViews and OnViewClicked functions

```kotlin

    class MainActivity : AppCompatActivity(), AdapterCallback {
    
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

```

To add items to the RecyclerViewAdapter, 2 functions are provided

```kotlin
    /**
     *  Function to add single item to the RecyclerView
     */
    fun addItem(item: Any) {
        this.items.add(item)
        notifyItemInserted(items.size-1)
    }

    /**
     *  Function to add a list of items to the RecyclerView
     */
    fun addItems(items: List<Any>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }
```

Here is an example of adding a list of contacts to the adapter
```kotlin

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
    
```

To clear the adapter, clearItems() function is provided
```kotlin

    /**
     *  Function to clear the RecyclerView
     */
    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }
   
```

### To do
1. Add click listener for individual items in the inflated layout

### License
MIT License

Copyright (c) 2018 Vincent Tirgei

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
