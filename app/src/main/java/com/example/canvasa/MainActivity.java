package com.example.canvasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;

    private int mCountNumber;

    private static final int OFFSET = 120;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int mColorBackground;

    private int mColorLightPink;

    private int mColorMediumPink;

    private int mColorDarkPink;

    private int mColorBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorBackground = ResourcesCompat.getColor(getResources(),
                R.color.colorBackground, null);
        mColorLightPink = ResourcesCompat.getColor(getResources(),
                R.color.colorLightPink, null);
        mColorMediumPink = ResourcesCompat.getColor(getResources(),
                R.color.colorMediumPink, null);
        mColorDarkPink = ResourcesCompat.getColor(getResources(),
                R.color.colorDarkPink, null);
        mColorBlack = ResourcesCompat.getColor(getResources(),
                R.color.colorBlack, null);

        mPaintText.setColor(ResourcesCompat.getColor(getResources(),
                R.color.black, null));
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.myimageview);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSomething(view);
            }
        });
    }

    public void drawSomething(View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        Point a = new Point(halfWidth, halfHeight);
        Point b = new Point(halfWidth, halfHeight);
        Point c = new Point(halfWidth, halfHeight);

        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight,
                    Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);
            mCanvas.drawText(getString(R.string.keep_tapping), 100,
                    100, mPaintText);
            mOffset += OFFSET;
        }
        else {
            switch (mCountNumber) {
                case 1:
                    //telinga
                    Path leftEar = new Path();
                    Path rightEar = new Path();

                    leftEar.setFillType(Path.FillType.EVEN_ODD);
                    leftEar.lineTo(a.x-370, a.y-100);
                    leftEar.lineTo(b.x-150, b.y-370);
                    leftEar.lineTo(c.x-400, c.y-400);
                    leftEar.lineTo(a.x-370, a.y-100);
                    leftEar.close();

                    rightEar.setFillType(Path.FillType.EVEN_ODD);
                    rightEar.lineTo(a.x+370, a.y-100);
                    rightEar.lineTo(b.x+150, b.y-370);
                    rightEar.lineTo(c.x+400, c.y-400);
                    rightEar.lineTo(a.x+370, a.y-100);
                    rightEar.close();

                    mPaint.setColor(mColorMediumPink);
                    mCanvas.drawPath(leftEar, mPaint);
                    mCanvas.drawPath(rightEar, mPaint);
                    break;

                case 2:
                    //muka
                    mPaint.setColor(mColorLightPink);
                    mCanvas.drawCircle(halfWidth, halfHeight, halfWidth*3/4, mPaint);
                    break;
                case 3:
                    //mata
                    Point leftEye = new Point(a.x-100, a.y-50);
                    Point rightEye = new Point(b.x+100, b.y-50);
                    mPaint.setColor(mColorBlack);
                    mCanvas.drawCircle(leftEye.x, leftEye.y, 30, mPaint);
                    mCanvas.drawCircle(rightEye.x, rightEye.y, 30, mPaint);
                    break;
                case 4:
                    //hidung
                    mPaint.setColor(mColorMediumPink);
                        mCanvas.drawOval(new RectF(a.x-150, a.x+300, a.x+150, a.x+500), mPaint);
                    break;
                case 5:
                    //lubang hidung
                    Point leftNose = new Point(a.x-50, a.y+140);
                    Point rightNose = new Point(b.x+50, b.y+140);
                    mPaint.setColor(mColorDarkPink);
                    mCanvas.drawCircle(leftNose.x, leftNose.y, 30, mPaint);
                    mCanvas.drawCircle(rightNose.x, rightNose.y, 30, mPaint);
            }

        }

        mCountNumber++;
        view.invalidate();
    }
}