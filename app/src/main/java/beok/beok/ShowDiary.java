package beok.beok;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ShowDiary extends Fragment {

    //DEFINITIONS
    Button button;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_show_diary, container, false);
        button = (Button) v.findViewById(R.id.button_write_diary);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent (getActivity(), Diary1.class);
                        startActivity(intent);
                    }
                }
        );


        return v;
    }

}
