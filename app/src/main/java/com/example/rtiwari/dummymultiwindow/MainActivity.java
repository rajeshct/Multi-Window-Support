package com.example.rtiwari.dummymultiwindow;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textView);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        linearLayout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        view.setBackgroundResource(R.drawable.bg_linear_layout_dragged);
                        view.setForeground(getDrawable(R.drawable.ic_drag_drop));
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        view.setBackgroundResource(R.drawable.bg_linear_layout);
                        view.setForeground(null);
                        break;

                    case DragEvent.ACTION_DROP:
                        ClipData clipData = dragEvent.getClipData();
                        if (clipData != null && clipData.getItemCount() > 0 &&
                                /* To make sure data from our app*/
                                clipData.getDescription().getLabel().equals(DragAndDropActivity.TAG)) {
                            textView.setText(clipData.getItemAt(0).getText());
                        }
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:
                        view.setBackgroundResource(R.drawable.bg_linear_layout);
                        view.setForeground(null);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        showToast("On Multi window mode changed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isInMultiWindowMode()) {
            showToast("OnPause Called in Multi-Window mode");
        } else {
            showToast("OnPause Called ");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isInMultiWindowMode()) {
            showToast("On Resume called in Multi-Window mode");
        } else {
            showToast("onResume Called");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("On start called");

    }

    @Override
    protected void onStop() {
        showToast("On Stop called ");
        super.onStop();
    }

    public void actionIntentExplicit(View view) {
        Intent intent = new Intent(MainActivity.this, DragAndDropActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void actionIntentExplicitAdjacent(View view) {
        Intent intent = new Intent(MainActivity.this, DragAndDropActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }

    public void actionIntentImplicit(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        // It doesn't work when we have a default intent chooser
        startActivity(intent);
    }

    public void actionIntentImplicitAdjacent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }

}
