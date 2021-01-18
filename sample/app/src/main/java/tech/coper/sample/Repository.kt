package tech.coper.sample

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository() {
    private val database = ProfileDatabase()


    suspend fun getProfile(isForce: Boolean): Profile =
        withContext(Dispatchers.IO) {

            if (isForce) {
                database.delete()
            }

            val profile = database.fetch()
            if (profile != null) {
                return@withContext profile
            }

            val response =  Profile(familyName = "Biden",givenName = "Joe",id = "bbbbbb")
            database.store(response)
            response
        }

    suspend fun store(profile:Profile) :Unit = withContext(Dispatchers.IO){
        database.store(profile)

    }
    suspend fun delete() :Unit = withContext(Dispatchers.IO){
        database.delete()

    }
}