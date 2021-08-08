package com.android.mappers.network

import com.android.aptoide.models.App
import com.android.aptoide.network.entities.NetworkApp
import com.android.utils.EntityMapper
import javax.inject.Inject

class AppNetworkMapper
    @Inject
    constructor(): EntityMapper<NetworkApp, App> {

    override fun mapFromEntity(entity: NetworkApp): App {
        return App(
            id = entity.id,
            addedDate = entity.addedDate,
            apkTags = entity.apkTags,
            downloads = entity.downloads,
            graphic = entity.graphic,
            icon = entity.icon,
            md5sum = entity.md5sum,
            modified = entity.modified,
            name = entity.name,
            appPackage = entity.appPackage,
            pDownloads = entity.pDownloads,
            rating = entity.rating,
            size = entity.size,
            storeId = entity.storeId,
            storeName = entity.storeName,
            updated = entity.updated,
            upType = entity.upType,
            versionCode = entity.versionCode,
            versionName = entity.versionName
        )
    }

    override fun mapToEntity(domainModel: App): NetworkApp {
        return NetworkApp(
            id = domainModel.id,
            addedDate = domainModel.addedDate,
            apkTags = domainModel.apkTags,
            downloads = domainModel.downloads,
            graphic = domainModel.graphic,
            icon = domainModel.icon,
            md5sum = domainModel.md5sum,
            modified = domainModel.modified,
            name = domainModel.name,
            appPackage = domainModel.appPackage,
            pDownloads = domainModel.pDownloads,
            rating = domainModel.rating,
            size = domainModel.size,
            storeId = domainModel.storeId,
            storeName = domainModel.storeName,
            updated = domainModel.updated,
            upType = domainModel.upType,
            versionCode = domainModel.versionCode,
            versionName = domainModel.versionName
        )
    }

    fun mapFromEntityList(entities: Array<NetworkApp>) = entities.map { mapFromEntity(it) }
}