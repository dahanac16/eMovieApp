package com.example.emovie.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emovie.R
import com.example.emovie.utilities.Constants
import com.example.emovie.view.adapter.MovieHomeAdapter
import com.example.emovie.databinding.FragmentHomeMovieBinding
import com.example.emovie.model.Result
import com.example.emovie.view.adapter.MovieHomeForGritAdapter
import com.example.emovie.viewmodel.HomeViewModel

class HomeMovieFragment : Fragment(), MovieHomeAdapter.MovieAdapterListener {
    private lateinit var binding: FragmentHomeMovieBinding
    private lateinit var adapterForGrit: MovieHomeForGritAdapter
    private lateinit var adapterGeneric1: MovieHomeAdapter
    private lateinit var adapterGeneric2: MovieHomeAdapter
    private var isFilterIdiom = true
    private val movieListTrend = ArrayList<Result>()
    private val movieListNextReleases = ArrayList<Result>()
    private val movieListFilter = ArrayList<Result>()
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFunViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMovieBinding.inflate(inflater, container, false)
        initObserver()
        initOnClick()
        initAdapters()
        return binding.root
    }

    private fun initFunViewModel() {
        viewModel.searchByTrend(Constants.Home.QUERYTREND)
        viewModel.searchByNextReleases(Constants.Home.QUERYNEXTRELEASES)
        viewModel.searchByFilter(Constants.Home.QUERYFILTERIDIOM, isFilterIdiom)
    }

    private fun initAdapters() {
        adapterForGrit = MovieHomeForGritAdapter(movieListFilter, this)
        binding.recyclerViewFilter.layoutManager = GridLayoutManager(requireContext(), 2)
        adapterGeneric1 = MovieHomeAdapter(movieListTrend, this)
        adapterGeneric2 = MovieHomeAdapter(movieListNextReleases, this)
        binding.recyclerViewTrend.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewNextReleases.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){

        viewModel.successTrend.observe(viewLifecycleOwner) {
            it.results?.let { it1 ->
                movieListTrend.addAll(it1)
                val myAdapter = adapterGeneric1
                binding.recyclerViewTrend.adapter = myAdapter
                myAdapter.notifyDataSetChanged()
            }
        }

        viewModel.successNextReleases.observe(viewLifecycleOwner) {
            it.results?.let { it1 ->
                movieListNextReleases.addAll(it1)
                val myAdapter = adapterGeneric2
                binding.recyclerViewNextReleases.adapter = myAdapter
                myAdapter.notifyDataSetChanged()
            }
        }

        viewModel.successFilter.observe(viewLifecycleOwner) {
            it.results?.let { it1 ->
                movieListFilter.clear()
                movieListFilter.addAll(it1)
                val myAdapter = adapterForGrit
                binding.recyclerViewFilter.adapter = myAdapter
                myAdapter.notifyDataSetChanged()
            }
        }

        viewModel.errorTrend.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.recyclerViewTrend.visibility = View.GONE
                    binding.textErrorTrend.visibility = View.VISIBLE
                    viewModel.finisErrors()
                }
            }
        }

        viewModel.errorNextReleases.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.recyclerViewNextReleases.visibility = View.GONE
                    binding.textErrorNextReleases.visibility = View.VISIBLE
                    viewModel.finisErrors()
                }
            }
        }

        viewModel.errorFilter.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.recyclerViewFilter.visibility = View.GONE
                    binding.textErrorFilter.visibility = View.VISIBLE
                    viewModel.finisErrors()
                }
            }
        }
    }

    private fun initOnClick() {
        with(binding) {
            filterDate.setOnClickListener {
                isFilterIdiom = false
                filterIdiom.setBackgroundDrawable(getDrawable(requireContext(),R.drawable.style_bottom_detail))
                filterIdiom.setTextColor(getColor(requireContext(),R.color.white))
                filterDate.setBackgroundDrawable(getDrawable(requireContext(),R.drawable.style_bottom_filter))
                filterDate.setTextColor(getColor(requireContext(),R.color.black))
                viewModel.searchByFilter(Constants.Home.QUERYFILTERDATE, isFilterIdiom)
            }
            filterIdiom.setOnClickListener {
                isFilterIdiom = true
                filterDate.setBackgroundDrawable(getDrawable(requireContext(),R.drawable.style_bottom_detail))
                filterDate.setTextColor(getColor(requireContext(),R.color.white))
                filterIdiom.setBackgroundDrawable(getDrawable(requireContext(),R.drawable.style_bottom_filter))
                filterIdiom.setTextColor(getColor(requireContext(),R.color.black))
                viewModel.searchByFilter(Constants.Home.QUERYFILTERIDIOM, isFilterIdiom)
            }
        }
    }

    override fun onClick(movie: Result) {
        findNavController().navigate(R.id.detailMovieFragment, bundleOf("movie" to movie))
    }

}