package inc.generics.archive.vm

import androidx.lifecycle.ViewModel
import inc.generics.archive_data.ArchiveRepository

class ArchiveViewModel(
    private val repository: ArchiveRepository
) : ViewModel() {
}