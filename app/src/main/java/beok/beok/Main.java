package beok.beok;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

import beok.beok.POJO.BotaoAtivo;
import beok.beok.api.DB;


public class Main extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    Home home_fragment;
    TestFragment tf;
    ShowDiary show_diary_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_fragment = new Home();
        show_diary_fragment = new ShowDiary();

        tf = new TestFragment();

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.showDiaryItem:
                                Toast.makeText(Main.this, "diário", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(show_diary_fragment, R.id.main_fragment_container);
                                break;

                            case R.id.goalsItem:
                                Toast.makeText(Main.this, "metas", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                break;

                            case R.id.homeItem:
                                Toast.makeText(Main.this, "principal", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(home_fragment, R.id.main_fragment_container);
                                break;

                            case R.id.inspirationItem:
                                Toast.makeText(Main.this, "inspiração", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                break;

                            case R.id.therapyItem:
                                Toast.makeText(Main.this, "terapia", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
                                break;
                        }
                        return true;
                    }
                });

    }

    private void colocaFragment(Fragment fragment, int container){
        if(fragment.isAdded())
            return;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment);
        //fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void saveClick(View v){
        Date dtn=new Date();
        dtn.setTime(System.currentTimeMillis());
        BotaoAtivo btA=new BotaoAtivo();
        btA.setMotivo(true);
        btA.setOQueFez(5);
        btA.setDataAtivo(dtn);

        DB.save(btA);
    }




}