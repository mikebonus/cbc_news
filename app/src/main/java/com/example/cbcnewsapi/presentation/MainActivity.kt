package com.example.cbcnewsapi.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cbcnewsapi.R
import com.example.cbcnewsapi.presentation.ui.NewsActivity
import com.example.cbcnewsapi.presentation.ui.VideoActivity
import com.example.cbcnewsapi.utils.Extender
import kotlinx.android.synthetic.main.activity_main.*
// change made one.... another pull request 8
class MainActivity : AppCompatActivity() {

    val TAG = "TAG"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!Extender.isOnline(applicationContext)) {
            Toast.makeText(this, "Please check your network", Toast.LENGTH_LONG).show()
        }

        Log.d(TAG, "onCreate: onCreate() is called here.. ")

        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()

                if (selectedItem == "All News") {
                    val intentOne = Intent(this@MainActivity, NewsActivity::class.java)
                    startActivity(intentOne)

                } else {
                    val intentOne = Intent(this@MainActivity, VideoActivity::class.java)
                    startActivity(intentOne)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        val newsTypes = resources.getStringArray(R.array.news_types)
        val arrayAdapter = ArrayAdapter(this@MainActivity, R.layout.dropdown_item, newsTypes)
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}