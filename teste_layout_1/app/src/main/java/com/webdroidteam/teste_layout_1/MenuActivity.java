package com.webdroidteam.teste_layout_1;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.adapter.ViewPagerAdapter;
import com.webdroidteam.teste_layout_1.fragments.Concluidas;
import com.webdroidteam.teste_layout_1.fragments.Executar;
import com.webdroidteam.teste_layout_1.fragments.Orcar;


/**
 * Created by Leonardo on 30/03/2016.
 */
public class MenuActivity extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener{
    private TabHost mTabHost;
    private ViewPager mViewPager;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, MenuActivity.TabInfo>();
    private PagerAdapter mPagerAdapter;

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
                //Toast.makeText(this, id_tec, Toast.LENGTH_LONG).show();
                // Chama consulta ao Web-Service passando id do usuário;

            }
        }

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
}
