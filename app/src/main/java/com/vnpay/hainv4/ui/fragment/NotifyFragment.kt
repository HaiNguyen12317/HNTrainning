package com.vnpay.hainv4.ui.fragment


import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vnpay.hainv4.R
import com.vnpay.hainv4.databinding.FragmentNotifyBinding
import com.vnpay.hainv4.model.Growth
import com.vnpay.hainv4.view.ChartLineScrollView


class NotifyFragment : Fragment() {
    private lateinit var binding: FragmentNotifyBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifyBinding.inflate(inflater)
        val growths = arrayListOf<Growth>(
            Growth(2000, 1000f),
            Growth(2001, 170f),
            Growth(2002, 2380f),
            Growth(2003, 1210f),
            Growth(2004, 370f),
            Growth(2005, 1811f),
            Growth(2006, 182f),
            Growth(2007, 2476f),
            Growth(2008, 5476f),
            Growth(2008, 3476f),
            Growth(2008, 1476f),
            Growth(2008, 2476f),
            Growth(2007, 2476f),
            Growth(2008, 5476f),
            Growth(2008, 3476f),
            Growth(2008, 1476f),
        )

        binding.sfv.initData(growths)
//        binding.sfv.layoutParams =
//            LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, growths.size * 100)

        binding.btnZoom.setOnClickListener {
            if (binding.crvLineChart.visibility == View.VISIBLE){
                binding.crvLineChart.visibility = View.GONE
                binding.chartView.visibility = View.VISIBLE
                binding.chartView.startAnimation(AnimationUtils.loadAnimation(requireContext(),
                    R.anim.zoom_in))
                binding.crvLineChart.startAnimation(AnimationUtils.loadAnimation(requireContext(),
                    R.anim.zoom_out))
            } else if (binding.crvLineChart.visibility == View.GONE){
                binding.crvLineChart.visibility = View.VISIBLE
                binding.crvLineChart.startAnimation(AnimationUtils.loadAnimation(requireContext(),
                    R.anim.zoom_in))
                binding.chartView.visibility = View.GONE
                binding.chartView.startAnimation(AnimationUtils.loadAnimation(requireContext(),
                    R.anim.zoom_out))

            }
        }
        return binding.root

    }

}

