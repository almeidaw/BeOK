package beok.beok;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


public class Main extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    Home home_fragment;
    TestFragment tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_fragment = new Home();
        tf = new TestFragment();

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.diaryItem:
                                Toast.makeText(Main.this, "diário", Toast.LENGTH_SHORT).show();
                                Main.this.colocaFragment(tf, R.id.main_fragment_container);
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


}