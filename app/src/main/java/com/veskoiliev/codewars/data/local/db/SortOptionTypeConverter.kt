package com.veskoiliev.codewars.data.local.db

import android.arch.persistence.room.TypeConverter
import com.veskoiliev.codewars.data.local.model.SortOption

class SortOptionTypeConverter {

    @TypeConverter
    fun fromSortOption(sortOption: SortOption): String = sortOption.column
}