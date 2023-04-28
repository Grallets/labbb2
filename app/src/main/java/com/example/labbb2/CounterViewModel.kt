import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CounterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState

    fun add() {
        _uiState.value = _uiState.value.copy(counterValue = _uiState.value.counterValue + 1)
    }
}

data class CounterUiState(val counterValue: Int = 0)
