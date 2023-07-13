package com.rns.nestedrecyclerview.util

import com.rns.nestedrecyclerview.data.MainItem
import com.rns.nestedrecyclerview.data.enums.MainItemType
import com.rns.nestedrecyclerview.data.domain.Podcast

fun Podcast.toMainItem(): MainItem<Any> {
    return MainItem(this, MainItemType.TYPE_PODCAST)
}