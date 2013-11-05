package com.maps;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maps.model.Shop;


public class MapsActivity extends Activity {

    //Тег для логов
    private static final String TAG = MapsActivity.class.getSimpleName();

    //Координаты города
    private static final LatLng LOCATION_VLADIVOSTOK = new LatLng(43.134019, 131.928379);

    //Карта
    private GoogleMap googleMap;
    //Фрагмент карты
    private MapFragment mapFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        googleMap = mapFragment.getMap();

        showNeededLocation();
        addMarkers();
    }

    //Метод показывает нужное местоположение: Владивосток
    private void showNeededLocation() {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_VLADIVOSTOK, 12);
        googleMap.animateCamera(update);
    }

    //Добавить маркеры на карту
    private void addMarkers() {
        Shop center = new Shop("Центральный", 43.116079, 131.886067, BitmapFactory.decodeResource(this.getResources(), R.drawable.centr));
        Shop avangard = new Shop("Авангард", 43.111988, 131.917537, BitmapFactory.decodeResource(this.getResources(), R.drawable.avangard));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(center.getLatitude(), center.getLongitude()))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title(center.getName()));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(avangard.getLatitude(), avangard.getLongitude()))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.shop)))
                .title(avangard.getName()));

        googleMap.setInfoWindowAdapter(new MyInfoView());
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Адаптер для вида на карте
    private class MyInfoView implements GoogleMap.InfoWindowAdapter {

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View myContentView = getLayoutInflater().inflate(R.layout.custom_marker, null);
            ImageView imageView = (ImageView) myContentView.findViewById(R.id.centr_image_view);
            TextView titleView = (TextView) myContentView.findViewById(R.id.title_text_view);
            titleView.setText(marker.getTitle());

            if (marker.getTitle().equalsIgnoreCase("Центральный"))
                imageView.setImageBitmap(BitmapFactory.decodeResource(MapsActivity.this.getResources(), R.drawable.centr));
            else if (marker.getTitle().equalsIgnoreCase("Авангард"))
                imageView.setImageBitmap(BitmapFactory.decodeResource(MapsActivity.this.getResources(), R.drawable.avangard));

            return myContentView;
        }
    }
}
