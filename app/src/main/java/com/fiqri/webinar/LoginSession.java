package com.fiqri.webinar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSession extends AppCompatActivity {

    @BindView(R.id.tv_login_loc)
    TextView tvLoginLoc;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    Preference preference;
    final int PLACE_AUTO_COMPLETE_REQUEST_CODE = 1;
    final int PERMISSION = 2;
    String name = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_session);
        ButterKnife.bind(this);

        preference = new Preference(LoginSession.this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION);
        }
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked(View v) {
        switch (v.getId()) {

            case R.id.tv_login_loc:
                findLocation();
                break;

            case R.id.btn_submit:
                submitLocation();
                break;
        }
    }

    private void findLocation() {
    }

    private void submitLocation() {
        name = tvLoginLoc.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "You Must Input Your Location", Toast.LENGTH_SHORT).show();
        } else {
            preference.setLocLogin(name);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                System.exit(0);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
