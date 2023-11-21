package com.carlosdiestro.remote_database.firestore.users

interface UserRemoteDatasource {
    fun upsert(user: UserDto)
}