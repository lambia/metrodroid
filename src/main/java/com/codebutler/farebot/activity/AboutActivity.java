/*
 * AboutActivity.java
 *
 * Copyright 2015-2016 Michael Farrell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.codebutler.farebot.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import au.id.micolous.farebot.R;
import com.codebutler.farebot.util.Utils;

/**
 * @author Michael Farrell
 */
public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ((TextView) this.findViewById(R.id.lblDebugText)).setText(Utils.getDeviceInfoString());

    }

    public void onWebsiteClick(View view) {
        Uri.Builder b = Uri.parse("https://micolous.github.io/metrodroid/").buildUpon();
        int version = -1;
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            // Shouldn't hit this...
        }

        // Pass the version number to the website.
        // This allows the website to have a hook showing if the user's version is out of date
        // and flag specifically which cards *won't* be supported (or have problems).
        b.appendQueryParameter("ver", Integer.toString(version));
        startActivity(new Intent(Intent.ACTION_VIEW, b.build()));
    }

    public void onLicenseClick(View view) {
        startActivity(new Intent(this, LicenseActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }
}
