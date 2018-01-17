package tugaspipl.transbatam;

/**
 * Created by Adepu on 16/12/2017.
 */

public class User_Riwayat_Perjalanan_Model {
    private String asal_tujuan, tipe_transaksi, nama_koridor, nilai_transaksi, waktu;

    public User_Riwayat_Perjalanan_Model() {
    }

    public User_Riwayat_Perjalanan_Model(String asal_tujuan, String tipe_transaksi, String nilai_transaksi, String waktu) {
        this.asal_tujuan = asal_tujuan;
        this.tipe_transaksi = tipe_transaksi;
        this.nilai_transaksi = nilai_transaksi;
        this.waktu = waktu;
    }

    public void setData(String asal_tujuan, String tipe_transaksi, String nilai_transaksi, String waktu){
        this.asal_tujuan = asal_tujuan;
        this.tipe_transaksi = tipe_transaksi;
        this.nilai_transaksi = nilai_transaksi;
        this.waktu = waktu;
    }

    public String getasal_tujuan(){
        return asal_tujuan;
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