package de.feisar.wordvalue;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CalcWordValue extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_word_value);

        final EditText eText;
        final TextView tv;
        final Button clrbtn;

        tv = (TextView)findViewById(R.id.wordvalue);
        eText = (EditText) findViewById(R.id.word);
        clrbtn = (Button) findViewById(R.id.clrbtn);

        eText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                int wordvalue = 0;
                for(int i=0; i<s.length(); i++) {
                    wordvalue += this.calcCharValue(s.charAt(i));
                }
                tv.setText(String.valueOf(wordvalue));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
            protected int calcCharValue(char a) {
                Character ch = new Character(a);
                if(ch >= 'A' && ch <= 'Z') { // a=1, b=2, c=3, ..., z=26
                    Character ca = new Character('A');
                    return ch.charValue() - ca.charValue() + 1;
                }
                if(ch >= 'a' && ch <= 'z') { // a=1, b=2, c=3, ..., z=26
                    Character cas = new Character('a');
                    return ch.charValue() - cas.charValue() + 1;
                }
                if(ch >= '1' && ch <= '9') { // 1=1, 2=2, 3=3
                    Character cn = new Character('0');
                    return ch.charValue() - cn.charValue();
                }
                switch (a) { // ä=27, ö=28, ü=29, ß=30
                    case 'ä':
                    case 'Ä':
                        return 27;
                    case 'ö':
                    case 'Ö':
                        return 28;
                    case 'ü':
                    case 'Ü':
                        return 29;
                    case 'ß':
                        return 30;
                    default:
                        return 0;
                }
            }
        });

        clrbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                eText.setText("");
                tv.setText("0");
            }
        });
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc_word_value, menu);
        return true;
    }
*/

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
