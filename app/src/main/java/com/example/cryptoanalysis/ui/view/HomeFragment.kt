package com.example.cryptoanalysis.ui.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.databinding.FragmentHomeBinding
import com.example.cryptoanalysis.ui.viewmodel.HomeViewModel
import com.example.cryptoanalysis.ui.adapters.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val homeViewModel : HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerview :RecyclerView = binding.recyclerView

        var list = ArrayList<Coin>()
        list.add(Coin(name = "Bitcoin", symbol = "BTC", color = "#f7931A", price = 21173.51734301634f, iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg"))

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val RecyclerAdapter = RecyclerAdapter(list, requireContext())

        RecyclerAdapter.setItemListener(object : RecyclerAdapter.onItemClickListener {
            override fun onClickListener(position: Int) {
                Log.d("Clicked", "element - ${list[position]}")
            }

        })
        recyclerview.adapter = RecyclerAdapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}