package com.ghitatomy.yooxshop.data.repository.mapper

interface BaseMapper<in inModel, out outModel> {
    fun map(inModel: inModel): outModel
    fun map(inList: List<inModel>): List<outModel>
}