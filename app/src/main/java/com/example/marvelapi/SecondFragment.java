package com.example.marvelapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelapi.databinding.FragmentSecondBinding;
import com.example.marvelapi.models.comics.ComicsResult;
import com.example.marvelapi.viewModels.ComicsViewModel;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private RecyclerView recyclerView;
    private ComicsViewModel viewModel;
    private ComicsListAdapter comicsListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ComicsResult> comicsList = new ArrayList<>();
    private Integer id;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ComicsViewModel.class);


        getParentFragmentManager().setFragmentResultListener("getCharId", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                recyclerView = binding.getRoot().findViewById(R.id.comicsRecyclerView);

                comicsListAdapter = new ComicsListAdapter(comicsList);
                linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(comicsListAdapter);

                id = result.getInt("charId");

                viewModel.getComics(id).observe(getViewLifecycleOwner(), h ->{
                    comicsList.addAll(h);
                    recyclerView.getAdapter().notifyDataSetChanged();
                });

                Log.d("APIE", id.toString());


            }
        });

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.clearList();
        comicsList.clear();
        recyclerView.getAdapter().notifyDataSetChanged();

        Log.d("APIE", "Destroyed!");
        binding = null;
    }




}