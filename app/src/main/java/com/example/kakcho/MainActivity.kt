package com.example.kakcho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.kakcho.adapters.IconsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var rvImages: RecyclerView

    lateinit var adapter: IconsAdapter

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvImages = findViewById(R.id.rv_icons)
        adapter = IconsAdapter()
        rvImages.adapter = adapter
        progressBar = findViewById(R.id.progress_circular)

        // set search-view
        findViewById<SearchView>(R.id.search_view).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.makeApiCall(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        viewModel.uiList.observe(this){
            adapter.submitList(it)
        }
        viewModel.error.observe(this){
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.isLoading.observe(this){
            progressBar.isVisible = it
        }

        // make api call
        viewModel.makeApiCall()
    }
}