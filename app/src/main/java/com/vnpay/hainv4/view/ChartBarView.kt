package com.vnpay.hainv4.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.vnpay.hainv4.model.Growth
class ChartBarView : View {
    private val backgroundPaint = Paint()
    private val eyesPaint = Paint()
    private val linePaint = Paint()
    private val chartPaint = Paint()
    private var leftChart = 0f
    private var rightChart = 0f
    private var topChart = 0f
    private var bottomChart = 0f
    private var sizeHeightChart = 0f
    private var sizeWidthChart = 0f
    private var growths: ArrayList<Growth> = arrayListOf<Growth>()
    private var maxGrowthRate = 0f
    private var space = 0f
    private var maxSize = 0f
    private var valueHeight  = 0f

    constructor(context: Context) : super(context) {
//initInfo()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initInfo()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initInfo()
    }

    private fun initInfo() {
        backgroundPaint.isAntiAlias = true
        backgroundPaint.color = Color.BLUE
        backgroundPaint.style = Paint.Style.FILL

        eyesPaint.style = Paint.Style.STROKE
        eyesPaint.isAntiAlias = true
        eyesPaint.color = Color.RED

        chartPaint.color = Color.BLUE
        chartPaint.isAntiAlias = true
        chartPaint.style = Paint.Style.FILL
        chartPaint.textSize = 20f

        linePaint.color = Color.RED
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = 1f
        linePaint.isAntiAlias = true

        growths = arrayListOf<Growth>(
            Growth(2000, 1000f),
            Growth(2001, 170f),
            Growth(2002, 2380f),
            Growth(2003, 1210f),
            Growth(2004, 370f),
            Growth(2005, 1811f),
            Growth(2006, 182f),
            Growth(2007, 2476f),
            Growth(2008, 5476f),
//            Growth(2009, 476f),
//            Growth(20010, 4796f),
//            Growth(20011, 2476f),
//            Growth(20011, 2476f),
//            Growth(20011, 2476f),
//            Growth(20011, 2476f),

        )

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        maxSize = heightSize * 0.15f
        setMeasuredDimension(widthSize, heightSize)

        maxGrowthRate = growths[0].growthRate
        for (i in 0 until growths.size) {
            if (growths[i].growthRate > maxGrowthRate) {
                maxGrowthRate = growths[i].growthRate
            }
        }
        val s = maxGrowthRate.toInt().toString()

        var c = s[0].digitToInt() + 1
        valueHeight = (c * Math.pow(10.0,s.length-1.0)).toFloat()


    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        leftChart = width * 0.1f
        topChart = height * 0.9f
        rightChart = width * 0.9f
        bottomChart = height * 0.1f

        val sizeWidth = rightChart - leftChart
        val sizeHeight = topChart - bottomChart
        val addHeight = (valueHeight - maxGrowthRate)/maxGrowthRate * sizeHeight


        sizeWidthChart = (sizeWidth / growths.size) * 0.75f
        space = (sizeWidth / growths.size) * 0.25f
        val spaceLineHeight = (sizeHeight+addHeight) / 5
        val spaceLineWidth = sizeWidth / (growths.size / 2)
        val average = valueHeight / 5


        canvas?.drawRect(leftChart, topChart, rightChart, bottomChart - addHeight, eyesPaint)


        for (i in 0 until growths.size) {
//            if (i == 0) {
//                canvas?.drawRect(
//                    space * 2 + sizeWidthChart,
//                    topChart,
//                    sizeWidthChart + sizeWidthChart + space ,
//                    topChart - (growths[0].growthRate / maxGrowthRate) * sizeHeight,
//                    chartP9aint
//                )
//
//            }
//            else
            canvas?.drawRect(
                space * (i + 1) + sizeWidthChart * i + space + sizeWidthChart,
                topChart,
                sizeWidthChart * i + sizeWidthChart + space * (i + 1) + sizeWidthChart,
                topChart - (growths[i].growthRate / maxGrowthRate) * sizeHeight,
                chartPaint
            )
            canvas?.drawText(growths[i].growthRate.toString(),space * (i + 1) + sizeWidthChart * i + space + sizeWidthChart - (sizeWidthChart/4),topChart - (growths[i].growthRate / maxGrowthRate) * sizeHeight - 5f,chartPaint)
        }

        //draw y
        for (i in 0..5) {
            canvas?.drawLine(
                leftChart,
                bottomChart + spaceLineHeight * i - addHeight,
                rightChart,
                bottomChart + spaceLineHeight * i - addHeight,
                linePaint
            )
        }

        //draw x
        for (i in 0..spaceLineWidth.toInt()) {
            canvas?.drawLine(
                leftChart + spaceLineWidth * i,
                bottomChart - addHeight,
                leftChart + spaceLineWidth * i,
                topChart,
                linePaint
            )
        }

        for (i in 0 until growths.size) {
            canvas?.drawText(
                growths[i].year.toString(),
                space * (i + 1) + sizeWidthChart * i + space + sizeWidthChart - 5f,
                topChart + 25f,
                chartPaint
            )
        }
        for (i in 0 until 5) {
            canvas?.drawText(
                (average * (i + 1)).toString(),
                leftChart - 60f,
                topChart - spaceLineHeight * (i + 1),
                chartPaint
            )
        }


    }

}

