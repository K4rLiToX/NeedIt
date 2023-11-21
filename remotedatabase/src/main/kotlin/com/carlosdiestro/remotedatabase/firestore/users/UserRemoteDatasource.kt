package com.carlosdiestro.remotedatabase.firestore.users

interface UserRemoteDatasource {
    fun upsert(user: UserDto)
}