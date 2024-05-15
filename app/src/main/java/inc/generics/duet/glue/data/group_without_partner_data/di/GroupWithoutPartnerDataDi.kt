package inc.generics.duet.glue.data.group_without_partner_data.di

import inc.generics.group_without_partner.GroupWithoutPartnerRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val groupWithoutPartnerDataModule = module {
    singleOf(::GroupWithoutPartnerRepository)
}