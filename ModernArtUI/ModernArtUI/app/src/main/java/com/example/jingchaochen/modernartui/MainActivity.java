package com.example.jingchaochen.modernartui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TextView> squares;
    int[] colors = {R.color.color0, R.color.color1, R.color.color2, R.color.color3,
            R.color.color4, R.color.color5, R.color.color6, R.color.color7, R.color.color8, R.color.color9};
    int squareMargin = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void createSquares() {

        RelativeLayout container = (RelativeLayout) findViewById(R.id.content_main);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        squares = new ArrayList<>(5);
        int containerWidth = container.getMeasuredWidth();
        int containerHeight = container.getMeasuredHeight() - seekBar.getMeasuredHeight();
        for (int i=0; i<5; i++) {
            TextView view = new TextView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(getWidth(containerWidth, i),
                    getHeight(containerHeight, i));
            params.leftMargin = getLeft(containerWidth, i);
            params.topMargin = getTop(containerHeight, i);
            squares.add(i, view);
            container.addView(view, params);
        }
        refreshColors(0);
    }

    private void refreshColors(int position) {
        for (int i = 0; i< 5; i++) {
            TextView view = squares.get(i);
            int start = getResources().getColor(colors[i]);
            int end = getResources().getColor(colors[i+5]);
            int red = Color.red(start) * (100 - position) / 100 + Color.red(end) * position / 100;
            int green = Color.green(start) * (100 - position) / 100 + Color.green(end) * position / 100;
            int blue = Color.blue(start) * (100 - position) / 100 + Color.blue(end) * position / 100;
            int color = Color.rgb(red, green, blue);
            view.setBackgroundColor(color);
        }
    }

    private int getLeft(int containerWidth, int index) {
        if (index == 0 || index == 3) {
            return 0;
        }
        return getWidth(containerWidth, 0) + 2 * squareMargin;
    }

    private int getTop(int containerHeight, int index) {
        switch (index) {
            case 0:
            case 1:
                return 0;
            case 2:
                return getHeight(containerHeight, index) + 2 * squareMargin;
            case 3:
                return getHeight(containerHeight, index) + 2 * squareMargin;
            default:
                return 2 * getHeight(containerHeight, index) + 4 * squareMargin;
        }
    }

    private int getWidth(int containerWidth, int index) {
        if (index == 0 || index == 3) {
            return (int)(containerWidth * 0.4) - squareMargin;
        }
        return (int)(containerWidth * 0.6) - squareMargin;
    }

    private int getHeight(int containerHeight, int index) {
        if (index == 0 || index == 3) {
            return containerHeight/2 - squareMargin;
        }
        return containerHeight/3 - squareMargin;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        createSquares();
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                Log.i("SEEKBAR", "progress: " + progress);
                refreshColors(progress);
            }
        });
    }
}
