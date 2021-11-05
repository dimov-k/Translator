package ru.mrroot.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.mrroot.translator.R
import ru.mrroot.translator.data.AppState
import ru.mrroot.translator.data.word.Word
import ru.mrroot.translator.databinding.ActivityMainBinding
import ru.mrroot.translator.utils.convertMeaningsToString
import ru.mrroot.translator.utils.isOnline
import ru.mrroot.translator.view.base.BaseActivity
import ru.mrroot.translator.view.description.DescriptionActivity
import ru.mrroot.translator.view.history.HistoryActivity
import ru.mrroot.translator.view.main.adapter.MainAdapter

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            fromRemote = true
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: Word) = showDescription(data)
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, fromRemote)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    private var fromRemote = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    private fun showDescription(data: Word) {
        startActivity(
            DescriptionActivity.getIntent(
                this@MainActivity,
                data.text!!,
                convertMeaningsToString(data.meanings!!),
                data.meanings[0].imageUrl
            )
        )
    }

    override fun setDataToAdapter(data: List<Word>) {
        adapter.setData(data)
        if (!fromRemote) showDescription(data = data[0])
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.menu_search_room -> {
                fromRemote = false
                val searchDialogFragment = SearchDialogFragment.newInstance()
                searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
                searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

}