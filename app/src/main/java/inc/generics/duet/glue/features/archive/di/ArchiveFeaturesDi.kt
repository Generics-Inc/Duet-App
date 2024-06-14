package inc.generics.duet.glue.features.archive.di

import inc.generics.archive.vm.ArchiveViewModel
import inc.generics.archive.vm.ItemArchiveBottomSheetViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val archiveFeaturesModule = module {
    viewModelOf(::ArchiveViewModel)
    viewModelOf(::ItemArchiveBottomSheetViewModel)
}