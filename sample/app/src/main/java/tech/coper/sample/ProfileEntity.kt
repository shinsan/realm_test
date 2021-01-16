package tech.coper.sample

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey



open class ProfileEntity(
    var familyName: String = "",
    var givenName: String = "",
    @PrimaryKey var id: String = "",

) : RealmObject() {

    constructor(account: Profile) : this(
        familyName = account.familyName,
        givenName = account.givenName,
        id = account.id,
    )

    fun toModel(): Profile {
        return Profile(
            givenName = givenName,
            familyName = familyName,
            id = id,
        )
    }
}
