package com.example.testdrawableintersection;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralClock extends View {
    public static final String TAG = SpiralClock.class.getSimpleName();
    public static final int L = 2540;


    public SpiralClock(Context context) {
        super(context);
    }

    public SpiralClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpiralClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SpiralClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public List<String> mPathsOuterHoursList = new ArrayList<>();
    public List<String> mPathsInnerHoursList = new ArrayList<>();
    public List<String> mPathsOuterCyclesList = new ArrayList<>();
    public List<String> mPathsInnerCyclesList = new ArrayList<>();

    public Path mPathOuterHours = new Path();
    public Path mPathInnerHours = new Path();
    public Path mPathOuterCycles = new Path();
    public Path mPathInnerCycles = new Path();

    public Path mPathHours = new Path();
    public Path mPathCycles = new Path();

    public List<ShapeDrawable> mShapeDrawablesList = new ArrayList<>();
    public LayerDrawable mLayerDrawable;



    public void init() {
        mPathsOuterHoursList .addAll( Arrays.asList( getResources().getStringArray(R.array.outer_path_hours)));
        mPathsInnerHoursList .addAll( Arrays.asList( getResources().getStringArray(R.array.inner_path_hours)));
        mPathsOuterCyclesList.addAll( Arrays.asList( getResources().getStringArray(R.array.outer_path_cycles)));
        mPathsInnerCyclesList.addAll( Arrays.asList( getResources().getStringArray(R.array.inner_path_cycles)));


        mPathsOuterHoursList .add(0, "M");  mPathsOuterHoursList .add(2, "L");
        mPathsInnerHoursList .add(0, "M");  mPathsInnerHoursList .add(2, "L");
        mPathsOuterCyclesList.add(0, "M");  mPathsOuterCyclesList.add(2, "L");
        mPathsInnerCyclesList.add(0, "M");  mPathsInnerCyclesList.add(2, "L");


        mPathOuterHours .addPath( PathParser.createPathFromPathData( UtilKt.list2String(mPathsOuterHoursList)));
        mPathInnerHours .addPath( PathParser.createPathFromPathData( UtilKt.list2String(mPathsInnerHoursList)));
        mPathOuterCycles.addPath( PathParser.createPathFromPathData( UtilKt.list2String(mPathsOuterCyclesList)));
        mPathInnerCycles.addPath( PathParser.createPathFromPathData( UtilKt.list2String(mPathsInnerCyclesList)));


        mPathHours.addPath(mPathOuterHours);
        mPathHours.addPath(mPathInnerHours);
        mPathHours.setFillType(Path.FillType.EVEN_ODD);

        mPathCycles.addPath(mPathOuterCycles);
        mPathCycles.addPath(mPathInnerCycles);
        mPathCycles.setFillType(Path.FillType.EVEN_ODD);
    }






    public void setup() {
        init();


        mShapeDrawablesList.add(getShapePath());
        //mShapeDrawablesList.add(getHighlights());
    }


    public void submit(int hour) {
        if (hour < 0) {
            hour = 24 + hour + 1;
        }
        Log.e(TAG, "submit: " + hour);
        Path path = new Path();
        String sOuter = "M " + mPathsOuterHoursList.get(hour) + " L " + mPathsOuterHoursList.get(hour + 1);
        String sInner = "M " + mPathsInnerHoursList.get(hour) + " L " + mPathsInnerHoursList.get(hour + 1);



        Log.e(TAG, "\n" + sOuter + "\n" + sInner);
        path.addPath( PathParser.createPathFromPathData(sOuter));
        path.addPath( PathParser.createPathFromPathData(sInner));
        path.addPath( PathParser.createPathFromPathData("M " + mPathsOuterHoursList.get(hour) + " L " + mPathsInnerHoursList.get(hour)));
        path.addPath( PathParser.createPathFromPathData("M " + mPathsOuterHoursList.get(hour+ 1) + " L " + mPathsInnerHoursList.get(hour + 1)));

        path.setFillType(Path.FillType.EVEN_ODD);

        ShapeDrawable shapePath = new ShapeDrawable( new PathShape(path, L, L));
        shapePath.getPaint().setColor(Color.RED);
        shapePath.setAlpha((int) (1.0 * 255));
        shapePath.getPaint().setStyle(Paint.Style.STROKE);
        shapePath.getPaint().setStrokeWidth(12.0f);
        shapePath.getPaint().setStrokeCap(Paint.Cap.ROUND);
        shapePath.getPaint().setStrokeJoin(Paint.Join.ROUND);

        mShapeDrawablesList.add(shapePath);
        refresh();
    }


    public void refresh() {
        mLayerDrawable = new LayerDrawable( mShapeDrawablesList.toArray(new ShapeDrawable[0]));
        setBackground( mLayerDrawable );
    }



    public ShapeDrawable getShapePath() {
        // create a path basic path

        ShapeDrawable shapePath = new ShapeDrawable( new PathShape(mPathHours, L, L));
        shapePath.getPaint().setColor(Color.WHITE);
        shapePath.setAlpha((int) (0.1 * 255));
        shapePath.getPaint().setStyle(Paint.Style.STROKE);
        shapePath.getPaint().setStrokeWidth(12.0f);
        shapePath.getPaint().setStrokeCap(Paint.Cap.ROUND);
        shapePath.getPaint().setStrokeJoin(Paint.Join.ROUND);
        return shapePath;
    }










    public ShapeDrawable getHighlights() {
        Path high = new Path();
        List<String> highlights = Arrays.asList( getResources().getStringArray(R.array.cells));
        highlights.forEach( cell -> {
            Path pathCell = PathParser.createPathFromPathData(cell);
            high.addPath(pathCell);
        });
        high.setFillType(Path.FillType.EVEN_ODD);
        ShapeDrawable shapeHigh = new ShapeDrawable( new PathShape(high, L, L));
        shapeHigh.getPaint().setColor(Color.YELLOW);
        shapeHigh.setAlpha((int) (0.1 * 255));
        shapeHigh.getPaint().setStyle(Paint.Style.FILL);
        shapeHigh.getPaint().setStrokeWidth(12.0f);
        shapeHigh.getPaint().setStrokeCap(Paint.Cap.ROUND);
        shapeHigh.getPaint().setStrokeJoin(Paint.Join.ROUND);
        return shapeHigh;
    }

}
