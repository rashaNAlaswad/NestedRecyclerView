package com.rns.nestedrecyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rns.nestedrecyclerview.data.DataSource
import com.rns.nestedrecyclerview.data.MainItem
import com.rns.nestedrecyclerview.databinding.ActivityMainBinding
import com.rns.nestedrecyclerview.ui.adapter.MainAdapter
import com.rns.nestedrecyclerview.data.enums.MainItemType
import com.rns.nestedrecyclerview.util.toMainItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        val itemList: MutableList<MainItem<Any>> = mutableListOf()
        itemList.add(MainItem(DataSource.getAllChannels(), MainItemType.TYPE_CHANNEL))
        itemList.addAll(
            DataSource.getAllPodcasts().map { it.toMainItem() }
        )

        adapter = MainAdapter(itemList)
        binding.recyclerview.adapter = adapter
    }
}