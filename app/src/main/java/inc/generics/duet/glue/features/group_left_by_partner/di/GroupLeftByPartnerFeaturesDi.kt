package inc.generics.duet.glue.features.group_left_by_partner.di

import inc.generics.group_left_by_partner.GroupLeftByPartnerViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val groupLeftByPartnerFeaturesModule = module {
    viewModelOf(::GroupLeftByPartnerViewModel)
}