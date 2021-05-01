package com.test.roomsample.feature.countries.presentation.ui.toolbar

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.test.roomsample.R
import com.test.roomsample.databinding.ViewToolbarCountriesBinding
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarView.Event
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarView.Model
import kotlinx.coroutines.*

class CountriesToolbarViewImpl(
    private val root: ViewGroup,
    private val coroutinesContext: CoroutineScope,
    attachToRoot: Boolean = false
) : BaseMviView<Model, Event>() {

    private var searchJob: Job? = null

    private val viewBinding: ViewToolbarCountriesBinding = ViewToolbarCountriesBinding.inflate(
        LayoutInflater.from(root.context), root, attachToRoot
    )

    init {
        viewBinding.root.run {
            // Не добавляет меню, если уже имеется
            if (menu.size() <= 0) {
                inflateMenu(R.menu.menu_search)
                setOnMenuItemClickListener { menu ->
                    when (menu.itemId) {
                        R.id.action_search -> {
                            initSearchMenu()
                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }

    private fun initSearchMenu() {

        val menuItem = viewBinding.root.menu.getItem(0)

        val searchView = menuItem.actionView as SearchView
        val searchEditText =
            searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)

        val closeBtn = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)

        closeBtn.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.ic_close))

        searchView.maxWidth = android.R.attr.width

        searchEditText.run {
            setTextColor(Color.BLACK)
            setHintTextColor(Color.GRAY)
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.text_size_12)
            )
        }

        searchView.run {
            setIconifiedByDefault(true)
            isIconified = true
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchJob?.cancel()
                    dispatch(event = Event.Search(query = query.orEmpty()))

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    searchJob?.cancel()

                    searchJob = coroutinesContext.launch(Dispatchers.Main) {
                        delay(500)
                        dispatch(event = Event.Search(query = newText.orEmpty()))
                    }

                    return true
                }
            })
        }
    }
}