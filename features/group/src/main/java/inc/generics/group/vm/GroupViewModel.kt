package inc.generics.group.vm

import androidx.lifecycle.ViewModel
import inc.generics.group_data.GroupRepository

class GroupViewModel(
    private val repository: GroupRepository
) : ViewModel() {
}