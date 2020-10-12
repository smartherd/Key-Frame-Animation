package com.sriyank.keyframeanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
	private var isDetailLayout = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		constraintLayout.setOnClickListener {
			if (isDetailLayout)
				swapFrames(R.layout.activity_main) // switch to default layout
			else
				swapFrames(R.layout.activity_main_detail) // switch to detail layout
		}
	}

	private fun swapFrames(layoutId: Int){

		val constraintSet = ConstraintSet()
		constraintSet.clone(this, layoutId)

		val transition = ChangeBounds()
		transition.interpolator = AnticipateOvershootInterpolator(1.0f)
		transition.duration = 1200

		TransitionManager.beginDelayedTransition(constraintLayout, transition)

		constraintSet.applyTo(constraintLayout)

		isDetailLayout = !isDetailLayout
	}
}
