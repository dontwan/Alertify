package dontwan.sample_alertify;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dontwan.alertify.Alertify;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alertifyNormal = (Button)findViewById(R.id.alertifyNormal);
        alertifyNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);

                Alertify alert = Alertify.make(view, Alertify.LAYOUT_ALERTIFY_SUCCEED, Alertify.LENGTH_INFINITE);
                //alert.setIcon(R.drawable.notification_succeed);
                alert.setPosition(Alertify.POSITION_TOP);
                alert.setIcon(R.drawable.notification_error);
                alert.setSwipeDismiss(true);
                alert.setIconAnimation(Alertify.ANIMATION_PULSE);
                alert.show();
            }
        });

        Log.i("LENGTH", String.valueOf(Snackbar.LENGTH_LONG));



    }
}
