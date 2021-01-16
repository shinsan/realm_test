package tech.coper.sample

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            // todo
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)

    }



}