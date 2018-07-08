package com.fiqri.webinar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_hours)
    TextView tvHours;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    String loginLocation;
    TextView tvInputLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Preference preference = new Preference(MainActivity.this);
        loginLocation = preference.getLocLogin();
        if (loginLocation.isEmpty()) {
            startActivity(new Intent(this, LoginSession.class));
            finish();
        }

        date();
        hours();
        getScheduleSholat(loginLocation);
    }

    private void getScheduleSholat(String detailPlaces) {

        String location = detailPlaces;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat
                = new SimpleDateFormat("yyyy-MM-dd");

        final String currentDateSholat = dateFormat.format(new Date());
        final ProgressDialog dialog = ProgressDialog.show(
                MainActivity.this, "", "Loading", false);

        /**
         * Eksekusi dengan retrofit dan kirim request API
         */

    }

    private void date() {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format
                = new SimpleDateFormat("EEE, d MMM yyyy");
        final String currentDate = format.format(new Date());
        tvDate.setText(currentDate);
    }

    private void hours() {

        final Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void run() {
                tvHours.setText(new SimpleDateFormat("K:mm a").format(new Date()));
                handler.postDelayed(this, 1000);
            }
        }, 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item_your:
                changeLocation();
                break;

            case R.id.item_logout:
                break;

            case R.id.exit:
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeLocation() {

        LayoutInflater inflater = getLayoutInflater();
        final View alert = inflater.inflate(R.layout.item_location, null);
        tvInputLoc = alert.findViewById(R.id.tv_input_loc);

        tvInputLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNewLoc();
            }
        });

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(alert);

        dialog.setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        dialog.show();

    }

    private void findNewLoc() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
