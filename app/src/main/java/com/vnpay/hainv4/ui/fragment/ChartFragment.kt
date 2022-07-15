package com.vnpay.hainv4.ui.fragment

import android.R
import android.content.res.Resources
import android.view.View
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.vnpay.hainv4.base.BaseFragment
import com.vnpay.hainv4.databinding.FragmentChartBinding


class ChartFragment : BaseFragment<FragmentChartBinding>(FragmentChartBinding::inflate) {

    override fun initView() {
        super.initView()
//        arguments?.let { getGrowChart(it.getString("method", "")) }
        getGrowChart()
    }
    //    private fun getGrowChart(method: String) {
//        lifecycleScope.launch(Dispatchers.IO) {
//            val growths: Call<List<Growth>> = GrowthClient.invoke().getGrowth()
//            growths.enqueue(object : Callback<List<Growth>> {
//                override fun onResponse(
//                    call: Call<List<Growth>>,
//                    response: Response<List<Growth>>
//                ) {
//                    if (response.body() != null) {
//                        if (method == "bars") {
//                            val barsEntries = ArrayList<BarEntry>()
//                            for (growth in response.body()!!) {
//                                barsEntries.add(BarEntry(growth.year.toFloat(), growth.growthRate))
//                            }
//                            val barDataSet = BarDataSet(barsEntries, "Growth")
//                            barDataSet.color = ColorTemplate.COLORFUL_COLORS as Int
//
//                            val barData = BarData(barDataSet)
//                            barData.barWidth = 0.9f
//
//                            binding.barChart.visibility = View.VISIBLE
//                            binding.barChart.animateY(5000)
//                            binding.barChart.data = barData
//                            binding.barChart.setFitBars(true)
//
//                            val description = Description()
//                            description.text = "Growth Rate Per Year"
//                            binding.barChart.description = description
//                            binding.barChart.invalidate()
//
//                        } else if (method == "pie") {
//
//                        }
//                    }
//                    Log.d("anhhai","chart: ${response.body()}")
//                }
//
//                override fun onFailure(call: Call<List<Growth>>, t: Throwable) {
//
//                }
//
//
//            }
//
//            )
//        }
//    }
    private fun getGrowChart() {
        val barEntry = ArrayList<BarEntry>()
        barEntry.add(BarEntry( 1008f,  1047f))
        barEntry.add(BarEntry(1009f, 1040f))
        barEntry.add(BarEntry(1010f, 1133f))
        barEntry.add(BarEntry(1011f, 1140f))
        barEntry.add( BarEntry(1012f, 1169f))
        barEntry.add(BarEntry(1013f, 1087f))
        barEntry.add( BarEntry(1014f, 1101f))
        barEntry.add(BarEntry(1015f, 1145f))
        barEntry.add(BarEntry(1016f, 10078f))
        barEntry.add(BarEntry(1017f, 1092f))
        barEntry.add(BarEntry(1018f, 1095f))





//        val year = ArrayList<String>();
//
//        year.add("2008");
//        year.add("2009");
//        year.add("2010");
//        year.add("2011");
//        year.add("2012");
//        year.add("2013");
//        year.add("2014");
//        year.add("2015");
//        year.add("2016");
//        year.add("2017");

        val barDataSet = BarDataSet(barEntry, "Growth")
//        barDataSet.setColors(intArrayOf(ColorTemplate.COLORFUL_COLORS[0], ColorTemplate.COLORFUL_COLORS[1]),requireContext())
   //     barDataSet.colors = Int[]{ColorTemplate.COLORFUL_COLORS[0],1}
        barDataSet.color = ColorTemplate.COLORFUL_COLORS[1]
        val barData = BarData(barDataSet)
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,context)
        barData.barWidth = 0.9f
        binding.barChart.animateY(3000)
        binding.barChart.data = barData
        binding.barChart.setFitBars(true)
        val description = Description()
        description.text = "Growth Rate Per Year"
        binding.barChart.description = description
        binding.barChart.invalidate()
        binding.barChart.visibility = View.VISIBLE
    }
}