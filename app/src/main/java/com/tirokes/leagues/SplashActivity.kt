package com.tirokes.leagues

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.tirokes.leagues.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    private var coroutineTwo : Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBall()
        goLogo()
    }
    private fun goBall(){
        coroutine = coroutineScope.launch {
            delay(300)
            Glide.with(this@SplashActivity).asGif().load(R.drawable.ball).into(binding.ivBall)
            binding.ivBall.startAnimation(AnimationUtils.loadAnimation(this@SplashActivity, R.anim.scale_ball))
            ObjectAnimator.ofFloat(binding.ivBall, "translationX", -300f).apply {
                duration = 1700
                start()
            }
            ObjectAnimator.ofFloat(binding.ivBall, "translationY", 300f).apply {
                duration = 1700
                start()
            }
            delay(1700)
            Glide.with(this@SplashActivity).asGif().load(R.drawable.ball).into(binding.ivBall)
            binding.ivBall.startAnimation(AnimationUtils.loadAnimation(this@SplashActivity, R.anim.scale_ball_two))
            ObjectAnimator.ofFloat(binding.ivBall, "translationX", -50f).apply {
                duration = 1700
                start()
            }
            ObjectAnimator.ofFloat(binding.ivBall, "translationY", 300f).apply {
                duration = 1700
                start()
            }
            coroutine?.cancel()
        }
    }
    private fun goLogo(){
        coroutineTwo = coroutineScope.launch {
            delay(300)
            ObjectAnimator.ofFloat(binding.ivLogo, "translationY", -600f).apply {
                duration = 1700
                start()
            }
            delay(1700)
            ObjectAnimator.ofFloat(binding.tvLeagues, "translationY", -300f).apply {
                duration = 1700
                start()
            }
            delay(1550)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            coroutineTwo?.cancel()
        }
    }
}