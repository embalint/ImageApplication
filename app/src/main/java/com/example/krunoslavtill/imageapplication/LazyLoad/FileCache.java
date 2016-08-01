package com.example.krunoslavtill.imageapplication.LazyLoad;
import android.content.Context;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Deni Slunjski on 31.3.2016..
 */
public class FileCache {
    private File cacheDir;

    private ArrayList<String> oldPictures;
    private Context context;

    public FileCache(Context mContext) {

        context = mContext;
        //Find the dir at SDCARD to save cached images

        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            //if SDCARD is mounted (SDCARD is present on device and mounted)


            cacheDir = new File(
                    android.os.Environment.getExternalStorageDirectory().toString());
        } else {
            // if checking on simulator the create cache dir in your application context
            cacheDir = context.getCacheDir();
        }

        if (!cacheDir.exists()) {
            // create cache dir in your application context
            cacheDir.mkdirs();
        }
    }

    public FileCache(Context mContext, boolean Players) {

        context = mContext;
        //Find the dir at SDCARD to save cached images

        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            //if SDCARD is mounted (SDCARD is present on device and mounted)


            cacheDir = new File(
                    android.os.Environment.getExternalStorageDirectory().toString());
        } else {
            // if checking on simulator the create cache dir in your application context
            cacheDir = context.getCacheDir();
        }

        if (!cacheDir.exists()) {
            // create cache dir in your application context
            cacheDir.mkdirs();
        }
    }


    public File getFile(String url, String imageName) {
        //Identify images by hashcode or encode by URLEncoder.encode.
        //String filename=String.valueOf(url.hashCode());

        File f = new File(cacheDir, imageName + ".png");
        return f;

    }

    public File[] getAllFiles() {
        File[] files = cacheDir.listFiles();
        return files;

    }
/*
    public void clear() {

        // list all files inside cache directory
        File[] files = cacheDir.listFiles();
        Date date = new Date();
        long today = date.getTime();
        long threeWeeks = today - (1000 * 60 * 60 * 24 * 21);

        String threeWeeksBefore = Util.convertTimestampToDate(threeWeeks);

        for (int i = 0; i < files.length; i++) {
            String name = files[i].getAbsolutePath();
            String fileName = name.substring(name.lastIndexOf("/") + 1);
            String[] justName = fileName.split("\\.");
            String[] timestamp = justName[0].split("\\-");
            String time = timestamp[1];

            if (Long.valueOf(time) < Long.valueOf(threeWeeksBefore)) {
                files[i].delete();
            }

        }

        // It could happend that for two day we have one timestamp for news pictures, and on thirt day we have new timestamp for pictures
        // that way I'm having here this two method.
        // First I'm checking if there is any picture with wrong timestamp, and I'm saving this picture into arraylist
        // In second method I'm deleting all from arraylist.
        checkNewsPicturesTimestampInXMLWithNewsPicturesTimestampOnDevide();

        deleteAllPicturesWithWrongTimeStamp();

        if (files == null)
            return;


    }

    public void checkNewsPicturesTimestampInXMLWithNewsPicturesTimestampOnDevide() {

        oldPictures = new ArrayList<String>();
        //Log.d(FileCache.class.getSimpleName(), "SIZE OF ARRAYLIST WITH PICTURES, WHICH WE NEED TO DELETE" + oldPictures.size());

        // FIRST I NEED TO CHECK IF THERE ARE ANY FILE PICTURES ON DEVICE, OTHERWISE I WILL GET NULL POINTER EXCEPTION
        if (Util.isThereAtLeastOneNewsPictureFile() >= 1) {

            for (int index1 = 0; index1 < MyApplication.getInstance().getHomeScreenNews().size(); index1++) {

                HomeScreenNews homeNews = MyApplication.getInstance().getHomeScreenNews().get(index1);

                if( homeNews.getNewsPictures().size() >= 1 ) {
                    String xmlFileName = homeNews.getNewsID() + "-" + homeNews.getNewsPictures().get(MyApplication.getInstance().getIndexOfNewsPicturesUnderArrayListForDownload()).getPicChangeTime() + ".png";
                    String localFileName = Util.getNewsFileName(xmlFileName);

                    // ALL PICTURES ON DEVICE ARE NOT SAME AS PICTURES ON XML FILE ... IT CAN HAPPEND BECAUSE OF 3 REASONS
                    // 1) PICTURE ON XML FILE AND ON DEVICE, HAVE THE SAME ID, BUT DIFFERENT TIMESTAMP.
                    // 1) THAT MEANS I NEED TO DELETE OLD PICTURE AND INSERT NEW PICTURE FROM XML FILE
                    // 2) NEW PICTURES IS ON XML FILE, BUT THIS NEW PICTURE IS NOT YET ADDED TO DEVICE
                    // 3) ALL PICTURES OLDER THEN 3 WEEKS I NEED TO DELETE

                    if (localFileName == null) {
                        // do nothing
                    } else {
                        if (xmlFileName.equals(localFileName)) {
                            // do nothing
                        } else {
                            oldPictures.add(localFileName);
                        }
                    }
                }
                //else
                    //Log.d(FileCache.class.getSimpleName(), "THAT NEWS HAS ID: " + homeNews.getNewsID());

            }

            ArrayList<String> test1 = oldPictures;

            //Log.d(AsyncAddUser.class.getSimpleName(), "SIZE OF ARRAYLIST WITH PICTURES, WHICH WE NEED TO DELETE" + test1.size() );
        } else {
            // DO NOTHING, USER DID NOT YET STARTED TO DOWNLOAD PICTURE
        }

    }

    private void deleteAllPicturesWithWrongTimeStamp() {

        if (oldPictures.size() >= 1) {

            File[] files = cacheDir.listFiles();
            for (File fileName : files) {

                String currentFileName = fileName.getName();

                for (String s : oldPictures) {

                    String[] localPictureID = currentFileName.split("-");
                    String[] xmlPictureID = s.split("-");

                    if (localPictureID[0].equals(xmlPictureID[0])) {
                        Settings.getDeletedOldPictureFiles().remove(s);

                        File f = new File(cacheDir + "/" + s);
                        f.delete();
                        MyApplication.getInstance().getNewsFilePictures().remove(f);

                        Log.d(AsyncRemoveAllUnnecessaryImagesFromApplication.class.getSimpleName(), "File name: " + f);
                        break;
                    }
                }
            }
        }
    }
*/

}
