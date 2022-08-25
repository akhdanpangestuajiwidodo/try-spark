package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Transaksi {
    @JsonAlias({"transaksi_id", "transaksiid"})
    private String transaksIid;

    @JsonAlias({"idpengirim", "id_pengirim"})
    private String idPengirim;

    @JsonAlias({"idpenerima", "id_penerima"})
    private String idPenerima;

    @JsonAlias({"jumlahuang", "jumlah_uang"})
    private int jumlahUang;

    @JsonAlias({"tanggaltransaksi", "tanggal_transaksi"})
    private String tanggalTransaksi;
}
