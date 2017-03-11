package beok.beok;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import static beok.beok.R.id.center;



public class Atividade2 extends AppCompatActivity {

    static int pontos = 0;
    static int contador = 0;
    static int indice = 0;
    static String mito;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    public void botaoMito(View v){
        mito = "MITO!";
        contador++;
        mViewPager.setCurrentItem(contador);
    }

    public void botaoVerdade(View v){
        mito = "VERDADE!";
        contador++;
        mViewPager.setCurrentItem(contador);

    }

    public void botaoOk(View v){
        contador++;
        mViewPager.setCurrentItem(contador);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            //Cria as cada uma das abas (cards)
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Resources res = getResources();
            String[] perguntas = res.getStringArray(R.array.perguntas);
            String[] respostas = res.getStringArray(R.array.resposta);
            String[] explicacao = res.getStringArray(R.array.explicacao);
            TypedArray imagens = getResources().obtainTypedArray(R.array.imagens);
            imagens.getResourceId(indice, -1);


            View rootView;

             if (contador == perguntas.length*2-1) {
                rootView = inflater.inflate(R.layout.fragment_atividade2_resposta, container, false);

                TextView txtpergunta2 = (TextView) rootView.findViewById(R.id.txtpergunta2);
                String text = String.format(res.getString(R.string.escore));
                CharSequence styledText = Html.fromHtml(text);
                txtpergunta2.setText(styledText);

                Button btresposta = (Button) rootView.findViewById(R.id.btresposta);

                String text2 = String.format(res.getString(R.string.pontos), pontos);
                CharSequence styledText2 = Html.fromHtml(text2);
                btresposta.setText(styledText2);
                 btresposta.setTextSize(40);
                 btresposta.setBackgroundColor(Color.parseColor("#00000000"));
                 btresposta.setTextColor(Color.parseColor("#8cc63f"));

                TextView txtexplicacao = (TextView) rootView.findViewById(R.id.txtexplicacao);
                txtexplicacao.setText(res.getString(R.string.parabens));
                 txtexplicacao.setGravity(center);
            }

            else if (getArguments().getInt(ARG_SECTION_NUMBER) % 2 != 0) {
                //Tabs de numero impar sao perguntas
                rootView = inflater.inflate(R.layout.fragment_atividade2_pergunta, container, false);

                TextView txtpergunta1 = (TextView) rootView.findViewById(R.id.txtpergunta1);
                String alala = perguntas[indice];
                String text = String.format(res.getString(R.string.pergunta), indice+1, alala);
                CharSequence styledText = Html.fromHtml(text);
                txtpergunta1.setText(styledText);

                ImageView imagem = (ImageView) rootView.findViewById(R.id.imagem_mito);
                if(indice != 5) {
                    imagem.setImageResource(imagens.getResourceId(indice, -1));
                }
                else {
                    imagem.setVisibility(View.GONE);
                }

                imagens.recycle();
            }
            else {
                rootView = inflater.inflate(R.layout.fragment_atividade2_resposta, container, false);

                TextView txtpergunta2 = (TextView) rootView.findViewById(R.id.txtpergunta2);
                String alala = perguntas[indice];
                String text = String.format(res.getString(R.string.pergunta), indice+1, alala);
                CharSequence styledText = Html.fromHtml(text);
                txtpergunta2.setText(styledText);

                TextView txtexplicacao = (TextView) rootView.findViewById(R.id.txtexplicacao);
                txtexplicacao.setText(explicacao[indice]);

                Button btresposta = (Button) rootView.findViewById(R.id.btresposta);
                if (mito == "MITO!") {
                    btresposta.setBackgroundColor(Color.parseColor("#8cc63f"));
                    pontos++;
                }
                else if (mito == "VERDADE!"){
                    btresposta.setBackgroundColor(Color.RED);
                }

                btresposta.setText(respostas[indice]);

                indice++;
            }

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 17;
        }
    }
    @Override
    public void onBackPressed() {

    }
}