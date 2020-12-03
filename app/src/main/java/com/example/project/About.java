package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Element;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mehdi.sakout.aboutpage.Element adsElement = new Element();
//        adsElement.setTitle("Advertise here");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                //.setImage(R.drawable.canopy_logo)
                .setDescription("This is a demo version of our app")
                .addItem(new mehdi.sakout.aboutpage.Element().setTitle("Version 1.0"))
                //.addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("clement_ravindran@mymail.sutd.edu.sg")
                .addWebsite("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                .addFacebook("SUTD")
                .addTwitter("rickastley")
                .addYoutube("watch?v=dQw4w9WgXcQ")
                .addPlayStore("My PlayStore")
                .addInstagram("officialrickastley")
                .addGitHub("Lanvoine")
                //.addItem(createCopyright())
                .create();

        setContentView(aboutPage);
    }

//    private mehdi.sakout.aboutpage.Element createCopyright() {
//        mehdi.sakout.aboutpage.Element copyright = new Element();
//        String copyrightString = String.format("Copyright %d by SUTD", Calendar.getInstance().get(Calendar.YEAR));
//        copyright.setTitle(copyrightString);
//        copyright.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(About.this,copyrightString,Toast.LENGTH_SHORT).show();
//            }
//        });
//        return copyright;
//    }
}