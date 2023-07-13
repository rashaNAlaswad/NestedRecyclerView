package com.rns.nestedrecyclerview.data

import com.rns.nestedrecyclerview.data.enums.MainItemType

data class MainItem<T>(
    var item: T,
    val type: MainItemType
)