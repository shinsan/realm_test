package tech.coper.sample

import android.provider.ContactsContract
import android.util.Log
import io.realm.Realm

class ProfileDatabase {

    fun fetch(): Profile? {
        val realm = Realm.getDefaultInstance()
        val instance = realm.where(ProfileEntity::class.java).findFirst()
        Log.e("American President", "fetch " + instance?.toModel()?.familyName)
        return instance?.toModel()
    }

    fun store(accountProfile: Profile) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val entity = ProfileEntity(accountProfile)
            it.copyToRealmOrUpdate(entity)
            Log.e( "American President","store " + entity.familyName)
        }
    }

    fun delete() {
        val realm = Realm.getDefaultInstance()
        val entity = realm.where(ProfileEntity::class.java).findFirst()
        realm.executeTransaction {
            entity?.deleteFromRealm()
            Log.e("American President","delete ")
        }
    }
}