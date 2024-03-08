package com.catapp.domainapp.domain.usecase

import android.content.Context
import com.catapp.domain.usecase.GetApplicationConfigurationUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetApplicationConfigurationUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GetApplicationConfigurationUseCase {
    companion object {
        const val CONFIG_FILE = "config.json"
    }

    override suspend fun fetchConfiguration(): Result<String> {
        val fileContent = readAssetFile(context, CONFIG_FILE)

        return Result.success(fileContent)
    }

    private fun readAssetFile(context: Context, fileName: String): String {

        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}