package fr.bicyclopresto.impactdeep;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_home extends Fragment {


    public Fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//DECLARATION DES VARIABLES

        ImageButton btnHomeMenu = (ImageButton) rootView.findViewById(R.id.btn_home_menu);

        ImageButton btnHomeFingerprint = (ImageButton) rootView.findViewById(R.id.btn_home_fingerprint);
        ImageButton btnHomePhone = (ImageButton) rootView.findViewById(R.id.btn_home_phone);
        ImageButton btnHomeDetect = (ImageButton) rootView.findViewById(R.id.btn_home_run);

        ImageButton btnHomeShare = (ImageButton) rootView.findViewById(R.id.btn_home_share);
        ImageButton btnHomeJoin = (ImageButton) rootView.findViewById(R.id.btn_home_join);

        btnHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open drawer
                //drawer.openDrawer(GravityCompat.START);
                //@Override
                //public void onClick(View v) {
                ((MainActivity) getActivity()).ouvrirDrawer();
                //}
                //Toast.makeText(getActivity().getApplicationContext(), "Merci d'appuyer sur le bouton en haut à gauche "
                //                , Toast.LENGTH_SHORT).show();


            }
        });

        btnHomeFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_profil fragment_profil = new Fragment_profil();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_profil).commit();


            }
        });

        btnHomePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_phone fragment_phone = new Fragment_phone();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_phone).commit();


            }
        });

        btnHomeDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_detect fragment_detect = new Fragment_detect();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_detect).commit();


            }
        });

        btnHomeShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_share fragment_share = new Fragment_share();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_share).commit();


            }
        });

        btnHomeJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_join fragment_join = new Fragment_join();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_join).commit();


            }
        });





        return rootView;
    }

}
