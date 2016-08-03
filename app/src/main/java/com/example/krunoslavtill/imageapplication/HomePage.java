package com.example.krunoslavtill.imageapplication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.krunoslavtill.imageapplication.Adapters.ImageAdapter;
import com.example.krunoslavtill.imageapplication.RetrofitModels.AllPlayerPictures;
import com.example.krunoslavtill.imageapplication.RetrofitModels.PlayerPictures;
import com.example.krunoslavtill.imageapplication.RetrofitModels.PrevPicture;
import com.example.krunoslavtill.imageapplication.Utils.VerticalSpaceItemDecoration;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private static final int VERTICAL_ITEM_SPACE = 8;
    String imageFramework;
    List<String> picturesList= new ArrayList<>();
    List<String> playerPicturesUrl= new ArrayList<>();
    List<String> newsURL,playerNames;
    ImageAdapter imageAdapter;
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

        mRecyclerView = (RecyclerView) findViewById(R.id.galleryRecyclerView);
        Log.d("emil", Glide.getPhotoCacheDir(getApplicationContext()).getPath());

        newsURL = (List<String>) Registry.getInstance().get("newsURL");

        List<PrevPicture> prevPictureListList = (List<PrevPicture>) Registry.getInstance().get("pictures");
        for (int i=0;i<prevPictureListList.size();i++){
            picturesList.add(prevPictureListList.get(i).getPicURL());
        }
        addPlayersPictures();
        imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);

        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        // here i can manage how many columms i will have
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(imageAdapter);

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
            Snackbar.make(mRecyclerView, "You choised LazyLoad", Snackbar.LENGTH_SHORT)
                    .show();
            /*
            Toast.makeText(getApplication(), "You choised LazyLoad",
                    Toast.LENGTH_LONG).show();
                    */
            imageFramework="LazyLoad";
            ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(imageAdapter);



            mRecyclerView.setAdapter(imageAdapter);

        } else if (id == R.id.glide) {
            Snackbar.make(mRecyclerView, "You choised Glide Framework", Snackbar.LENGTH_SHORT)
                    .show();

            // Toast.makeText(getApplication(), "You choised Glide Framework",
            //s       Toast.LENGTH_LONG).show();
            imageFramework="Glide";
            imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);
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
            imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(imageAdapter);




        } else if (id == R.id.team_players) {

            Snackbar.make(mRecyclerView, "You choised Squad", Snackbar.LENGTH_SHORT)
                    .show();
            /*
            Toast.makeText(getApplication(), "You choised Picasso Framework",
                    Toast.LENGTH_LONG).show();
                    */
            imageFramework="Squad";
            imageAdapter = new ImageAdapter(getApplicationContext(),playerPicturesUrl,playerNames,imageFramework);

            mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            // here i can manage how many columms i will have
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            
            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mRecyclerView.setAdapter(imageAdapter);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void addPlayersPictures() {
        playerNames = (List<String>) Registry.getInstance().get("playerNames");

        List<AllPlayerPictures> allPlayerPicturesList = (List<AllPlayerPictures>) Registry.getInstance().get("playerPictures");
        for (int i=0;i<allPlayerPicturesList.size();i++){
            playerPicturesUrl.add(allPlayerPicturesList.get(i).getPicURL());
        }
    }
}
