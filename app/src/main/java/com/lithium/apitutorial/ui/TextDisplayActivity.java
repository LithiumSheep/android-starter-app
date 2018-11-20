package com.lithium.apitutorial.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lithium.apitutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import timber.log.Timber;


public class TextDisplayActivity extends AppCompatActivity {

    @BindView(R.id.edit_text)
    EditText editText;

    @BindView(R.id.submit_button)
    Button submitButton;

    @BindView(R.id.text_display)
    TextView display;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);
        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.edit_text)
    void onFoo() {
        Timber.d(editText.getText().toString());
    }

    @OnClick(R.id.submit_button)
    void onSubmitClicked() {
        String entry = editText.getText().toString();
        String transform = entry + "!!!1!@!11";
        display.setText(transform);
    }
}
