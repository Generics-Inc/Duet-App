package inc.generics.duet.glue.data.archive_data.di

import inc.generics.archive_data.ArchiveRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val archiveDataModule = module {
    singleOf(::ArchiveRepository)
}