package com.hfad.bitsandpizzas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //получаем ссылку на панель приложения
        ActionBar actionBar = getSupportActionBar();
        //включаем кнопку Вверх
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //метод вызывается, когда пользователь щелкает на FAB-кнопке
    public void onClickDone(View view){
        CharSequence text = "Your order has been updated";
        int duration = Snackbar.LENGTH_SHORT;

        //создаем snackbar
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);

        //методу передается текст, который должен выводится на экране для действия, и объект View.OnClickListener
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            //при выборе действия отмены выполняется код:
            public void onClick(View v) {
                //если пользователь щелкнет на действии Snackbar, будет выведено уведомление Toast
                Toast toast = Toast.makeText(OrderActivity.this, "Undone!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        snackbar.show();
    }
}