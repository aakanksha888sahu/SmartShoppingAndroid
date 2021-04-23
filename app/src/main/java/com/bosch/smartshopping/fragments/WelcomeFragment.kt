package com.bosch.smartshopping.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bosch.smartshopping.navigators.WelcomeNavigator
import com.bosch.smartshopping.viewmodel.WelcomeViewModel
import com.bosch.smartshopping.R
import com.kaveri.smartshopping.databinding.FragmentWelcomeBinding
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {

    private val TAG = "MyHomeFragment"
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        binding = FragmentWelcomeBinding.bind(view)
        //require activity always returns an activity and not null.
        //If the activity is not attached to the fragment, throws Illegal argument exception
        //mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getSharedViewModel<MainViewModel<MainNavigator>>().uploadData()
    }

    override fun onResume(){
        Log.d(TAG, "onResume")
        super.onResume()
        updateViewModelData()
        setListeners()
    }

    private fun updateViewModelData() {
        //getSharedViewModel<MainViewModel<MainNavigator>>().
    }

    private fun setListeners() {
        getSharedViewModel<WelcomeViewModel<WelcomeNavigator>>().screenName.observe(requireActivity(),
            Observer {
                Log.d(TAG, "screen $it")
            })
        getSharedViewModel<WelcomeViewModel<WelcomeNavigator>>().messageFRomService.observe( requireActivity(),
            Observer {
            binding.statusMsgTv.text = it
            })
    }


}