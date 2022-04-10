package com.leonv.spaceapp.Popup;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Utils.OnItemClickListener;
import com.leonv.spaceapp.Views.PopupLaunchesRecyclerViewAdapter;
import com.leonv.spaceapp.R;
import com.leonv.spaceapp.databinding.PopupLaunchpadBinding;

import java.util.ArrayList;

public class PopupLaunchpad implements OnItemClickListener {

    private final String title;
    private final ArrayList<Flight> flights;
    private PopupWindow popupWindow;

    public PopupLaunchpad(String title, ArrayList<Flight> flights){
        this.title = title;
        this.flights = flights;
    }

    public void show(LayoutInflater inflater){
        View popupView = inflater.inflate(R.layout.popup_launchpad, null);

        PopupLaunchpadBinding popupLaunchpadBinding = PopupLaunchpadBinding.bind(popupView);
        popupLaunchpadBinding.popupLaunchpadTitle.setText(this.title);
        popupLaunchpadBinding.setPopupManager(this);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        this.popupWindow = new PopupWindow(popupView, width, height, true);

        this.popupWindow.showAtLocation(new View(inflater.getContext()), Gravity.CENTER, 0, 0);

        RecyclerView recyclerView = (RecyclerView)popupView.findViewById(R.id.popup_launchpad_launches);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        TextView emptyView = popupView.findViewById(R.id.popup_launchpad_empty);

        if (this.flights.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

        PopupLaunchesRecyclerViewAdapter adapter = new PopupLaunchesRecyclerViewAdapter(this.flights, this);

        recyclerView.setAdapter(adapter);

        popupView.setOnTouchListener((v, event) -> {
            v.performClick();
            return true;
        });
    }

    public void dismiss(){
        if(popupWindow != null)
        {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    @Override
    public void onItemClick(int clickedPosition) {

    }
}
