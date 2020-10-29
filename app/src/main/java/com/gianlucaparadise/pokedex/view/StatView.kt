package com.gianlucaparadise.pokedex.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.getIntegerOrThrow
import com.gianlucaparadise.pokedex.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.stat_view.view.*
import kotlin.Exception

class StatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : MaterialCardView(context, attrs, defStyle) {

    private var _statValue: Int? = null
    var statValue: Int?
        get() {
            return _statValue
        }
        set(value) {
            txt_stat_value.text = value?.toString() ?: ""
        }

    var statName: String?
        get() {
            return txt_stat_name.text.toString()
        }
        set(value) {
            txt_stat_name.text = value
        }

    init {
        this.radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            context.resources.displayMetrics
        )

        val typedValue = TypedValue()
        context.theme.resolveAttribute (android.R.attr.colorAccent, typedValue, true)
        val colorAccent = typedValue.data
        this.setCardBackgroundColor(colorAccent)

        inflate(context, R.layout.stat_view, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StatView)

        this.statName = attributes.getString(R.styleable.StatView_statName) ?: ""

        try {
            this.statValue = attributes.getIntegerOrThrow(R.styleable.StatView_statValue)
        } catch(ex: Exception) {
            this.statValue = null
        }

        attributes.recycle()
    }
}