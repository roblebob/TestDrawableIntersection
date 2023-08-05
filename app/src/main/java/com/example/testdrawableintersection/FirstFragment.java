package com.example.testdrawableintersection;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.graphics.PathParser;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.example.testdrawableintersection.databinding.FragmentFirstBinding;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstFragment extends Fragment {
    public static final String TAG = "FirstFragment";
    public static final int L = 2540;

    private FragmentFirstBinding binding;

    private List<Drawable> mDrawables = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        VectorDrawableCompat vectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.test_further_cycles, null);
        binding.imageview.setImageDrawable(vectorDrawable);


        binding.spiralclock.setup();


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Instant instantRoot = Instant.now();
                instantRoot = instantRoot.atZone(ZoneOffset.of("+02:00")).withHour(6).withMinute(0).withSecond(0).withNano(0).toInstant();
                Instant instant = Instant.now();
                Log.e(TAG, "" + LocalTime.ofInstant( instant, ZoneId.systemDefault()) + "            " + LocalTime.ofInstant( instantRoot, ZoneId.systemDefault()));
            }
        });

   /*

        mDrawables.add( getShapePath() );
        mDrawables.add( getHighlights() );









        Path pathArm = new Path();
        pathArm.moveTo(1271, 1271);
        pathArm.lineTo(0, 0);
        pathArm.close();



*/


/*

        Path pathIntersection = new Path();
        pathIntersection.op(pathOuter, pathArm, Path.Op.INTERSECT);



        Log.e(TAG, String.valueOf( pathIntersection.isEmpty()));


        ShapeDrawable shapeOuter = new ShapeDrawable( new PathShape(pathOuter, L, L));
        ShapeDrawable shapeArm = new ShapeDrawable( new PathShape(pathArm, L, L));
        ShapeDrawable shapeIntersection = new ShapeDrawable( new PathShape(pathIntersection, L, L));


        shapeOuter.getPaint().setColor(Color.BLUE);
        shapeArm.getPaint().setColor(Color.YELLOW);
        shapeIntersection.getPaint().setColor(Color.RED);


        ShapeDrawable shapePath = getShapePath();

*/





/*


        LayerDrawable layerDrawable = new LayerDrawable( mDrawables.toArray(new Drawable[0]));
        binding.spiralclock.setBackground( layerDrawable );

*/






    }





    public ShapeDrawable getShapePath() {
        // create a path basic path
        Path pathOuter = PathParser.createPathFromPathData(getString(R.string.path_outer));
        Path pathInner = PathParser.createPathFromPathData(getString(R.string.path_inner));
        Path path = new Path();
        path.addPath(pathOuter);
        path.addPath(pathInner);
        path.setFillType(Path.FillType.EVEN_ODD);
        ShapeDrawable shapePath = new ShapeDrawable( new PathShape(path, L, L));
        shapePath.getPaint().setColor(Color.LTGRAY);
        shapePath.setAlpha((int) (0.649 * 255));
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
        shapeHigh.getPaint().setColor(Color.LTGRAY);
        shapeHigh.setAlpha((int) (0.649 * 255));
        shapeHigh.getPaint().setStyle(Paint.Style.FILL);
        shapeHigh.getPaint().setStrokeWidth(12.0f);
        shapeHigh.getPaint().setStrokeCap(Paint.Cap.ROUND);
        shapeHigh.getPaint().setStrokeJoin(Paint.Join.ROUND);
        return shapeHigh;
    }







    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


//  PathMeasure pathMeasure = new PathMeasure(path, false);