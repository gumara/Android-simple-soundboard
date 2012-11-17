package com.gadgetplus.soundboard;

import com.gadgetplus.soundboard.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
 
public class MainActivity extends Activity implements OnClickListener {
    
    String[] buttonLabels = {"Button0", "Button1", "Button2", "Button3", "Button4", "Button5", "Button6", "Button7", "Button8"};
    int[] rawMedia = {R.raw.sound0, R.raw.sound1, R.raw.sound2, R.raw.sound3, R.raw.sound4, R.raw.sound5, R.raw.sound6, R.raw.sound7, R.raw.sound8};
    public static final int ROWS = 3, COLUMNS = 3;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        TableLayout layout = new TableLayout(this);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        layout.setStretchAllColumns(true);
         
        for(int i=0; i<ROWS; i++) {
            TableRow row = new TableRow(this);
          
            for(int j=0; j<COLUMNS; j++) {
                Button b = new Button(this);
                b.setOnClickListener(this);
                b.setId(i*ROWS + j);
                b.setText(buttonLabels[b.getId()]);
           
                row.addView(b, new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f));
            }
          
            layout.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f));
        }
        super.setContentView(layout);
    }
     
    public void onClick(View view) {
        MediaPlayer player = MediaPlayer.create(this, rawMedia[view.getId()]);
        player.setLooping(false);
        player.start();
        player.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer player) {player.release();} });
    }
     
}