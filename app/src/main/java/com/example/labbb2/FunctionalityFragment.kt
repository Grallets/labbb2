package com.example.labbb2

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.labbb2.databinding.FragmentFunctionalityBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FunctionalityFragment : Fragment() {

    private var _binding: FragmentFunctionalityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFunctionalityBinding.inflate(inflater, container, false)

        binding.buttonPlaySound.setOnClickListener {
            playSound()
            viewModel.add()
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.textCounter.text = uiState.counterValue.toString()

            }
        }

        return binding.root
    }

    private fun playSound() {
        val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.raygun)
        mediaPlayer.setOnCompletionListener {
            it.release()
        }
        mediaPlayer.start()
        viewModel.add()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class CounterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState

    fun add() {
        _uiState.value = _uiState.value.copy(counterValue = _uiState.value.counterValue + 1)
    }
}

data class CounterUiState(val counterValue: Int = 0)
