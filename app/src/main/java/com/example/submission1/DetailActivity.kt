package com.example.submission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submission1.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    companion object{
        const val EXTRA_USER = "detail_user"

        private val TAB_CODES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        val login = intent.getStringExtra(EXTRA_USER)
        binding.tvUserlogin.text = login

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.username = login.toString()
        val viewPage: ViewPager2 = findViewById(R.id.viewPager)
        viewPage.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPage) { tab, position ->
            tab.text = resources.getString(TAB_CODES[position])
        }.attach()

        if (login != null){
            showLoading(true)
            detailViewModel.getDetailUsers(login)
            showLoading(false)
        }

        detailViewModel.detailUsers.observe(this) {detailUser ->
            setUserDetail(detailUser)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbDetail.visibility = View.VISIBLE
        } else {
            binding.pbDetail.visibility = View.GONE
        }
    }

    private fun setUserDetail(Name: DetailUserResponse) {
        Glide.with(this@DetailActivity)
            .load(Name.avatarUrl)
            .into(binding.ivDetailuser)
        binding.tvUserlogin.text = Name.login
        binding.tvName.text = Name.bio as CharSequence?
        binding.tvFollower.text = Name.followersUrl
        binding.tvFollowing.text = Name.followingUrl
    }
}