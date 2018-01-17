package tugaspipl.transbatam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adepu on 06/12/2017.
 */

public class User_Riwayat_Perjalanan extends AppCompatActivity{
    private ListView listView;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private User_Riwayat_Perjalanan_List_Adapter adapter;
    private List<User_Riwayat_Perjalanan_Model> trx_list = new ArrayList<User_Riwayat_Perjalanan_Model>();
    private DrawerLayout drawerLayout;
    Intent intent;
    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedpreferences;
    final Activity activity = this;
    private String url = Konfigurasi.URL + "riwayat_perjalanan";
    private static final String TAG = User_Riwayat_Perjalanan.class.getSimpleName();
    int success;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_riwayat);
        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.daftar);
        adapter = new User_Riwayat_Perjalanan_List_Adapter(this, trx_list);
        listView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        sharedpreferences = getSharedPreferences(User_Login.my_shared_preferences, Context.MODE_PRIVATE);
        // Menginisiasi  NavigationView
        final String username_skrg = sharedpreferences.getString("username", null);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        url = url+"?username="+username_skrg;
        JsonArrayRequest strReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e(TAG, "Login Response: " + response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        User_Riwayat_Perjalanan_Model m_riwayat = new User_Riwayat_Perjalanan_Model();
                        JSONObject jObj = response.getJSONObject(i);
                        // Check for error node in json
                        String asal_tujuan = jObj.getString("asal_tujuan");
                        String tipe_transaksi = jObj.getString("tipe_transaksi");
                        String nilai_transaksi = jObj.getString("nilai_transaksi");
                        String waktu = jObj.getString("waktu");
                        m_riwayat.setData(asal_tujuan, tipe_transaksi, nilai_transaksi, waktu);
                        trx_list.add(m_riwayat);
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
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
                        intent = new Intent(User_Riwayat_Perjalanan.this, User_Dashboard.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation2:
                        IntentIntegrator integrator = new IntentIntegrator(activity);
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                        integrator.setPrompt("Scan");
                        integrator.setCameraId(0);
                        integrator.setBeepEnabled(false);
                        integrator.setBarcodeImageEnabled(false);
                        integrator.initiateScan();
//                        intent = new Intent(User_About.this, User_Naikbus.class);
//                        finish();
//                        startActivity(intent);
                        return true;
                    case R.id.navigation3:
                        intent = new Intent(User_Riwayat_Perjalanan.this, User_Profile.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation4:
                        intent = new Intent(User_Riwayat_Perjalanan.this, User_Saldo.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation5:
                        intent = new Intent(User_Riwayat_Perjalanan.this, User_Jadwal.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation6:
                        Toast.makeText(getApplicationContext(),"Anda Sedang Berada di Riwayat",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation7:
                        intent = new Intent(User_Riwayat_Perjalanan.this, User_Setting.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation8:
                        intent = new Intent(User_Riwayat_Perjalanan.this, User_About.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation9:
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(User_Login.session_status, false);
                        editor.putString("username", null);
                        editor.commit();
                        Intent intent = new Intent(User_Riwayat_Perjalanan.this, User_Login.class);
                        finish();
                        startActivity(intent);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigationtambahan:
                        Toast.makeText(getApplicationContext(),"Anda Sedang Berada di Riwayat Perjalanan!",Toast.LENGTH_SHORT).show();
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
