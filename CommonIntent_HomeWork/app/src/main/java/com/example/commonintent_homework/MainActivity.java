package com.example.commonintent_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Code for Create Alarm, Get Map Position and Load URL
        ImageView youtubeImg = findViewById(R.id.youtube);

        youtubeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage("https://www.youtube.com");
            }
        });

        ImageView mapImage = findViewById(R.id.map);

        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", "This is Map Debug");
                showMapLocation("geo:37.7749,-122.4194");
            }
        });

        ImageView noteImage = findViewById(R.id.phone);
        noteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone("0943637724");
            }
        });

        ImageView clockImage = findViewById(R.id.create_alarm);
        clockImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlarm("Common Intent to create an Alarm" , 9 , 30);
            }
        });

        ImageView timerImage = findViewById(R.id.create_timer);
        timerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer("Common Intent to create an Timer" , 30);
            }
        });

        ImageView calendarImage = findViewById(R.id.create_event);
        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent("Common Intent to create an event" , "Ho Chi Minh City" , 10000000, 2000000);
            }
        });

        ImageView contactImage = findViewById(R.id.create_contact);
        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertContact("Common Intent to create new contact", "viethoangnghia@gmail.com");
            }
        });

        ImageView emailImage = findViewById(R.id.create_email);
        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail(new String[]{"viethoangnghia@gmail.com"}, "This is subject");
            }
        });

        ImageView musicImage = findViewById(R.id.create_music);
        musicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSearchArtist("Taylor Swift");
            }
        });
    }

    public void callPhone(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public void showMapLocation(String location) {
        Uri sanFranciscoLoc = Uri.parse(location);
        Intent intent = new Intent(Intent.ACTION_VIEW, sanFranciscoLoc);
        intent.setData(sanFranciscoLoc);
        startActivity(intent);
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        startActivity(intent);
    }
    public void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(intent);
    }

    public void addEvent(String title, String location, long begin, long end) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
            startActivity(intent);
    }

    public void insertContact(String name, String email) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
            startActivity(intent);
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            startActivity(intent);

    }

    public void playSearchArtist(String artist) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
        intent.putExtra(MediaStore.EXTRA_MEDIA_FOCUS,
                MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE);
        intent.putExtra(MediaStore.EXTRA_MEDIA_ARTIST, artist);
        intent.putExtra(SearchManager.QUERY, artist);
            startActivity(intent);

    }
}
