/*
 * Kingsgate Media Player
 * Copyright (C) 2016 Jon Burney (jon@version7.co.uk)
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
 *
 */
package jonburney.version7.kingsgatemediaplayer.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import jonburney.version7.kingsgatemediaplayer.Components.IApplicationComponent;
import jonburney.version7.kingsgatemediaplayer.Fragments.VideoListPhoneFragment;
import jonburney.version7.kingsgatemediaplayer.MainApp;
import jonburney.version7.kingsgatemediaplayer.R;

/**
 * Home activity - The main activity when first starting the appilication
 */
public class MainActivity extends BaseActivity {

    /**
     * Executed when the activity is created
     *
     * @param savedInstanceState The activity state
     *
     * @return void
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.frame_container);

        getFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), VideoListPhoneFragment.newInstance()).commit();
    }

    protected IApplicationComponent getApplicationComponent() {
        return ((MainApp)getApplication()).getApplicationComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
