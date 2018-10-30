package bloder.com.android_coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bloder.com.presentation.SearchState
import bloder.com.presentation.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(SearchViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search_button.setOnClickListener {
            viewModel.search("")
        }
        observeChanges()
    }

    private fun observeChanges() {
        viewModel.state().observe(this, Observer<SearchState> { when(it) {
            is SearchState.OnSearched -> text.text = it.search.name
        }})
    }
}
