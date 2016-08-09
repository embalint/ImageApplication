package com.example.krunoslavtill.imageapplication.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.Toast;

import com.example.krunoslavtill.imageapplication.MainActivity;
import com.example.krunoslavtill.imageapplication.R;

/**
 * Created by krunoslavtill on 01/08/16.
 */
public class HttpErrorHandler {
    int httpError;
    Context context;
    Throwable t;
    MainActivity mainActivity;
    public HttpErrorHandler(Context context,int httpError) {
        this.httpError = httpError;
        this.context=context;

        errorCheck();
    }

    public HttpErrorHandler(Context applicationContext, Throwable t, MainActivity mainActivity) {
        this.context=applicationContext;
        this.t=t;
        this.mainActivity=mainActivity;
        checkOfErrors();
    }

    private void checkOfErrors() {
        switch (t.getMessage().toString()) {
            case "Unable to resolve host \"www.eclecticasoft.com\": No address associated with hostname":

                Toast.makeText(context, R.string.error_no_internet_connection,
                        Toast.LENGTH_LONG).show();

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mainActivity);
                        alertDialogBuilder.setTitle("Exit Application?");
                        alertDialogBuilder
                                .setMessage("Click yes to exit!")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                android.os.Process.killProcess(android.os.Process.myPid());
                                                System.exit(1);
                                            }
                                        })

                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }, 1800);



                break;
        }
    }

    private void errorCheck() {
        switch (httpError){
            case 400:
                Toast.makeText(context, R.string.error_http_400,
                        Toast.LENGTH_LONG).show();
                break;

            case 404:
                Toast.makeText(context, R.string.error_http_404,
                        Toast.LENGTH_LONG).show();
                break;
            case 407:
                Toast.makeText(context, R.string.error_http_407,
                        Toast.LENGTH_LONG).show();
                break;
            case 408:
                Toast.makeText(context, R.string.error_http_408,
                        Toast.LENGTH_LONG).show();
                break;
            case 415:
                Toast.makeText(context, R.string.error_http_415,
                        Toast.LENGTH_LONG).show();
                break;
            case 421:
                Toast.makeText(context, R.string.error_http_421,
                        Toast.LENGTH_LONG).show();
                break;
            case 429:
                Toast.makeText(context, R.string.error_http_429,
                        Toast.LENGTH_LONG).show();
                break;

            case 500:
                Toast.makeText(context, R.string.error_http_500,
                        Toast.LENGTH_LONG).show();
                break;

            case 504:
                Toast.makeText(context, R.string.error_http_504,
                        Toast.LENGTH_LONG).show();
                break;

            case 505:
                Toast.makeText(context, R.string.error_http_505,
                        Toast.LENGTH_LONG).show();
                break;


        }
    }


}
