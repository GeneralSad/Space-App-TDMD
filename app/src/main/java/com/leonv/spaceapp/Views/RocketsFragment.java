package com.leonv.spaceapp.Views;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.R;
import com.leonv.spaceapp.Viewmodels.RocketsViewModel;

import java.util.List;

public class RocketsFragment extends Fragment implements RocketsViewModel.RocketsListener {

    private static final String LOGTAG = RocketsFragment.class.getName();

    private RocketsViewModel rocketsViewModel;
    private RocketsRecyclerViewAdapter rocketsRecyclerViewAdapter;

    public static RocketsFragment newInstance() {
        return new RocketsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.rocketsViewModel = new ViewModelProvider(requireActivity()).get(RocketsViewModel.class);
        this.rocketsViewModel.addRocketsListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upcoming_fragment, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            Context context = view.getContext();

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            this.rocketsRecyclerViewAdapter = new RocketsRecyclerViewAdapter(rocketsViewModel.getRockets(), rocketsViewModel);
            recyclerView.setAdapter(rocketsRecyclerViewAdapter);
        }

        return view;
    }

    @Override
    public void onRocketsAvailable(List<Rocket> rocketList) {
        rocketsRecyclerViewAdapter.updateRockets(rocketList);
    }
}