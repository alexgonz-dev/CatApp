package com.catapp.domainapp.domain.model

import com.google.gson.Gson

class CatAppEntity : ArrayList<CatAppEntityItem>() {
    companion object {
        fun convertFromConfig(config: String): CatAppEntity? {
            if (config.isEmpty())
                return null
            return try {
                val gson = Gson()
                gson.fromJson(config, CatAppEntity::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}