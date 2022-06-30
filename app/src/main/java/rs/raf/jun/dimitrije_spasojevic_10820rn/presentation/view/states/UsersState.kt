package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states

import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserFull
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserUpdateDto


sealed class UsersState {
    object Loading: UsersState()
    object DataFetched: UsersState()
    data class Success(val user: UserFull): UsersState()
    data class Error(val message: String): UsersState()
}