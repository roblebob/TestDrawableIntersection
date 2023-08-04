package com.example.testdrawableintersection;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.VectorDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.example.testdrawableintersection.databinding.FragmentFirstBinding;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public class FirstFragment extends Fragment {
    public static final String TAG = "FirstFragment";
    public static final int L = 2540;

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        VectorDrawableCompat vectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.test, null);
        binding.imageview.setImageDrawable(vectorDrawable);



        Path pathOuter = PathParser.createPathFromPathData(getString(R.string.path_outer));
        Path pathInner = PathParser.createPathFromPathData(getString(R.string.path_inner));
        pathOuter.close();

        Path path = new Path();
        path.addPath(pathOuter);
        path.addPath(pathInner);



        Path pathArm = new Path();
        pathArm.moveTo(1271, 1271);
        pathArm.lineTo(0, 0);
        pathArm.close();


        Paint paint = new Paint();
        paint.setStrokeWidth(2f);
        paint.setStyle(Paint.Style.STROKE);
        paint.getFillPath(pathOuter, pathArm);
        paint.getFillPath(pathArm, pathOuter);





        Path pathIntersection = new Path();
        pathIntersection.op(pathOuter, pathArm, Path.Op.INTERSECT);



        Log.e(TAG, String.valueOf( pathIntersection.isEmpty()));


        ShapeDrawable shapeOuter = new ShapeDrawable( new PathShape(pathOuter, L, L));
        ShapeDrawable shapeArm = new ShapeDrawable( new PathShape(pathArm, L, L));
        ShapeDrawable shapeIntersection = new ShapeDrawable( new PathShape(pathIntersection, L, L));


        shapeOuter.getPaint().setColor(Color.BLUE);

        shapeArm.getPaint().setColor(Color.YELLOW);


        shapeIntersection.getPaint().setColor(Color.RED);


        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
                shapeOuter,
                shapeArm
                //, shapeIntersection
        });








/*

        path.setFillType(Path.FillType.EVEN_ODD);

        ShapeDrawable shapeDrawable = new ShapeDrawable( new PathShape(path, L, L));
        shapeDrawable.setAlpha((int) (0.649 * 255));
        shapeDrawable.getPaint().setColor(Color.parseColor("#729FCF"));
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.getPaint().setStrokeWidth(12.0f);
        shapeDrawable.getPaint().setStrokeCap(Paint.Cap.ROUND);
        shapeDrawable.getPaint().setStrokeJoin(Paint.Join.ROUND);



        ShapeDrawable shapeDrawableFill = new ShapeDrawable( new PathShape(path, L, L));
        shapeDrawableFill.setAlpha((int) (0.649 * 255));
        shapeDrawableFill.getPaint().setColor(Color.parseColor("#FFFFFF"));
        shapeDrawableFill.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        shapeDrawableFill.getPaint().setStrokeWidth(12.0f);
        shapeDrawableFill.getPaint().setStrokeCap(Paint.Cap.ROUND);
        shapeDrawableFill.getPaint().setStrokeJoin(Paint.Join.ROUND);




        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawableFill, shapeDrawable});

*/



        binding.view.setBackground( layerDrawable );

        PathMeasure pathMeasure = new PathMeasure(path, false);





    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}