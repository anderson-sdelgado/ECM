package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MsgNoteiroActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_noteiro);

        Button buttonOkMsgNoteiro = (Button) findViewById(R.id.buttonOkMsgNoteiro);
        Button buttonCancMsgNoteiro = (Button) findViewById(R.id.buttonCancMsgNoteiro);

        buttonOkMsgNoteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(MsgNoteiroActivity.this, NoteiroActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancMsgNoteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(MsgNoteiroActivity.this, MenuInicialApontActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}
