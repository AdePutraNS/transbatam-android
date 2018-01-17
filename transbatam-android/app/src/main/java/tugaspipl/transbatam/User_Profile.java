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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Adepu on 06/12/2017.
 */

public class User_Profile extends AppCompatActivity{
    Intent intent;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    SharedPreferences sharedpreferences;
    final Activity activity = this;
    private NetworkImageView imageView;
    private ImageLoader imageLoader;
    TextView set_nama_lengkap, set_username,set_email, set_no_telp;
    public final static String TAG_URL = "https://transbatam.000webhostapp.com/assets/profile_pic/";
    String profile_pic_url, nama_lengkap, username, email, no_telp;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        set_nama_lengkap = (TextView) findViewById(R.id.nama_lengkap);
        set_email = (TextView) findViewById(R.id.email);
        set_no_telp = (TextView) findViewById(R.id.no_telp);
        set_username = (TextView) findViewById(R.id.username);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (NetworkImageView) findViewById(R.id.profile_pic);
        setSupportActionBar(toolbar);
        sharedpreferences = getSharedPreferences(User_Login.my_shared_preferences, Context.MODE_PRIVATE);
        // Menginisiasi  NavigationView
        profile_pic_url = sharedpreferences.getString("profile_pic_url", null);
        email = sharedpreferences.getString("email", null);
        nama_lengkap = sharedpreferences.getString("nama_lengkap", null);
        no_telp = sharedpreferences.getString("no_telp", null);
        username = sharedpreferences.getString("username", null);
        Toast.makeText(getApplicationContext(), profile_pic_url,Toast.LENGTH_SHORT).show();
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        set_nama_lengkap.setText(nama_lengkap);
        set_username.setText("Username : " + username);
        set_email.setText("E-Mail : " + email);
        set_no_telp.setText("+62 "+no_telp);
        loadImage();
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
                        intent = new Intent(User_Profile.this, User_Dashboard.class);
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
                        Toast.makeText(getApplicationContext(),"Anda Sedang Berada di Profil",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation4:
                        intent = new Intent(User_Profile.this, User_Saldo.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation5:
                        intent = new Intent(User_Profile.this, User_Jadwal.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation6:
                        intent = new Intent(User_Profile.this, User_Riwayat.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation7:
                        intent = new Intent(User_Profile.this, User_Setting.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation8:
                        intent = new Intent(User_Profile.this, User_About.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigation9:
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(User_Login.session_status, false);
                        editor.putString("username", null);
                        editor.commit();
                        Intent intent = new Intent(User_Profile.this, User_Login.class);
                        finish();
                        startActivity(intent);
                        return true;
                    case R.id.navigationtambahan:
                        intent = new Intent(User_Profile.this, User_Riwayat_Perjalanan.class);
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
    private void loadImage(){
        String url = TAG_URL + profile_pic_url;
        if(url.equals("")){
            Toast.makeText(this,"Tolong Masukkan URL",Toast.LENGTH_LONG).show();
            return;
        }

        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.image, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
    }
}
