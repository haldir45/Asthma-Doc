package com.example.win7.asthmadoc;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;



import com.example.win7.adapters.NavListAdapter;
import com.example.win7.fragments.MyAbout;
import com.example.win7.fragments.MyHome;
import com.example.win7.fragments.MySettings;
import com.example.win7.models.NavItem;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity  {

     DrawerLayout drawerLayout;
    RelativeLayout drawerPane;
    ListView lvNav;
    List<NavItem> listNavItems;
    List<Fragment> listFragments;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger_icon);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);

        listNavItems = new ArrayList<NavItem>();
        listNavItems.add(new NavItem("Home","Home page",R.drawable.home_icon));
        listNavItems.add(new NavItem("Settings", "Change Profile Info", R.drawable.settings_icon));
        listNavItems.add(new NavItem("About", "Author's information", R.drawable.about_icon));

        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext(),R.layout.item_nav_list,listNavItems);

        lvNav.setAdapter(navListAdapter);


        listFragments = new ArrayList<Fragment>();
        listFragments.add(new MyHome());
        listFragments.add(new MySettings());
        listFragments.add(new MyAbout());


        //load first fragment as default:
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, listFragments.get(0)).commit();

        setTitle(listNavItems.get(0).getTitle());
        lvNav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerPane);

        //set listener for navigation items
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //replace the fragment with the selection correspondingly
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, listFragments.get(position)).commit();

                setTitle(listNavItems.get(position).getTitle());
                lvNav.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerPane);

            }
        });

        //create listener for drawer layout
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,drawerLayout,R.string.drawer_opened,R.string.drawer_closed)
        {
            public void onDrawerOpened(View drawerView){
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_icon);
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);

            };
            public void onDrawerClosed(View drawerView){
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger_icon);
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);

            };
        };
        //actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.hamburger_icon);
       // actionBarDrawerToggle.setDisplayHomeAsUpEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        // SeekBars 

        
    } // end onCreate method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }
}
