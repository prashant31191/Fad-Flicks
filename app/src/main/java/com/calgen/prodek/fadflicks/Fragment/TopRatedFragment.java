package com.calgen.prodek.fadflicks.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.calgen.prodek.fadflicks.Adapter.MovieAdapter;
import com.calgen.prodek.fadflicks.R;
import com.calgen.prodek.fadflicks.Utility.Network;
import com.calgen.prodek.fadflicks.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

/**
 * Created by Gurupad on 29-Aug-16.
 * You asked me to change it for no reason.
 */
public class TopRatedFragment extends Fragment {

    private static final String TAG = PopularFragment.class.getSimpleName();
    private static final int MIN_VOTE_COUNT = 1000;
    //@formatter:off
    @BindView(R.id.recycler_view) public RecyclerView recyclerView;
    @State public String sort_type;
    @State public ArrayList<Movie> movieList;
    //@formatter:on
    private View rootView;
    private MovieAdapter adapter;

    public TopRatedFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        if (savedInstanceState == null)
            sort_type = getString(R.string.sort_type_top_rated);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);

        ButterKnife.bind(this, rootView);

        if (savedInstanceState == null) {
            movieList = new ArrayList<>();
        }

        adapter = new MovieAdapter(getActivity(), movieList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) updateMovieData();
    }

    private void updateMovieData() {
        //Check for network connection beforehand
        if (Network.isConnected(getContext())) {
            fetchData();
        } else {
            //Also display snackBar to get latest content
//            final Snackbar snackbar = Snackbar.make(),
//                    getString(R.string.internet_error_message),
//                    Snackbar.LENGTH_INDEFINITE);
//            snackbar.setAction(R.string.try_again, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    updateMovieData();
//                }
//            }).show();
        }
    }

    private void fetchData() {
    }
}