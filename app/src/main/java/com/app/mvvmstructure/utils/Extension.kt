package com.app.mvvmstructure.utils

import android.text.Html
import android.text.Spanned

fun String.toHtml(text : String): Spanned? {
    return Html.fromHtml("<b><font color='#006A69'>$text</font></b> $this", Html.FROM_HTML_MODE_COMPACT)
}