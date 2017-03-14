package beok.beok;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import static beok.beok.R.id.center;
import static beok.beok.R.id.wrap_content;


public class Atividade2 extends AppCompatActivity {

    static int pontos = 0;
    static int contador = 0; // Itera por cada item do array (para colocar o numero correto nas perguntas)
    static int indice = 0; // Itera por cada aba (Perguntas + respostas + escore final
    final int indiceDroga = 2;
    static String nomeDroga;

    static Resources res;
    static int[] respostas;
    static String[] perguntas, explicacao;
    static TypedArray imagens;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        res = getResources();

        switch (indiceDroga) {
            case 1: {
                respostas = res.getIntArray(R.array.respostas_maconha);
                perguntas = res.getStringArray(R.array.perguntas_maconha);
                explicacao = res.getStringArray(R.array.explicacoes_maconha);
                imagens = getResources().obtainTypedArray(R.array.imagens_maconha);
                //imagens.getResourceId(indice, -1);
                nomeDroga = "a maconha";
                break;
            }
            case 2: {
                respostas = res.getIntArray(R.array.respostas_cocaina);
                perguntas = res.getStringArray(R.array.perguntas_cocaina);
                explicacao = res.getStringArray(R.array.explicacoes_cocaina);
                imagens = getResources().obtainTypedArray(R.array.imagens_cocaina);
                nomeDroga = "a cocaína";
                break;
            }
            case 3: {
                respostas = res.getIntArray(R.array.respostas_alcool);
                perguntas = res.getStringArray(R.array.perguntas_alcool);
                explicacao = res.getStringArray(R.array.explicacoes_alcool);
                imagens = getResources().obtainTypedArray(R.array.imagens_alcool);
                nomeDroga = "o álcool";
                break;
            }
            case 4: {
                respostas = res.getIntArray(R.array.respostas_crack);
                perguntas = res.getStringArray(R.array.perguntas_crack);
                explicacao = res.getStringArray(R.array.explicacoes_crack);
                imagens = getResources().obtainTypedArray(R.array.imagens_crack);
                nomeDroga = "o crack";
                break;
            }
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    public void botaoMito(View v){
        if (respostas[indice-1] == 1) {
            pontos++;
        }
        contador++;
        mViewPager.setCurrentItem(contador);
    }

    public void botaoVerdade(View v){
        if (respostas[indice-1] == 0) {
            pontos++;
        }
        contador++;
        mViewPager.setCurrentItem(contador);

    }

    public void botaoOk(View v){
        //botao da setinha, se estiver na tela de score passa para a proxima atividade, senao passa para a proxima tab
        if (contador == perguntas.length*2) {
            Intent i = new Intent(this, Main.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else {
            contador++;
            mViewPager.setCurrentItem(contador);
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        TextView txtpergunta2, txtpergunta1, txtexplicacao;
        View rootView;
        Button btresposta;
        ImageView imagem;


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


            if (contador == perguntas.length*2-1) {
                rootView = inflater.inflate(R.layout.fragment_atividade2_resposta, container, false);

                txtpergunta2 = (TextView) rootView.findViewById(R.id.txtpergunta2);
                String text = String.format(res.getString(R.string.escore));
                CharSequence styledText = Html.fromHtml(text);
                txtpergunta2.setText(styledText);

                btresposta = (Button) rootView.findViewById(R.id.btresposta);
                btresposta.setTextSize(12);
                String text2 = String.format(res.getString(R.string.pontos), pontos);
                CharSequence s = Html.fromHtml(text2);
                SpannableString ss1=  new SpannableString(s);
                ss1.setSpan(new RelativeSizeSpan(4), 0,1, 0);
                btresposta.setText(ss1);
                btresposta.setBackgroundColor(Color.parseColor("#00000000"));
                btresposta.setAllCaps(false);
                btresposta.setWidth(wrap_content);
                btresposta.setBackgroundResource(R.drawable.atividade2_button_style);
                btresposta.setTextColor(Color.GRAY);

                txtexplicacao = (TextView) rootView.findViewById(R.id.txtexplicacao);
                txtexplicacao.setText(res.getString(R.string.parabens, nomeDroga));
                txtexplicacao.setGravity(center);
            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) % 2 != 0) {
                //Tabs de numero impar sao perguntas
                rootView = inflater.inflate(R.layout.fragment_atividade2_pergunta, container, false);

                txtpergunta1 = (TextView) rootView.findViewById(R.id.txtpergunta1);
                String alala = perguntas[indice];
                String text = String.format(res.getString(R.string.pergunta), indice+1, alala);
                CharSequence styledText = Html.fromHtml(text);
                txtpergunta1.setText(styledText);

                imagem = (ImageView) rootView.findViewById(R.id.imagem_mito);
                imagem.setImageResource(imagens.getResourceId(indice, -1));
            }
            else {
                rootView = inflater.inflate(R.layout.fragment_atividade2_resposta, container, false);

                txtpergunta2 = (TextView) rootView.findViewById(R.id.txtpergunta2);
                String alala = perguntas[indice];
                String text = String.format(res.getString(R.string.pergunta), indice+1, alala);
                CharSequence styledText = Html.fromHtml(text);
                txtpergunta2.setText(styledText);

                txtexplicacao = (TextView) rootView.findViewById(R.id.txtexplicacao);
                txtexplicacao.setText(explicacao[indice]);

                btresposta = (Button) rootView.findViewById(R.id.btresposta);
                if (respostas[indice] == 1) {
                    btresposta.setText("MITO!");
                    btresposta.setBackgroundColor(Color.RED);
                }
                else {
                    btresposta.setText("VERDADE!");
                    btresposta.setBackgroundColor(Color.parseColor("#8cc63f"));
                }
                btresposta.setTextColor(Color.parseColor("#FFFFFF"));
                indice++;
            }

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

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