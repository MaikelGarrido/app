package com.example.myapplication.modules.card.presentation.cardlist.composables

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.modules.card.presentation.cardlist.composables.myiconpack.Icon
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Icon)
    return __AllIcons!!
  }
