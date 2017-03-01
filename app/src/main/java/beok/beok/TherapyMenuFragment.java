package beok.beok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class TherapyMenuFragment extends Fragment {

    //Button buttonModule1, buttonModule2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_therapy_menu, container, false);

        Button buttonModule2 = (Button) v.findViewById(R.id.open_therapy_module_2);
        buttonModule2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "teste", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent (getActivity(), Atividade2.class);
                        startActivity(intent);
                    }
                }
        );

        return v;


    }
}