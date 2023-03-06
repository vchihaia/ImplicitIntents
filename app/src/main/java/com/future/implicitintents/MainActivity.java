package com.future.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.future.implicitintents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    public void openWebsite(View view) {
        String url = mBinding.websiteEdittext.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d(TAG, "Cannot handle this intent!");
        }
    }

    public void openLocation(View view) {
        String location = mBinding.locationEdittext.getText().toString();
        Uri locationUri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, locationUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d(TAG, "Cannot handle this intent!");
        }
    }

    public void shareText(View view) {
        String message = mBinding.shareEdittext.getText().toString();
        ShareCompat.IntentBuilder.from(this)
                .setText(message)
                .setChooserTitle("Share via")
                .setType("text/plain")
                .startChooser();
    }
}