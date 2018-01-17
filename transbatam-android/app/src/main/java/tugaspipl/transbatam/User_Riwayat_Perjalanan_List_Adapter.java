package tugaspipl.transbatam;

/**
 * Created by Adepu on 16/12/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class User_Riwayat_Perjalanan_List_Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<User_Riwayat_Perjalanan_Model> m_riwayat;

    public User_Riwayat_Perjalanan_List_Adapter(Activity activity, List<User_Riwayat_Perjalanan_Model> m_riwayat) {
        this.activity = activity;
        this.m_riwayat = m_riwayat;
    }

    @Override
    public int getCount() {
        return m_riwayat.size();
    }

    @Override
    public Object getItem(int location) {
        return m_riwayat.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_user_riwayat_list_row, null);



        TextView asal_tujuan = (TextView) convertView.findViewById(R.id.kd_transaksi);
        TextView nilai_transaksi = (TextView) convertView.findViewById(R.id.nilai_transaksi);
        TextView tipe_transaksi = (TextView) convertView.findViewById(R.id.tipe_transaksi);
        TextView waktu = (TextView) convertView.findViewById(R.id.waktu);

        // getting movie data for the row
        User_Riwayat_Perjalanan_Model m = m_riwayat.get(position);
        asal_tujuan.setText(m.getasal_tujuan());
        nilai_transaksi.setText(m.getNilai_transaksi());
        tipe_transaksi.setText(m.getTipe_transaksi());
        waktu.setText(m.getWaktu());
        return convertView;
    }

}