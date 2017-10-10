package dontwan.sample_alertify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dontwan.alertify.Alertify;

public class MainActivity extends AppCompatActivity {

    private ViewGroup view;
    private Button alertifySimple, alertifyButton, alertifyIcon, alertifyCustom, alertifyPosition, alertifyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertifySimple = (Button)findViewById(R.id.alertifySimple);
        alertifyButton = (Button)findViewById(R.id.alertifyButton);
        alertifyIcon = (Button)findViewById(R.id.alertifyIcon);
        alertifyCustom = (Button)findViewById(R.id.alertifyCustom);
        alertifyPosition = (Button)findViewById(R.id.alertifyPosition);
        alertifyType = (Button)findViewById(R.id.alertifyType);

        view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);

        alertifySimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertify.make(view, Alertify.LAYOUT_NORMAL, Alertify.LENGTH_SHORT)
                        .setText("This is a Alertify")
                        .show();
            }
        });

        alertifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertify.make(view, Alertify.LAYOUT_WITH_BUTTON, Alertify.LENGTH_SHORT)
                        .setText("This is a Alertify")
                        .setButtonAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(), "Button has been clicked!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        alertifyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertify.make(view, Alertify.LAYOUT_WITH_ICON, Alertify.LENGTH_SHORT)
                        .setText("This is a Alertify")
                        .setIcon(R.drawable.notification_succeed)
                        .setIconAnimation(Alertify.ANIMATION_PULSE)
                        .show();
            }
        });

        alertifyCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertify alertify = Alertify.make(view, Alertify.LAYOUT_ALERTIFY_CUSTOM_EXAMPLE, Alertify.LENGTH_SHORT);
                final TextView tvCustom = (TextView) alertify.getView().findViewById(R.id.tvCustom);
                Button btnCustom = (Button) alertify.getView().findViewById(R.id.btnCustom);


                btnCustom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvCustom.setText("This is a custom Alertify");
                    }
                });
                alertify.show();
            }
        });

        alertifyPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertify.make(view, Alertify.LAYOUT_NORMAL, Alertify.LENGTH_SHORT)
                        .setText("This is a Alertify")
                        .setPosition(Alertify.POSITION_TOP)
                        //.setPosition(Alertify.POSITION_CENTER)
                        //.setPosition(Alertify.POSITION_BOTTOM)
                        .show();
            }
        });

        alertifyType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertify.make(view, Alertify.LAYOUT_ALERTIFY_DEFAULT, Alertify.LENGTH_SHORT)
                        //Alertify.make(view, Alertify.LAYOUT_ALERTIFY_SUCCEED, Alertify.LENGTH_SHORT)
                        //Alertify.make(view, Alertify.LAYOUT_ALERTIFY_ERROR, Alertify.LENGTH_SHORT)
                        .setTitle("This is a Alertify title")
                        .setSubTitle("This is a Alertify subtitle")
                        .setPosition(Alertify.POSITION_TOP)
                        .setIconAnimation(Alertify.ANIMATION_PULSE)
                        .show();
            }
        });

    }
}
