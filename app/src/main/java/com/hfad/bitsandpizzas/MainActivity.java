package com.hfad.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //связывание SectionsPagerAdapter с ViewPager
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter (getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        //связывание ViewPager c TabLayout
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }
        //метод добавляет элементы действий из файла ресурсов меню на панель приложения
        public boolean onCreateOptionsMenu (Menu menu) {
            //заполнение меню, элементы действий добавляются на панель приложения
            getMenuInflater().inflate(R.menu.menu_main, menu);
            MenuItem menuItem = menu.findItem(R.id.action_share);
            shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
            //получить ссылку на провайдера действия передачи информации и присвоить ее приватной переменной
            setShareActionIntent("Want to join me for pizza?");
            return super.onCreateOptionsMenu(menu);
        }

        private void setShareActionIntent(String text) {
            //создаем интент, передаем его провайдеру действия передачи информации при помощи метода setShareIntent
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            shareActionProvider.setShareIntent(intent);
        }

    //метод вызывается при выборе действия на панели приложения
        public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_create_order:
                //код, выполняемый при выборе элемента Create Order (запускается OrderActivity)
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
            }
        }

        private class SectionsPagerAdapter extends FragmentPagerAdapter{

            public SectionsPagerAdapter (FragmentManager fm){
                super(fm);
            }

            @Override
            //сообщает количество страниц, которые содержит ViewPager
            public int getCount() {
                return 4;
            }

            @Override
            //указывает какой фрагмент должен выводится на каждой странице
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new TopFragment();
                    case 1:
                        return new PizzaFragment();
                    case 2:
                        return new PastaFragment();
                    case 3:
                        return new StoresFragment();
                }
                return null;
            }

            public CharSequence getPageTitle (int position) {
                switch (position){
                    case 0:
                        return getResources().getText(R.string.home_tab);
                    case 1:
                        return getResources().getText(R.string.pizza_tab);
                    case 2:
                        return getResources().getText(R.string.pasta_tab);
                    case 3:
                        return getResources().getText(R.string.store_tab);
                }
                return null;
            }
        }
    }
