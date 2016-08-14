package com.grapps.jhonny.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.grapps.jhonny.interactivestory.R;
import com.grapps.jhonny.interactivestory.model.Page;
import com.grapps.jhonny.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;
    private Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra("name");
        if (mName == null) {
            mName = "Friend";
        }
        Log.d(TAG + "mNames: " , mName);

        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2 = (Button) findViewById(R.id.choiceButton2);


        loadPage(0);
    }


    private void loadPage(int choice) {
        mCurrentPage = mStory.getPage(choice);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), mCurrentPage.getImageId(),null);
        mImageView.setImageDrawable(drawable);

        String pageText = mCurrentPage.getText();

        // Add the name if placeholder (%1$s) included. Won't replace if no place holder.
        pageText = String.format(pageText, mName );

        mTextView.setText(pageText);

        if ( mCurrentPage.isFinal() ) {

            mChoice1.setText("PLAY AGAIN");
            mChoice2.setVisibility(View.INVISIBLE);

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } else {

            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }
}
