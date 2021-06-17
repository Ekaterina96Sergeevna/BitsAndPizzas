package com.hfad.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PastaFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView pastaRecycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_pasta, container, false);

        String[] pastaNames = new String[Pasta.pastas.length];
        for(int i = 0; i < pastaNames.length; i++){
            pastaNames[i] = Pasta.pastas[i].getName();
        }

        int[] pastaImage = new int[Pasta.pastas.length];
        for(int i = 0; i < pastaImage.length; i++){
            pastaImage[i] = Pasta.pastas[i].getImageResourceId();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pastaNames, pastaImage);
        pastaRecycler.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pastaRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PastaDetailActivity.class);
                intent.putExtra(PastaDetailActivity.EXTRA_PASTA_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return pastaRecycler;
    }
}