package com.hfad.bitsandpizzas;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

//компонент ViewHolder(внутренний класс) указывает, какие преставления должны использоваться для каждого элемента данных
class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    //переменные для хранения данных пиццы
    private String[] captions; //название
    private int[] imageIds; //идентификатор графического ресурса
    private Listener listener;

    interface Listener{
        void onClick(int position);
    }

    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    //каждый объект ViewHolder содержит CardView, определяет представления, содержит дополнительную информацию
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        //в компоненте RecyclerView отображаются карточки
        //ViewHolder содержит представления CardView
        //если нужно в RecyclerView отображать данные другого типа, нужно определить их здесь

        //каждый объект ViewHolder отображает CardView
        public ViewHolder(CardView v) {
            super(v); //суперкласс включает метаданные, необходимые для правильной работы адаптера
            cardView = v;
        }
    }

    //количество элементов данных
    @Override
    public int getItemCount() {
        return captions.length; //длина массива captions равна количеству элементов данных в RecyclerView
    }

    //активности и фрагменты используют этот метод для регистрации себя в качестве слушателя
    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    //отображение разных представлений для разных вариантов в списке
    //метод вызывается когда RecyclerView потребуется создать ViewHolder
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    //добавление данных в карточки, метод вызывается когда компоненту RecyclerView нужно вывести данные в ViewHolder
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        Drawable drawable =
                ContextCompat.getDrawable(cardView.getContext(), imageIds[position]); //изображение выводится в графическом представление ImageView
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]); //название выводится в компоненте TextView

        //интерфейс добавляется к CardView
        cardView.setOnClickListener(new View.OnClickListener(){
            //при щелчке на CardView вызвать метод onClick() нитерфейса Listener
            public void onClick(View v){
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }
    //создан макет карточного представления для вывода фотографий и подписей с адаптером, который создает карточки и заполняет их данными
    //компонент RecyclerView будет заполнять карточки изображениями и текстом, после отображает на экране
}


