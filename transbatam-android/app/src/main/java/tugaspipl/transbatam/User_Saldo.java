package tugaspipl.transbatam;

import android.app.Activity;
import android.support.design.widget.NavigationView;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Adepu on 06/12/2017.
 */

public class User_Saldo extends AppCompatActivity{

    String tag_json_obj = "json_obj_req";
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Intent intent;
    TextView set_saldo;
    SharedPreferences sharedpreferences;
    final Activity activity = this;
    String saldo_skrg, username_skrg;
    public final static String TAG_SALDO = "saldo";
    public final static String TAG_USERNAME = "username";
    private String url = Konfigurasi.URL + "get_saldo";
    public static final String session_status = "session_status";
    Boolean session = false;
    int success;
    private static final String TAG_SUCCESS = "success";

    private static final String TAG_MESSAGE = "message";
    private static final String TAG = User_Saldo.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_saldo);
        set_saldo = (TextView) findViewById(R.id.saldo);
        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedpreferences = getSharedPreferences(User_Login.my_shared_preferences, Context.MODE_PRIVATE);

        username_skrg = sharedpreferences.getString(TAG_USERNAME, null);
        Toast.makeText(getApplicationContext(), saldo_skrg, Toast.LENGTH_LONG).show();



        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    Log.e(TAG, "Fetch Data Response: " + success);
                    // Check for error node in json
                    if (success == 1) {
                        String saldo = jObj.getString(TAG_SALDO);
                        Log.e("Sukses !", jObj.toString());
                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_SALDO, saldo);
                        saldo_skrg = saldo;
                        set_saldo.setText("Rp. " + saldo_skrg + ",00");
                        editor.commit();
                        Toast.makeText(getApplicationContext(), saldo, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username_skrg);

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
















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
                        intent = new Intent(User_Saldo.this, User_Dashboard.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation2:
//                        IntentIntegrator integrator = new IntentIntegrator(activity);
//                        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//                        integrator.setPrompt("Scan");
//                        integrator.setCameraId(0);
//                        integrator.setBeepEnabled(false);
//                        integrator.setBarcodeImageEnabled(false);
//                        integrator.initiateScan();
                        intent = new Intent(User_Saldo.this, User_Naikbus.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation3:
                        intent = new Intent(User_Saldo.this, User_Profile.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation4:
                        Toast.makeText(getApplicationContext(),"Anda Sedang Berada di Saldo",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation5:
                        intent = new Intent(User_Saldo.this, User_Jadwal.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation6:
                        intent = new Intent(User_Saldo.this, User_Riwayat.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation7:
                        intent = new Intent(User_Saldo.this, User_Setting.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation8:
                        intent = new Intent(User_Saldo.this, User_About.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation9:
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(User_Login.session_status, false);
                        editor.putString("username", null);
                        editor.commit();
                        Intent intent = new Intent(User_Saldo.this, User_Login.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigationtambahan:
                        intent = new Intent(User_Saldo.this, User_Riwayat_Perjalanan.class);
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
