package com.irv205.codingchallenge2.data.mapper

import com.irv205.codingchallenge2.domain.model.GenericModelDomain

fun toDomainModel(
    img: String?,
    title: String,
    description: String) : GenericModelDomain = GenericModelDomain(img, title, description)