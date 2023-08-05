package com.example.testdrawableintersection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralClock extends View {

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


    private List<Drawable> mDrawables = new ArrayList<>();
    private LayerDrawable mLayerDrawable;

    public void setup() {
        mLayerDrawable = new LayerDrawable( new Drawable[] {getShapePath()});
        mLayerDrawable.addLayer(getRootPath());
//        mDrawables.add(getShapePath());
//        mDrawables.add(getHighlights());
//        LayerDrawable layerDrawable = new LayerDrawable( mDrawables.toArray(new Drawable[0]));
//

        setBackground( mLayerDrawable );
    }

    public ShapeDrawable getShapePath() {
        // create a path basic path
        Path pathOuter = PathParser.createPathFromPathData(getContext().getString(R.string.path_outer));
        Path pathInner = PathParser.createPathFromPathData(getContext().getString(R.string.path_inner));
        Path path = new Path();
        path.addPath(pathOuter);
        path.addPath(pathInner);
        path.setFillType(Path.FillType.EVEN_ODD);
        ShapeDrawable shapePath = new ShapeDrawable( new PathShape(path, L, L));
        shapePath.getPaint().setColor(Color.WHITE);
        shapePath.setAlpha((int) (0.1 * 255));
        shapePath.getPaint().setStyle(Paint.Style.FILL);
        shapePath.getPaint().setStrokeWidth(12.0f);
        shapePath.getPaint().setStrokeCap(Paint.Cap.ROUND);
        shapePath.getPaint().setStrokeJoin(Paint.Join.ROUND);
        return shapePath;
    }



    public ShapeDrawable getRootPath() {

        Path path = new Path();


        Arrays.asList( getResources().getStringArray(R.array.outer_path_hours))
                .forEach( pathString -> {
                    Path pathOuter = PathParser.createPathFromPathData(pathString);
                    path.addPath(pathOuter);
                });

        Arrays.asList( getResources().getStringArray(R.array.inner_path_hours))
                .forEach( pathString -> {
                    Path pathInner = PathParser.createPathFromPathData(pathString);
                    path.addPath(pathInner);
                });

        path.setFillType(Path.FillType.EVEN_ODD);
        ShapeDrawable shapePath = new ShapeDrawable( new PathShape(path, L, L));
        shapePath.getPaint().setColor(Color.WHITE);
        shapePath.setAlpha((int) (0.1 * 255));
        shapePath.getPaint().setStyle(Paint.Style.FILL);
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
