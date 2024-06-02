package inc.generics.duet.glue.data.group_left_by_partner_data.di

import inc.generics.group_left_by_partner_data.GroupLeftByPartnerRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val groupLeftByPartnerDataModule = module {
    singleOf(::GroupLeftByPartnerRepository)
}