package de.marxhendrik.skullandbones.core.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Context.inflate(@LayoutRes layout: Int, parent: ViewGroup, attachToRoot: Boolean = false) =
    LayoutInflater.from(parent.context).inflate(layout, parent, attachToRoot)
