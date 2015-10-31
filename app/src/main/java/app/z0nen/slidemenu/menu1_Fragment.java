package app.z0nen.slidemenu;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

public class menu1_Fragment extends Fragment {
    Chronometer mChronometer;
    int loop = 0;
    int CB = 1;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    int upgC1 = 100;
    int upgC2 = 500; //5000 25000
    int upgC3 = 1000; //10000 50000
    int upgC4 = 250000;
    double startTime;
    double millis;
    int cookies = 0;

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);
        setButton();
        setClick();
        return rootview;
    }

    public void setButton() {
        button1 = (Button) rootview.findViewById(R.id.upgbutton);
        button2 = (Button) rootview.findViewById(R.id.upgbutton1);
        button3 = (Button) rootview.findViewById(R.id.upgbutton2);
        button4 = (Button) rootview.findViewById(R.id.upgbutton3);
    }

    Button button;
    public void setClick() {


        mChronometer = (Chronometer) rootview.findViewById(R.id.aChronometer);

        button = (Button) rootview.findViewById(R.id.stopWatch);
        button.setOnClickListener(mStartListener);

    }
    public void cookieCalc(){
        if (Math.abs((5-(millis/1000))) <= 0.005)
            cookies += 100*CB;
        else if(Math.abs((5-(millis/1000))) <= 0.0125)
            cookies += 50*CB;
        else if(Math.abs((5-(millis/1000))) <= 0.025)
            cookies += 25*CB;
        else if(Math.abs((5-(millis/1000))) <= 0.05)
            cookies += 10*CB;
        else if(Math.abs((5-(millis/1000))) <= 0.125)
            cookies += 5*CB;
        else if(Math.abs((5-(millis/1000))) <= .5)
            cookies += 3*CB;
    }
    public void cookieStr(){
        TextView myText2 = (TextView) rootview.findViewById(R.id.editText);
        if(Math.abs((5-(millis/1000))) <= 0.005)
            myText2.setText("Unreal! X100 Bonus");
        else if(Math.abs((5-(millis/1000))) <= 0.0125)
            myText2.setText("Wow! X50 Bonus");
        else if(Math.abs((5-(millis/1000))) <= 0.025)
            myText2.setText("Nice! X25 Bonus");
        else if(Math.abs((5-(millis/1000))) <= 0.05)
            myText2.setText("Good! X10 Bonus");
        else if(Math.abs((5-(millis/1000))) <= 0.125)
            myText2.setText("Ok! X5 Bonus");
        else if(Math.abs((5-(millis/1000))) <= .5)
            myText2.setText("Keep Trying! X3 Bonus");
    }
public void cookieChange(){
    if(cookies >= upgC1){
        button1.setEnabled(false);
    } else {
        button1.setEnabled(true);
    }
    if(cookies >= upgC2){
        button2.setEnabled(false);
    } else {
        button2.setEnabled(true);
    }
    if(cookies >= upgC3) {
        button3.setEnabled(false);
    }
    else {
        button3.setEnabled(true);
    }
    if(cookies >= upgC4){
        button4.setEnabled(false);
    } else {
        button4.setEnabled(true);
    }
}
    View.OnClickListener mStartListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (loop == 0) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();
                startTime = System.currentTimeMillis();
                loop++;
            } else if (loop == 1) {
                millis = System.currentTimeMillis()-startTime;
                mChronometer.stop();
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Your Time is: " + (millis/1000);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM, 0, 25);
                toast.show();
                cookieStr();
                loop++;
            } else if (loop == 2) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                cookieCalc();
                TextView myText = (TextView) rootview.findViewById(R.id.editText2);
                myText.setText("= " + cookies);
                TextView myText2 = (TextView) rootview.findViewById(R.id.editText);
                myText2.setText("Stop Watch as Close as Possible to 5");
                cookieChange();
                loop = 0;
            }
        }
    };
}
