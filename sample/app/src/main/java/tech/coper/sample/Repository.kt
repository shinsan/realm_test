package tech.coper.sample

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository() {
    private val database = ProfileDatabase()


    suspend fun getProfile(): Profile? =
        withContext(Dispatchers.IO) {

            val profile = database.fetch()
            if (profile != null) {
                return@withContext profile
            }

            null
        }

    suspend fun store(profile:Profile) :Unit = withContext(Dispatchers.IO){
        database.store(profile)

    }
    suspend fun delete() :Unit = withContext(Dispatchers.IO){
        database.delete()

    }
}