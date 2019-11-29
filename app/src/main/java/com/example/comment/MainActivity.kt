package com.example.comment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_rl_collapse.*
import kotlinx.android.synthetic.main.include_rl_expend.*

class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    var colorMask: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        colorMask = ContextCompat.getColor(this, R.color.colorPrimary)
        appBarLayout.addOnOffsetChangedListener(this)

        iv_close.setOnClickListener { v ->
            this.finish()
        }

    }

    override fun onOffsetChanged(appBar: AppBarLayout?, verticalOffset: Int) {
        val absVerticalOffset = Math.abs(verticalOffset)
        val totalScrollRange = appBar?.totalScrollRange

        if (absVerticalOffset <= totalScrollRange!! / 2) {
            rl_title_expand.visibility = View.VISIBLE
            rl_title_collapse.visibility = View.GONE
            //根据偏移百分比 计算透明值
            val scale1: Float = absVerticalOffset / (totalScrollRange!! / 2).toFloat()

            val alpha1 = (255 * scale1).toInt()
            val argb1 = Color.argb(
                alpha1,
                0, 133, 119
            )

            bg_title_expand.setBackgroundColor(argb1)
        } else {
            rl_title_expand.visibility = View.GONE
            rl_title_collapse.visibility = View.VISIBLE
            val scale2: Float =
                (totalScrollRange!! - absVerticalOffset) / (totalScrollRange / 2).toFloat()
            val alpha2 = (255 * scale2).toInt()
            val argb2 = Color.argb(
                alpha2,
                0, 133, 119
            )
            bg_title_collapse.setBackgroundColor(argb2)
        }
    }


}


//appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
//    override fun onStateChanged(
//        appBarlayout: AppBarLayout,
//        state: State,
//        verticalOffset: Int
//    ) {
//        val absVerticalOffset = Math.abs(verticalOffset)
//        val totalScrollRange = appBarlayout.totalScrollRange
//
////                if (state == State.EXPANDED) {
////                    iv_agenda.visibility = View.GONE
////                    iv_search.visibility = View.GONE
////                } else if (state == State.COLLAPSED) {
////                    iv_agenda.visibility = View.VISIBLE
////                    iv_search.visibility = View.VISIBLE
////                }
//
//    }
//
//})
