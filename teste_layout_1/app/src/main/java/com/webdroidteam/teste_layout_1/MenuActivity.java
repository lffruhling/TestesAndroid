package com.webdroidteam.teste_layout_1;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.adapter.ViewPagerAdapter;
import com.webdroidteam.teste_layout_1.bodyOrcar.CriarOrcamento;
import com.webdroidteam.teste_layout_1.conectService.ApiFactory;
import com.webdroidteam.teste_layout_1.conectService.ConectService;
import com.webdroidteam.teste_layout_1.fragments.Concluidas;
import com.webdroidteam.teste_layout_1.fragments.Executar;
import com.webdroidteam.teste_layout_1.fragments.Orcar;
import com.webdroidteam.teste_layout_1.models.NovoProdutoOrcado;
import com.webdroidteam.teste_layout_1.models.Produtos;
import com.webdroidteam.teste_layout_1.models.ServiceCatalog;
import com.webdroidteam.teste_layout_1.models.Servicos;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;
import com.webdroidteam.teste_layout_1.util.Mensagem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Leonardo on 30/03/2016.
 */
public class MenuActivity extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener{
    private TabHost mTabHost;
    private ViewPager mViewPager;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, MenuActivity.TabInfo>();
    private PagerAdapter mPagerAdapter;
    private static final String TAG = "MG: ";
    private Servicos servicos;

    // Informação da Tab
    private class TabInfo {
        private String tag;
        private Class<?> clss;
        private Bundle args;
        private Fragment fragment;

        TabInfo(String tag, Class<?> clazz, Bundle args) {
            this.tag = tag;
            this.clss = clazz;
            this.args = args;
        }
    }

    // Um simples factory que retorna View para o TabHost
    class TabFactory implements TabContentFactory {

        private final Context mContext;

        public TabFactory(Context context) {
            mContext = context;
        }

        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Infla o layout
        setContentView(R.layout.activity_menu);
        // Inicializa o TabHost
        this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            // Define a Tab de acordo com o estado salvo
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }

        // Inicializa o ViewPager
        this.intialiseViewPager();

        //Recebe parametro da tela de login
        Intent intent = getIntent();
        if(intent != null){
            Bundle params = intent.getExtras();

            if(params != null){
                String id_tec = params.getString("ID_TEC");

                ApiFactory.conectService().listServicosId(id_tec)
                        .subscribeOn(Schedulers.io())
                        .map(r -> r.servicos)
                        .doOnError(error -> onError(error))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onResponse, error -> onError(error));
            }
        }

    }

    private void onResponse(List<Servicos> servicoses) {
        Log.d(TAG,"response ");

        for (Servicos servicos : servicoses){
            if(servicos.validaServicos(servicos.getId_web()) == null ){
                servicos.save();

                for (Produtos produtos : servicos.produtos){
                    produtos.save();
                }
            }
        }

        Executar executar = new Executar();
        executar.setListServicos(servicoses);
    }

    private void onError(Throwable error) {
        Log.d(TAG,"error "+error.getMessage());
    }

    protected void onSaveInstanceState(Bundle outState) {

        // salva a Tab selecionada
        outState.putString("tab", mTabHost.getCurrentTabTag());
        super.onSaveInstanceState(outState);
    }

    private void intialiseViewPager() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Orcar.class.getName()));
        fragments.add(Fragment.instantiate(this, Executar.class.getName()));
        fragments.add(Fragment.instantiate(this, Concluidas.class.getName()));
        this.mPagerAdapter = new ViewPagerAdapter(
                super.getSupportFragmentManager(), fragments);
        this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void initialiseTabHost(Bundle args) {

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        TabInfo tabInfo = null;
        MenuActivity.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab1").setIndicator("Orçar"),
                (tabInfo = new TabInfo("Tab1", Orcar.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        MenuActivity.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab2").setIndicator("Executar"),
                (tabInfo = new TabInfo("Tab2", Executar.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        MenuActivity.AddTab(this, this.mTabHost,
                this.mTabHost.newTabSpec("Tab3").setIndicator("Concluídas"),
                (tabInfo = new TabInfo("Tab3", Concluidas.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        mTabHost.setOnTabChangedListener(this);
    }

    private static void AddTab(MenuActivity activity, TabHost tabHost,
                               TabHost.TabSpec tabSpec, TabInfo tabInfo) {

        // Attach uma Tab view factory para o spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
        Log.i("LOG","Cria Aba");
    }

    public void onTabChanged(String tag) {

        // Avisa para o mViewPager qual a Tab que está ativa
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
        Log.i("LOG","Aba ativa");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        this.mTabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_filtro:
                Toast.makeText(getApplicationContext(), "speaking....", Toast.LENGTH_LONG).show();
                return false;
            case R.id.action_menu_sinc:
                Toast.makeText(getApplicationContext(), "stopping....", Toast.LENGTH_LONG).show();
                return false;
            default:
                break;
        }

        return false;
    }

    public void sincOs (View view){
        Toast.makeText(this, "Atualizando...", Toast.LENGTH_SHORT).show();
        Intent iinent= new Intent(MenuActivity.this,MenuActivity.class);
        startActivity(iinent);
        finish();

    }

    public void sair (View view){
        finish();
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    public void logout(View view){
        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(this);
        usuarioPreferences.setIdUser("");
        usuarioPreferences.setToken("");

        Servicos servicos = new Servicos();
        servicos.limpaBanco();
        Produtos produtos = new Produtos();
        produtos.limpaBanco();
        NovoProdutoOrcado novoProdutoOrcado = new NovoProdutoOrcado();
        novoProdutoOrcado.limpaBanco();

        finish();
    }
}
