package com.android.utils

/**
 * Defining the contract to be implemented on a
 * Pseudo [adapter](https://en.wikipedia.org/wiki/Adapter_pattern) class
 *
 * @param Entity        Object entity (for network or cache interactions)
 * @param DomainModel   Object domain model (used throughout the app - so that there is consistency everywhere in the app)
 */
interface EntityMapper<Entity, DomainModel> {

    /**
     * Convert an [Entity] into a [DomainModel]
     * @return Instance of [DomainModel], built from [entity]'s data
     */
    fun mapFromEntity(entity: Entity): DomainModel

    /**
     * Convert an [DomainModel] into a [Entity]
     * @return Instance of [Entity], built from [DomainModel]'s data
     */
    fun mapToEntity(domainModel: DomainModel): Entity

}