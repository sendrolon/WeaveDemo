/*
 * Copyright (C) 2015 Google
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.freescale.samples.apps.sensordemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.apps.weave.apis.data.WeaveDevice;

/**
 * Show controls to enable or disable LEDs on a given {@link WeaveDevice}.
 */
public class LedActivity extends AppCompatActivity implements WeaveDeviceProvider {

    public static final String EXTRA_KEY_WEAVE_DEVICE = BuildConfig.APPLICATION_ID + ".weave_device";
    private String TAG = this.getClass().toString();
    private WeaveDevice mDevice;
    private Switch updateEnableSwitch;

    android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                if (updateEnableSwitch.isChecked()) {
                    LedSwitchesFragment fragment = (LedSwitchesFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.led_fragment);
                    fragment.updateLightStates();
                    Log.d(TAG, "Timer rings!!");
                }
                android.os.Message message = new android.os.Message();
                message.what = 1;
                handler.sendMessageDelayed(message, 1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leds);

        mDevice = getDeviceFromIntent(getIntent());
        if (mDevice == null) {
            throw new IllegalArgumentException("No WeaveDevice set in intent extra " +
                    EXTRA_KEY_WEAVE_DEVICE);
        }

        updateEnableSwitch = (Switch) findViewById(R.id.sensor_update_switch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_leds);
        toolbar.setTitle(R.string.title_text);
        toolbar.setSubtitle(mDevice.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateEnableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        android.os.Message msg = new android.os.Message();
        msg.what = 1;
        handler.sendMessage(msg);
    }

    private WeaveDevice getDeviceFromIntent(Intent intent) {
        return intent.getParcelableExtra(EXTRA_KEY_WEAVE_DEVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_leds, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_licenses:
                new LicenseDialog().show(getSupportFragmentManager(), "Show licenses");
                return true;
            case R.id.action_refresh:
                LedSwitchesFragment fragment = (LedSwitchesFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.led_fragment);
                fragment.updateLightStates();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public WeaveDevice getDevice() {
        return mDevice;
    }



}
