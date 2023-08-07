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

import java.time.Duration;
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
        binding.spiralclock.refresh();


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Instant start = Instant.now().atZone(ZoneOffset.of("+02:00")).withHour(11).withMinute(30).withSecond(0).withNano(0).toInstant();
                Instant end = Instant.now().atZone(ZoneOffset.of("+02:00")).withHour(15).withMinute(30).withSecond(0).withNano(0).toInstant();
                binding.spiralclock.submit(start, end);
            }
        });



    }













    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


//  PathMeasure pathMeasure = new PathMeasure(path, false);