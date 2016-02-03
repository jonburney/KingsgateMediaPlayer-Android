package jonburney.version7.kingsgatemediaplayer;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jburney on 03/02/2016.
 */
public class VideoList extends ListActivity
{
    ArrayList<String> listItems = new ArrayList<String>() {{
        add("Test Video 1");
        add("Test Video 2");
    }};
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void onStart() {
        super.onStart();

        Log.i("TEST", "THIS IS A TESTLIST");

    }


}
