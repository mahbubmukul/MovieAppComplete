package com.mukul.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mukul.myapplication.DatabaseHeleper;
import com.mukul.myapplication.R;
import com.mukul.myapplication.databinding.FragmentDashboardBinding;
import com.mukul.myapplication.model.MovieTableData;
import com.mukul.myapplication.ui.MyAdapter;
import com.mukul.myapplication.ui.MyDatabaseAdapter;
import com.mukul.myapplication.ui.home.MovieFragment;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        DatabaseHeleper databaseHeleper = new DatabaseHeleper(getContext());

        List<MovieTableData> mr = databaseHeleper.getAllNotes();
        binding.movieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.movieList.setAdapter(new MyDatabaseAdapter(getContext(), mr));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}