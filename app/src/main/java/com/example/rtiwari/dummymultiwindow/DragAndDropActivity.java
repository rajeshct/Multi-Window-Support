package com.example.rtiwari.dummymultiwindow;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class DragAndDropActivity extends AppCompatActivity {

    public static String TAG = DragAndDropActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);
        final TextView dragAndDropView = (TextView) findViewById(R.id.dragAndDropView);
        dragAndDropView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText(TAG, dragAndDropView.getText());
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    //  DRAG_FLAG_GLOBAL: allows dragging of view outside of our app
                    view.startDragAndDrop(data, shadowBuilder, view, View.DRAG_FLAG_GLOBAL);
                    view.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    view.setVisibility(View.VISIBLE);
                    return false;
                }

            }
        });
    }
}
