package com.hfad.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PizzaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_pizza, container, false);

        String[] pizzaNames = new String[Pizza.pizzas.length];
        for (int i = 0; i < pizzaNames.length; i++){
            pizzaNames[i] = Pizza.pizzas[i].getName(); //названия видов пиццы добавляются в массив строк
        }

        int[] pizzaImages = new int[Pizza.pizzas.length];
        for (int i = 0; i < pizzaImages.length; i++){
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId(); //изображения добавляются в массив с элементами int
        }

        //массивы передаются адаптеру
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);

        //отображение карточек в виде таблицы из двух столбцов (вместо getActivity() - this, если код в активности, те RecyclerView не во фрагменте)
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            //реализация метода onClick() интерфейса Listener запускает активность PizzaDetailActivity,
            //передавая ей идентификатор пиццы, выбранной пользователем
            public void onClick(int position) {
                Intent intent = new Intent (getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return pizzaRecycler;
    }
}