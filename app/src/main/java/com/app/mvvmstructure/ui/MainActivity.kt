package com.app.mvvmstructure.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.mvvmstructure.adapter.ProductAdapter
import com.app.mvvmstructure.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    val adapter by lazy { ProductAdapter() }

    companion object {
        private val TAG = this::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.response.collect {
                    when (it) {
                        ApiState.Empty -> {
                            binding.progressBar.isVisible = false
                        }

                        is ApiState.Failure -> {
                            binding.progressBar.isVisible = false
                        }

                        ApiState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is ApiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.rvProduct.isVisible = true
                            it.data.products?.let { product ->
                                adapter.addAll(product.toMutableList())
                            }
                        }
                    }

                }
            }
        }
    }

    private fun initAdapter() {
        binding.rvProduct.adapter = adapter
    }
}