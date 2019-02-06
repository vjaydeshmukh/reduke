package org.wit.reduke.models

interface PostStore {
    fun findAll(): List<PostModel>
    fun create(post: PostModel)
    fun update(post: PostModel)
    fun delete(post: PostModel)
}