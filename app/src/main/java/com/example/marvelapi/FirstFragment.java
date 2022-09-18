package com.example.marvelapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelapi.databinding.FragmentFirstBinding;
import com.example.marvelapi.models.character.Result;
import com.example.marvelapi.viewModels.CharacterViewModel;

import java.util.ArrayList;

public class FirstFragment extends Fragment implements HeroListAdapter.OnClickId {

    private FragmentFirstBinding binding;
    private ArrayList<Result> heroesList = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d("APIE", "Created!");
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        CharacterViewModel viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);

        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.heroListView);

        HeroListAdapter heroListAdapter = new HeroListAdapter(heroesList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(heroListAdapter);

        viewModel.getHeroes().observe(getViewLifecycleOwner(), h ->{
            heroesList.clear();
            heroesList.addAll(h);
            recyclerView.getAdapter().notifyDataSetChanged();
        });




        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void sendId(Integer id) {
        Bundle result = new Bundle();
        result.putInt("charId", id);
        getParentFragmentManager().setFragmentResult("getCharId", result);

    }
}