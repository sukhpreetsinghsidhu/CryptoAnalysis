package com.example.cryptoanalysis.ui.view



import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoanalysis.data.api.Api
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.databinding.FragmentDiscoverBinding
import com.example.cryptoanalysis.ui.viewmodel.DiscoverViewModel
import com.example.cryptoanalysis.ui.adapters.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    var list = ArrayList<Coin>()
    lateinit var recyclerAdapter: RecyclerAdapter
    var limit = 8
    var offset= 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val vm : DiscoverViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

       recyclerAdapter =  RecyclerAdapter(list, requireContext())
         searchCoin()

        recyclerAdapter = RecyclerAdapter(list, requireContext())
        val recyclerview : RecyclerView = binding.recyclerview
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter.setItemListener(object : RecyclerAdapter.onItemClickListener {
            override fun onClickListener(position: Int) {
                Log.d("Clicked", "element - ${list[position]}")
            }
        })
        recyclerview.adapter = recyclerAdapter
        Api.seturl("https://coinranking1.p.rapidapi.com/")
        Log.d("Retrofit: fragement ", "${Api.geturl()}")
        LoadData(limit,offset)
        return root
    }

    private fun LoadData(limit: Int, offset: Int) {
        vm.getAllCoins(limit, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
//                    println("ResponseCoins:: $it")
                    list = it.data.coins as java.util.ArrayList<Coin>
                    recyclerAdapter.setItem(list)

                },
                onComplete = {
                    //println("Completed, $list")
                    //moneyadapter.setItem(list)
                },

                onError = { e ->
                    println("error :: $e")
                }
            )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    ///////*****************************


    lateinit var searchText :String
             //var list = ArrayList<Coin>()
            // var recyclerAdapter: RecyclerAdapter = RecyclerAdapter(list, requireContext())

    fun searchCoin() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                searchText = s.toString().lowercase()
                updateRecyclerView()
                //recyclerAdapter.setItem()
            }

        })
    }

    private fun updateRecyclerView() {
        val data = ArrayList<Coin>()

        for (item in list) {

            var coinName = item.name.lowercase(Locale.getDefault())
            var coinSymbol = item.symbol.lowercase(Locale.getDefault())

            if (coinName.contains(searchText) || coinSymbol.contains(searchText)) {
                data.add(item)
            }
        }
       // recyclerAdapter.updateData(data)
        recyclerAdapter.setItem(data)


    }
}


