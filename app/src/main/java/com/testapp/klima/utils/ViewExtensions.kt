package com.testapp.klima.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

fun String.toBitmap(textSize: Float) : Bitmap {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.textSize = textSize
    paint.textAlign = Paint.Align.LEFT
    val baseline: Float = -paint.ascent() // ascent() is negative

    val image = Bitmap.createBitmap(
        (paint.measureText(this)).toInt(),
        (baseline + paint.descent()).toInt(),
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(image)
    canvas.drawText(this, 0F, baseline, paint)
    return image
}