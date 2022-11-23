package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MaterialButton btnRecord;
    MaterialButton btnStart;

    String nameFile;
    MediaRecorder mediaRecorder = new MediaRecorder();
    MediaPlayer mediaPlayer;

    boolean rec = false;
    boolean start = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameFile = getCacheDir().getAbsolutePath() + "audio.acc";

        btnRecord = findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(v -> {

            if (rec == false) {
                rec = true;
                btnRecord.setText("Parar");
                recordAudio();
            } else {
                rec = false;
                btnRecord.setText("Grabar");
                stopRecordAudio();
            }

        });

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {

            if (start == false){
                start = true;
                btnStart.setText("Parar");
                playAudio();
            } else {
                start = false;
                btnStart.setText("Reproducir");
                stopAudio();
            }


        });

    }

    private void recordAudio() {

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(nameFile);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    private void stopRecordAudio() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void playAudio(){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(nameFile);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void stopAudio(){
        mediaPlayer.stop();
        mediaPlayer = null;
    }
}