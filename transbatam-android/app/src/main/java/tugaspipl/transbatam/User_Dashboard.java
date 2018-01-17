    package tugaspipl.transbatam;

    import android.app.Activity;
    import android.support.design.widget.NavigationView;
    import android.support.v4.app.FragmentActivity;
    import android.support.v4.widget.DrawerLayout;
    import android.support.v7.app.ActionBarDrawerToggle;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.support.v7.widget.Toolbar;
    import android.util.Log;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.android.volley.Request;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.StringRequest;
    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.MapFragment;
    import com.google.android.gms.maps.OnMapReadyCallback;
    import com.google.android.gms.maps.SupportMapFragment;
    import com.google.android.gms.maps.model.BitmapDescriptorFactory;
    import com.google.android.gms.maps.model.CameraPosition;
    import com.google.android.gms.maps.model.LatLng;
    import com.google.android.gms.maps.model.Marker;
    import com.google.android.gms.maps.model.MarkerOptions;
    import com.google.zxing.integration.android.IntentIntegrator;
    import com.google.zxing.integration.android.IntentResult;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    /**
     * Created by Adepu on 06/12/2017.
     */

    public class User_Dashboard extends AppCompatActivity implements OnMapReadyCallback{
        private GoogleMap mMap;
        private Toolbar toolbar;
        private NavigationView navigationView;
        private DrawerLayout drawerLayout;
        Intent intent;
        final Activity activity = this;
        MarkerOptions markerOptions = new MarkerOptions();
        CameraPosition cameraPosition;
        LatLng center, latLng;
        String title;

        public static final String ID = "id";
        public static final String TITLE = "judul";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        String tag_json_obj = "json_obj_req";
        private String url = Konfigurasi.URL + "get_halte";

        SharedPreferences sharedpreferences;
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_dashboard);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            // Menginisiasi Toolbar dan mensetting sebagai actionbar
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            sharedpreferences = getSharedPreferences(User_Login.my_shared_preferences, Context.MODE_PRIVATE);
            // Menginisiasi  NavigationView

            navigationView = (NavigationView) findViewById(R.id.navigation_view);
            //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                // This method will trigger on item Click of navigation menu
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                    if(menuItem.isChecked()) menuItem.setChecked(false);
                    else menuItem.setChecked(true);
                    //Menutup  drawer item klik
                    drawerLayout.closeDrawers();
                    //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                    switch (menuItem.getItemId()){
                        // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                        //dengan intent activity
                        case R.id.navigation1:
                            Toast.makeText(getApplicationContext(),"Anda Sedang Berada di Dashboard",Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navigation2:
//                            IntentIntegrator integrator = new IntentIntegrator(activity);
//                            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//                            integrator.setPrompt("Scan");
//                            integrator.setCameraId(0);
//                            integrator.setBeepEnabled(false);
//                            integrator.setBarcodeImageEnabled(false);
//                            integrator.initiateScan();
                            intent = new Intent(User_Dashboard.this, User_Naikbus.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigation3:
                            intent = new Intent(User_Dashboard.this, User_Profile.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigation4:
                            intent = new Intent(User_Dashboard.this, User_Saldo.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigation5:
                            intent = new Intent(User_Dashboard.this, User_Jadwal.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigation6:
                            intent = new Intent(User_Dashboard.this, User_Riwayat.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigation7:
                            intent = new Intent(User_Dashboard.this, User_Setting.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigation8:
                            intent = new Intent(User_Dashboard.this, User_About.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigation9:
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean(User_Login.session_status, false);
                            editor.putString("username", null);
                            editor.commit();
                            Intent intent = new Intent(User_Dashboard.this, User_Login.class);
                            finish();
                            startActivity(intent);
                            return true;
                        case R.id.navigationtambahan:
                            intent = new Intent(User_Dashboard.this, User_Riwayat_Perjalanan.class);
                            finish();
                            startActivity(intent);
                            return true;
                        default:
                            Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                            return true;
                    }
                }
            });
            // Menginisasi Drawer Layout dan ActionBarToggle
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
                @Override
                public void onDrawerClosed(View drawerView) {
                    // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                    super.onDrawerClosed(drawerView);
                }
                @Override
                public void onDrawerOpened(View drawerView) {
                    //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                    super.onDrawerOpened(drawerView);
                }
            };
            //Mensetting actionbarToggle untuk drawer layout
            drawerLayout.setDrawerListener(actionBarDrawerToggle);
            //memanggil synstate
            actionBarDrawerToggle.syncState();
        }

        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            center = new LatLng(1.1288869299708721, 104.04005811677246);
            cameraPosition = new CameraPosition.Builder().target(center).zoom(14).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            getMarkers();
        }
        private void addMarker(LatLng latlng, final String title) {
            markerOptions.position(latlng);
            markerOptions.title(title);
            mMap.addMarker(markerOptions);

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Fungsi get JSON marker
        private void getMarkers() {
            StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e("Response: ", response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        String getObject = jObj.getString("halte");
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            title = jsonObject.getString(TITLE);
                            latLng = new LatLng(Double.parseDouble(jsonObject.getString(LAT)), Double.parseDouble(jsonObject.getString(LNG)));

                            // Menambah data marker untuk di tampilkan ke google map
                            addMarker(latLng, title);
                        }

                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error: ", error.getMessage());
                    Toast.makeText(User_Dashboard.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if(result != null){
                if(result.getContents() == null){
                    Toast.makeText(this, "Pemindaian di batalkan", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
