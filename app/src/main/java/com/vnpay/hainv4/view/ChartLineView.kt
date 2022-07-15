package com.vnpay.hainv4.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import com.vnpay.hainv4.model.Growth

class ChartLineView : View {
    private val linePaint = Paint()
    private val pointPaint = Paint()
    private val textPain = Paint()
    private val linePointPaint = Paint()
    private var radius = 5f
    private val point = PointF()
    private var growths: ArrayList<Growth> = arrayListOf<Growth>()
    private var sizeX = 0f
    private var sizeY = 0f
    private var maxGrowthRate = 0f
    private var valueY = 0f
    private var spacePaddingX = 0f
    private var spacePaddingY = 0f
    private var paddingPoint = 0f
    private var addY = 0f


    constructor(context: Context) : super(context) {

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
        linePaint.isAntiAlias = true
        linePaint.style = Paint.Style.STROKE
        linePaint.color = Color.BLUE
        linePaint.strokeWidth = 1f

        pointPaint.isAntiAlias = true
        pointPaint.style = Paint.Style.FILL
        pointPaint.color = Color.RED

        textPain.color = Color.BLACK
        textPain.textSize = 20f
        textPain.style = Paint.Style.FILL
        textPain.isAntiAlias = true

        linePointPaint.isAntiAlias = true
        linePointPaint.style = Paint.Style.STROKE
        linePointPaint.color = Color.RED
        linePointPaint.strokeWidth = 1f




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
            Growth(2008, 3476f),
            Growth(2008, 1476f),
            Growth(2008, 2476f),
            Growth(2007, 2476f),
            Growth(2008, 5476f),
            Growth(2008, 3476f),
            Growth(2008, 1476f),
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        sizeX = widthSize * 0.8f
        sizeY = heightSize * 0.8f
        spacePaddingX = widthSize * 0.1f
        spacePaddingY = heightSize * 0.1f
        paddingPoint = sizeX / growths.size

        maxGrowthRate = growths[0].growthRate
        for (i in 0 until growths.size) {
            if (growths[i].growthRate > maxGrowthRate) {
                maxGrowthRate = growths[i].growthRate
            }
        }
        val s = maxGrowthRate.toInt().toString()
        var c = s[0].digitToInt() + 1
        valueY = (c * Math.pow(10.0, s.length - 1.0)).toFloat()

        addY = (valueY - maxGrowthRate) / maxGrowthRate * sizeY


    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
        //drawY
        canvas?.drawLine(
            spacePaddingX,
            spacePaddingY - addY,
            spacePaddingX,
            sizeY + spacePaddingY,
            linePaint
        )
        //drawX
        canvas?.drawLine(
            spacePaddingX,
            sizeY + spacePaddingY,
            spacePaddingX + sizeX + spacePaddingX,
            sizeY + spacePaddingY,
            linePaint
        )

        //draw point
        for (i in 0 until growths.size) {
            canvas?.drawCircle(
                spacePaddingX + paddingPoint * (i + 1),
                sizeY + spacePaddingY - (growths[i].growthRate / maxGrowthRate) * sizeY,
                radius,
                pointPaint
            )
            if (i == growths.size - 1) {
                canvas?.drawText(
                    growths[i].growthRate.toInt().toString(),
                    spacePaddingX + paddingPoint * (i + 1) - 15f,
                    sizeY + spacePaddingY - (growths[i].growthRate / maxGrowthRate) * sizeY - 8f,
                    textPain
                )
            } else if (i < growths.size - 1) {
                if (growths[i].growthRate > growths[i + 1].growthRate) {
                    canvas?.drawText(
                        growths[i].growthRate.toInt().toString(),
                        spacePaddingX + paddingPoint * (i + 1) - 15f,
                        sizeY + spacePaddingY - (growths[i].growthRate / maxGrowthRate) * sizeY - 8f,
                        textPain
                    )
                }else  canvas?.drawText(
                    growths[i].growthRate.toInt().toString(),
                    spacePaddingX + paddingPoint * (i + 1) - 15f,
                    sizeY + spacePaddingY - (growths[i].growthRate / maxGrowthRate) * sizeY + 22f,
                    textPain
                )
            }


        }
        //draw line connect point
        canvas?.drawLine(
            spacePaddingX,
            spacePaddingY + sizeY,
            spacePaddingX + paddingPoint,
            sizeY + spacePaddingY - (growths[0].growthRate / maxGrowthRate) * sizeY,
            linePointPaint
        )
        for (i in 0 until growths.size - 1) {
            canvas?.drawLine(
                spacePaddingX + paddingPoint * (i + 1),
                sizeY + spacePaddingY - (growths[i].growthRate / maxGrowthRate) * sizeY,
                spacePaddingX + paddingPoint * (i + 2),
                sizeY + spacePaddingY - (growths[i + 1].growthRate / maxGrowthRate) * sizeY,
                linePointPaint
            )
        }



        for (i in 0 until 5) {
            //draw line in y
            canvas?.drawLine(
                spacePaddingX - spacePaddingX / 10,
                (sizeY + spacePaddingY) - (sizeY + addY) / 5 * (i + 1),
                spacePaddingX + spacePaddingX / 10,
                (sizeY + spacePaddingY) - (sizeY + addY) / 5 * (i + 1),
                linePaint
            )
            //draw text growth
            canvas?.drawText(
                (valueY / 5 * (i + 1)).toInt().toString(),
                10f,
                (sizeY + spacePaddingY) - (sizeY + addY) / 5 * (i + 1) + 5f,
                textPain
            )
        }



        for (i in 0 until growths.size){
            //draw text year
            canvas?.drawText(
                growths[i].year.toString(),
                spacePaddingX + paddingPoint * (i + 1) - 20f,
                spacePaddingY+sizeY+40f,
                textPain
            )
            //draw line in x
            canvas?.drawLine(
                spacePaddingX + paddingPoint * (i + 1),
                (sizeY + spacePaddingY) - spacePaddingX / 10 ,
                spacePaddingX + paddingPoint * (i + 1),
                (sizeY + spacePaddingY) + spacePaddingX / 10,
                linePaint
            )


        }


    }


}