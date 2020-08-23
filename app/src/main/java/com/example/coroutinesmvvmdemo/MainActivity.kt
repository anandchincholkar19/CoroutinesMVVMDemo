package com.example.coroutinesmvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinesmvvmdemo.model.User
import com.example.coroutinesmvvmdemo.utils.Status
import com.example.coroutinesmvvmdemo.viewmodel.UserViewModel
import com.example.coroutinesmvvmdemo.viewmodel.UserViewModelFactory
import com.example.coroutinesmvvmdemo.webservicecommunication.ApiHelperImpl
import com.example.coroutinesmvvmdemo.webservicecommunication.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        setUpUI()
        setUpObservers()
    }

    private fun setUpObservers() {
        userViewModel.getUSers().observe(this, Observer{
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource?.data.let { user->
                            Log.e("ManActivity : ", user.toString())
                            retrieveList(user as ArrayList<User>)
                        }
                    }

                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }

                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setUpUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter = adapter
    }

    private fun setUpViewModel() {
        val viewModelFactory = UserViewModelFactory(ApiHelperImpl(RetrofitBuilder.getApiService()))
        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private fun retrieveList(users: ArrayList<User>) {
        adapter.apply {
            addUser(users)
            notifyDataSetChanged()
        }
    }


}
