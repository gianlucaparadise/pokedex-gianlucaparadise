package com.gianlucaparadise.pokedex.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.gianlucaparadise.pokedex.R
import com.gianlucaparadise.pokedex.vo.NameUrlPair
import com.gianlucaparadise.pokedex.vo.Type
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class TypesListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ChipGroup(context, attrs, defStyle) {

    private var _types: List<Type>? = null
    var types: List<Type>?
        get() = _types
        set(value) {
            if (_types == value) return
            _types = value
            updateTypeChips(value)
        }

    init {
        isSingleLine = true

        //region Code for Android Studio Layout Editor
        if (isInEditMode) {
            this.types = listOf(
                Type(0, NameUrlPair("grass", "")),
                Type(1, NameUrlPair("poison", ""))
            )
        }
        //endregion
    }

    private fun updateTypeChips(types: List<Type>?) {
        this.removeAllViews()
        if (types?.any() != true) return

        for (type in types) {
            try {
                val chip = createChipFromType(type)
                this.addView(chip)

            } catch (ex: Exception) {

            }
        }
    }

    private fun createChipFromType(type: Type): Chip {
        val chip = LayoutInflater.from(context)
            .inflate(R.layout.type_list_view_item, this, false) as Chip

        chip.text = type.type.name

        val color = when (type.type.name) {
            "normal" -> R.color.type_normal
            "fighting" -> R.color.type_fighting
            "flying" -> R.color.type_flying
            "poison" -> R.color.type_poison
            "ground" -> R.color.type_ground
            "rock" -> R.color.type_rock
            "bug" -> R.color.type_bug
            "ghost" -> R.color.type_ghost
            "steel" -> R.color.type_steel
            "fire" -> R.color.type_fire
            "water" -> R.color.type_water
            "grass" -> R.color.type_grass
            "electric" -> R.color.type_electric
            "psychic" -> R.color.type_psychic
            "ice" -> R.color.type_ice
            "dragon" -> R.color.type_dragon
            "dark" -> R.color.type_dark
            "fairy" -> R.color.type_fairy
            "unknown" -> R.color.type_unknown
            "shadow" -> R.color.type_shadow
            else -> R.color.type_other
        }
        chip.chipBackgroundColor = AppCompatResources.getColorStateList(context, color)

        return chip
    }
}

@BindingAdapter("types")
fun bindTypes(view: TypesListView, types: List<Type>?) {
    view.types = types
}