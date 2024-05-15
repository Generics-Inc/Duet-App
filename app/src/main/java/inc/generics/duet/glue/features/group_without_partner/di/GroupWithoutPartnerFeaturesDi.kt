package inc.generics.duet.glue.features.group_without_partner.di

import inc.generics.group_without_partner.GroupWithoutPartnerViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val groupWithoutPartnerFeaturesModule = module {
    viewModelOf(::GroupWithoutPartnerViewModel)
}