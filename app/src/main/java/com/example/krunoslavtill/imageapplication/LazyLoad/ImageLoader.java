package com.example.krunoslavtill.imageapplication.LazyLoad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;


import com.example.krunoslavtill.imageapplication.HomePage;
import com.example.krunoslavtill.imageapplication.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Deni Slunjski on 31.3.2016..
 */
public class ImageLoader {
    // Initialize MemoryCache
    MemoryCache memoryCache = new MemoryCache();

    FileCache fileCache;
    String imageName;

    private Bitmap bitmap1;

    //Create Map (collection) to store image and image url in key value pair
    public static Map<ImageView, String> imageViews = Collections.synchronizedMap(
            new WeakHashMap<ImageView, String>());

    ExecutorService executorService;

    //handler to display images in UI thread
    android.os.Handler handler = new android.os.Handler();
    Drawable placeHolderForNews;

    // we are using this imageLoader to save pictures from news, from dimTV
    public ImageLoader(Context context){

        fileCache = new FileCache(context);

        // Creates a thread pool that reuses a fixed number of
        // threads operating off a shared unbounded queue.
/*
        executorService= Executors.newFixedThreadPool(5);
        if(HomePage.getInstance().getPlaceholderHomeNews() == null) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            Bitmap temp = BitmapFactory.decodeResource( context.getResources(), R.drawable.profile, options).copy(Bitmap.Config.RGB_565, false);
            Drawable d = new BitmapDrawable(temp);
            MyApplication.getInstance().setPlaceholderHomeNews(d);
            placeHolderForNews = d;


        }else{
            placeHolderForNews = MyApplication.getInstance().getPlaceholderHomeNews();
        }
*/
    }

    // default image show in list (Before online image download)




    public void DisplayImage(String url, ImageView imageView, String imageName) {

        try {
            //Store image and url in Map
            imageViews.put(imageView, url);

            //Check image is stored in MemoryCache Map or not (see MemoryCache.java)
            Bitmap bitmap = memoryCache.get(url);

            if (bitmap != null) {
                // if image is stored in MemoryCache Map then
                // Show image in listview row
                imageView.setImageBitmap(bitmap);
            } else {
                //queue Photo to download from url
                queuePhoto(url, imageView, imageName);

                //Before downloading image show default image
                imageView.setImageDrawable(placeHolderForNews);
            }
        } catch (Exception e) {
            //Log.d(ImageLoader.class.getSimpleName(), "Error 1: " + e);
            //Before downloading image show default image
            imageView.setImageDrawable(placeHolderForNews);
        }
    }


    private void queuePhoto(String url, ImageView imageView, String imageName)
    {
        // Store image and url in PhotoToLoad object
        PhotoToLoad p = new PhotoToLoad(url, imageView, imageName);

        // pass PhotoToLoad object to PhotosLoader runnable class
        // and submit PhotosLoader runnable to executers to run runnable
        // Submits a PhotosLoader runnable task for execution

        executorService.submit(new PhotosLoader(p));
    }

    //Task for the queue
    private class PhotoToLoad
    {
        public String url;
        public ImageView imageView;
        public String imageName;
        public PhotoToLoad(String u, ImageView i, String x){
            url=u;
            imageView=i;
            imageName = x;
        }
    }

    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;

        PhotosLoader(PhotoToLoad photoToLoad){
            this.photoToLoad=photoToLoad;
        }

        @Override
        public void run() {
            try{
                //Check if image already downloaded
                if(imageViewReused(photoToLoad))
                    return;
                // download image from web url
                Bitmap bmp = getBitmap(photoToLoad.url, photoToLoad.imageName);

                // set image data in Memory Cache
                memoryCache.put(photoToLoad.url, bmp);

                if(imageViewReused(photoToLoad))
                    return;

                // Get bitmap to display
                BitmapDisplayer bd=new BitmapDisplayer(bmp, photoToLoad);

                // Causes the Runnable bd (BitmapDisplayer) to be added to the message queue.
                // The runnable will be run on the thread to which this handler is attached.
                // BitmapDisplayer run method will call
                handler.post(bd);

            }catch(Throwable th){
                th.printStackTrace();
            }
        }
    }

    private Bitmap getBitmap(String url, String imageName)
    {
        File f = fileCache.getFile(url,imageName);

        //from SD cache
        //CHECK : if trying to decode file which not exist in cache return null
        Bitmap b = decodeFile(f);
        if(b!=null) {
            //Log.v(ImageLoader.class.getSimpleName(), "We have found this picture on device: " + imageName);
            return b;
        }

        // Download image file from web
        try {

            Bitmap bitmap=null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is=conn.getInputStream();

            // Constructs a new FileOutputStream that writes to file
            // if file not exist then it will create file
            OutputStream os = new FileOutputStream(f);

            // See Utils class CopyStream method
            // It will each pixel from input stream and
            // write pixels to output stream (file)
            Utils.CopyStream(is, os);

            os.close();
            conn.disconnect();

            // Now file created and going to resize file with defined height
            // Decodes image and scales it to reduce memory consumption
            bitmap = decodeFile(f);

            return bitmap;

        } catch (Throwable ex){
            ex.printStackTrace();
            if(ex instanceof OutOfMemoryError)
                memoryCache.clear();
            return null;
        }
    }

    //Decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f){

        try {

            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inScaled = false;
            FileInputStream stream1=new FileInputStream(f);
            BitmapFactory.decodeStream(stream1,null,o);
            stream1.close();

            //Find the correct scale value. It should be the power of 2.

            // Set width/height of recreated image
            // BEFORE WAS WRITEN LIKE THIS, SOME STATIC VALUE 300.. final int REQUIRED_SIZE=300;
            // 21.04.2016, now I have rather add some dynamic value
            final int REQUIRED_SIZE=300;

            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2 < REQUIRED_SIZE || height_tmp/2 < REQUIRED_SIZE) {
                    //Log.i(ImageLoader.class.getSimpleName(), "Here will break because of conditions which is good so.");
                    break;
                }
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            //decode with current scale values
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            FileInputStream stream2=new FileInputStream(f);
            Bitmap bitmap=BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;

        } catch (FileNotFoundException e) {
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean imageViewReused(PhotoToLoad photoToLoad){

        String tag=imageViews.get(photoToLoad.imageView);
        //Check url is already exist in imageViews MAP
        if(tag==null || !tag.equals(photoToLoad.url))
            return true;
        return false;
    }

    //Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable
    {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p){bitmap=b;photoToLoad=p;}
        public void run()
        {
            if(imageViewReused(photoToLoad))
                return;

            // Show bitmap on UI
            if(bitmap!=null)
                photoToLoad.imageView.setImageBitmap(bitmap);
            else
                photoToLoad.imageView.setImageDrawable(placeHolderForNews);
        }
    }

    public void clearCache() {
        //Clear cache directory downloaded images and stored data in maps
        memoryCache.clear();
        //fileCache.clear();
        
        

    }

}
