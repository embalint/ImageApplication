package com.example.krunoslavtill.imageapplication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.krunoslavtill.imageapplication.Adapters.FragmentAdapter;
import com.example.krunoslavtill.imageapplication.Adapters.ImageAdapter;
import com.example.krunoslavtill.imageapplication.Fragments.NewsFragment;
import com.example.krunoslavtill.imageapplication.Fragments.SquadFragment;
import com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels.SquadModel;
import com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels.News;
import com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels.Players;
import com.example.krunoslavtill.imageapplication.Utils.VerticalSpaceItemDecoration;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private static final int VERTICAL_ITEM_SPACE = 8;

    String imageFramework;
    ImageAdapter imageAdapter;
    List<String> picturesList= new ArrayList<>();
    List<String> playerPicturesUrl= new ArrayList<>();
    List<String> playerNames= new ArrayList<>();
    List<String> newsURL= new ArrayList<>();
    List<Integer> playersNumber= new ArrayList<>();
    List<Players> listOfAllPlayersAtributes;
    List<News> listAllNews;
    TextView headerPosition;
    // this position is for header in recycleview, but its -1 because i dont want to show it here
    int sectionPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        imageFramework="LazyLoad";
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addNewsInfo();
        //addPlayersPictures();

        SquadModel sm = new SquadModel();
        sm.setImageFramework(imageFramework);
        //sm.setmRecyclerView(mRecyclerView);
        sm.setPicturesList(picturesList);
        sm.setNewsURL(newsURL);
        sm.setItemSpace(VERTICAL_ITEM_SPACE);
        Registry.getInstance().set("SquadModel",sm);

        NewsFragment nf = new NewsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, nf).commit();


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.lazy_load) {
            /*
            Snackbar.make(mRecyclerView, "You choised LazyLoad", Snackbar.LENGTH_SHORT)
                    .show();
            /*
            Toast.makeText(getApplication(), "You choised LazyLoad",
                    Toast.LENGTH_LONG).show();
                    */
            imageFramework="LazyLoad";

            SquadModel sm = new SquadModel();
            sm.setImageFramework(imageFramework);
            sm.setmRecyclerView(mRecyclerView);
            sm.setPicturesList(picturesList);
            sm.setNewsURL(newsURL);
            sm.setItemSpace(VERTICAL_ITEM_SPACE);
            Registry.getInstance().set("SquadModel",sm);

            NewsFragment nf = new NewsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, nf).commit();


        } else if (id == R.id.glide) {
            Snackbar.make(mRecyclerView, "You choised Glide Framework", Snackbar.LENGTH_SHORT)
                    .show();

            // Toast.makeText(getApplication(), "You choised Glide Framework",
            //s       Toast.LENGTH_LONG).show();

            imageFramework="Glide";
            imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework,sectionPosition);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(imageAdapter);

        } else if (id == R.id.picasso) {

            Snackbar.make(mRecyclerView, "You choised Picasso Framework", Snackbar.LENGTH_SHORT)
                    .show();
            /*
            Toast.makeText(getApplication(), "You choised Picasso Framework",
                    Toast.LENGTH_LONG).show();
                    */
            imageFramework="Picasso";
            imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework,sectionPosition);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(imageAdapter);




        } else if (id == R.id.team_players) {


            /*
            Toast.makeText(getApplication(), "You choised Picasso Framework",
                    Toast.LENGTH_LONG).show();

            // Begin the transaction
            FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
            adapter.addFragment(new SquadFragment(), "Playlist");
            */
            imageFramework="Squad";
            SquadModel sm = new SquadModel();
            sm.setImageFramework(imageFramework);
            sm.setmRecyclerView(mRecyclerView);
            sm.setPlayerPicturesUrl(playerPicturesUrl);
            sm.setPlayerNames(playerNames);
            sm.setItemSpace(VERTICAL_ITEM_SPACE);
            Registry.getInstance().set("SquadModel",sm);

            SquadFragment sf = new SquadFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, sf).commit();






            /*
            imageAdapter = new ImageAdapter(getApplicationContext(),playerPicturesUrl,playerNames,imageFramework);

            mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            // here i can manage how many columms i will have
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mRecyclerView.setAdapter(imageAdapter);
            */

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void addPlayersPictures() {

        listOfAllPlayersAtributes = (List<Players>) Registry.getInstance().get("Players");
       // List<AllPlayerPictures> allPlayerPicturesList = (List<AllPlayerPictures>) Registry.getInstance().get("playerPictures");


        for (int i=0;i<listOfAllPlayersAtributes.size();i++){
            playerPicturesUrl.add(listOfAllPlayersAtributes.get(i).getPlayerPicture());
            playerNames.add(listOfAllPlayersAtributes.get(i).getPlayerName());
            playersNumber.add(listOfAllPlayersAtributes.get(i).getPlayerNumber());
        }
    }
    private void addNewsInfo() {
        listAllNews = (List<News>) Registry.getInstance().get("NewsObjects");

        for (int i=0;i<listAllNews.size();i++){
            newsURL.add(listAllNews.get(i).getNewsTitle());
            picturesList.add(listAllNews.get(i).getPictureUrl());
        }
    }
}
