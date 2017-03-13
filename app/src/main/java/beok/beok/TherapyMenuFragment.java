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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_therapy_menu, container, false);

        Button buttonModule1 = (Button) v.findViewById(R.id.open_therapy_module_1);
        Button buttonModule2 = (Button) v.findViewById(R.id.open_therapy_module_2);
        Button buttonModule3 = (Button) v.findViewById(R.id.open_therapy_module_3);

        buttonModule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), Atividades.class);
                Bundle bundle = new Bundle();
                bundle.putInt("atividade", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        buttonModule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), Atividades.class);
                Bundle bundle = new Bundle();
                bundle.putInt("atividade", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        buttonModule3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), Atividades.class);
                Bundle bundle = new Bundle();
                bundle.putInt("atividade", 3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        return v;
    }

}