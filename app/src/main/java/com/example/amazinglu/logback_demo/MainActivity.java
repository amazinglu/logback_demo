package com.example.amazinglu.logback_demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * way to use FileAppender in android app
 *
 * https://github.com/tony19/logback-android/wiki/Appender-Notes
 *
 * the file path for the appender should be absolute path and the app should have
 * read and write permission to the path (internal storage)
 *
 * but use cache dir work only sometimes
 * use file dir is fine, but remember to delete the record from time to time to save space
 * */

public class MainActivity extends AppCompatActivity {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File cache = getCacheDir();
        String cachePath = cache.getAbsolutePath();

        File file = getFilesDir();
        String filePath = file.getAbsolutePath();

        System.out.println("amazing");

        String[] fileList = fileList();

        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;

        List<String> logRecords = new ArrayList<>();

        /**
         * clear the myApp.txt
         * */
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("myApp.txt", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.write(("").getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        logger.info("Main Activity start");
        logger.info("Main Activity start again");

        /**
         * read record in myApp.txt in a line
         * */
        try {
            fileInputStream = openFileInput("myApp.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            logRecords.add(line);
            while (line != null) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                logRecords.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logger.info("Main Activity end");
    }
}
