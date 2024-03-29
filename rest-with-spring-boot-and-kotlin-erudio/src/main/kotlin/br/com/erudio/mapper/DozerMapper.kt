package br.com.erudio.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object DozerMapper {
    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <O, D> parseObject(origin: O, destination: Class<D>?): D {
        return mapper.map(origin, destination)
    }
    fun <O, D> parseListObjects(originsList: List<O>, destination: Class<D>?): ArrayList<D> {
        val destinationObjects: ArrayList<D> = ArrayList<D>()
        for (origin in originsList){
            destinationObjects.add(mapper.map(origin, destination))
        }
        return destinationObjects
    }
}