package com.example.krunoslavtill.imageapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.krunoslavtill.imageapplication.Adapters.ImageAdapter;
import com.example.krunoslavtill.imageapplication.Models.PrevPicture;
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
    List<String> newsURL;
    ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageFramework="Glide";
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
        imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);

        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
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

        if (id == R.id.glide) {
            Toast.makeText(getApplication(), "You choised Glide Framework",
                    Toast.LENGTH_LONG).show();
            imageFramework="Glide";
            ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mRecyclerView.setAdapter(imageAdapter);
        } else if (id == R.id.picasso) {
            Toast.makeText(getApplication(), "You choised Picasso Framework",
                    Toast.LENGTH_LONG).show();
            imageFramework="Picasso";
            ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);
            mRecyclerView.setAdapter(imageAdapter);


        } else if (id == R.id.lazy_load) {
            Toast.makeText(getApplication(), "You choised LazyLoad",
                    Toast.LENGTH_LONG).show();
            imageFramework="LazyLoad";
            ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(),picturesList,newsURL,imageFramework);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(imageAdapter);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
