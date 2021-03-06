package tech.coper.sample

import android.provider.ContactsContract
import android.util.Log
import io.realm.Realm

class ProfileDatabase {

    fun fetch(): Profile? {
        val realm = Realm.getDefaultInstance()
        val instance = realm.where(ProfileEntity::class.java).findFirst()
        Log.e("American President", "fetch " + instance?.toModel()?.familyName)
        val model = instance?.toModel()
        realm.close()
        return model
    }

    fun store(accountProfile: Profile) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val entity = ProfileEntity(accountProfile)
            it.copyToRealmOrUpdate(entity)
            Log.e( "American President","store " + entity.familyName)
        }
        realm.close()
    }

    fun delete() {
        val realm = Realm.getDefaultInstance()
        val entity = realm.where(ProfileEntity::class.java).findFirst()
        realm.executeTransaction {
            entity?.deleteFromRealm()
            Log.e("American President","delete ")
        }
        realm.close()
    }
}