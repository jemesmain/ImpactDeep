package fr.bicyclopresto.impactdeep;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_detect extends Fragment implements SensorEventListener {


    public Fragment_detect() {
        // Required empty public constructor
    }

    int i = 0;

    //declaration des variables pour le sensor
    private SensorManager senSensorManager_G;
    private Sensor senAccelerometer;

    //declaration variable pour le GPS
    private LocationManager locationManager;
    private Location location;
    private LocationListener locationListener;

    // constant G pour la terre
    public static final float GRAVITY_EARTH = 9.80665f;
    // coordonnées du sensor acceleromètre
    Float X, Y, Z;
    // résultat du calcul de la gravité
    Double G;


    SensorEventListener eventListen;


    private Handler myHandler;
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            // Code à éxécuter de façon périodique
            //MainActivity.G_timer=MainActivity.G;
            if (MainActivity.switch_detect.isChecked()) {
                String message_i = "temps: " + i;
                MainActivity.text_time.setText(message_i);

                //get_g(event);
                //SensorEvent  event;
                //X = event.values[0];
                //Y = event.values[1];
                //Z = event.values[2];

                //G = Math.sqrt(X * X + Y * Y + Z * Z) / GRAVITY_EARTH;
                //G_text =
                //G = Double.valueOf(Math.round(G * 1000)) / 1000;

                String message_Grun = "Grun: " + G;
                MainActivity.text_Grun.setText(message_Grun);


                //String toast_message ="G runnable: " + G;
                //Toast msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
                //msg.show();
                i++;
            }
            //le post delay ci dessous fixe les intervalles de répétition du code de run()
            myHandler.postDelayed(this,1000);
        }
    };

    //jemesmain lorque l'activité rentre en pause on arrête les appel à my handler myrunnable...

    public void onPause() {
        super.onPause();
        if(myHandler != null)
            myHandler.removeCallbacks(myRunnable); // On arrete le callback
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detect, container, false);
        // permet de construire le contenu de l'onglet adequat du fragment
        View rootView = inflater.inflate(R.layout.fragment_detect, container, false);

        //assign switch
        MainActivity.switch_detect = (Switch) rootView.findViewById(R.id.switch_detect);

        //assign textview
        MainActivity.text_time = (TextView) rootView.findViewById(R.id.time_text);
        MainActivity.text_Grun = (TextView) rootView.findViewById(R.id.Grun_text);
        MainActivity.text_Gchange = (TextView) rootView.findViewById(R.id.Gchange_text);

        //declaration pour l'accéléromètre
        senSensorManager_G = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        // sensor type linear acceleration
        senAccelerometer = senSensorManager_G.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        //senAccelerometer = senSensorManager_G.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // declaration pour le GPS
        // appel depuis un fragment
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //register sensor listener
        //senSensorManager_G.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        //jemesmain: specification du delay en microseconde ici 1/10ieme de seconde
        //.registerListener( this, senAccelerometer, senSensorManager_G.SENSOR_DELAY_NORMAL);
        senSensorManager_G.registerListener((SensorEventListener) this, senAccelerometer, 100000);
//SensorEventListener
//Sensor
// int
                //senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_GAME);
        //analyzer = new StepAnalyzer(getApplicationContext());


        //jemesmain appel périodique à my runnable
        myHandler = new Handler();
        myHandler.postDelayed(myRunnable, 1000); // on redemande toute les 500ms

        return rootView;
    }


//cf implements SensorEventListener, LocationListener pour faire un override
    @Override
    public void onSensorChanged(SensorEvent event) {
        // lorsque le sensor change on récupère les valeur de l'accéléromètre dans les 3 axes
        X = event.values[0];
        Y = event.values[1];
        Z = event.values[2];

        G = Math.sqrt(X * X + Y * Y + Z * Z) / GRAVITY_EARTH;
        //G_text =
        G = Double.valueOf(Math.round(G * 1000)) / 1000;

        String message_Gchange = "Gchange: " + G;
        MainActivity.text_Gchange.setText(message_Gchange);

        //String toast_message ="G sensor change: " + G;
        //Toast msg = Toast.makeText(getContext(), toast_message, Toast.LENGTH_SHORT);
        //msg.show();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // lié à l'accélérometre implement sensoreventlistener
        //not in use
    }

}
