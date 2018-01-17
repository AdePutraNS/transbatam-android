package tugaspipl.transbatam;

/**
 * Created by Adepu on 16/12/2017.
 */

import java.util.ArrayList;

public class User_Riwayat_Model {
    private String kd_transaksi, tipe_transaksi, nama_koridor, nilai_transaksi, waktu;

    public User_Riwayat_Model() {
    }

    public User_Riwayat_Model(String kd_transaksi, String tipe_transaksi, String nama_koridor, String nilai_transaksi, String waktu) {
        this.kd_transaksi = kd_transaksi;
        this.tipe_transaksi = tipe_transaksi;
        this.nama_koridor = nama_koridor;
        this.nilai_transaksi = nilai_transaksi;
        this.waktu = waktu;
    }

    public void setData(String kd_transaksi, String tipe_transaksi, String nilai_transaksi, String waktu){
        this.kd_transaksi = kd_transaksi;
        this.tipe_transaksi = tipe_transaksi;
        this.nilai_transaksi = nilai_transaksi;
        this.waktu = waktu;
    }

    public String getKd_transaksi(){
        return kd_transaksi;
    }
    public String getTipe_transaksi(){
        return tipe_transaksi;
    }

    public String getNilai_transaksi(){
        return nilai_transaksi;
    }
    public String getWaktu(){
        return waktu;
    }
}