/**
 * Created by haluan on 3/1/16.
 */
public class Buku {
    private String id;
    private String nama;
    private String kategori;
    private long stok;

    public Buku(){}
    public Buku(String id, String nama, String kategori, long stok){
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public long getStok() {
        return stok;
    }

    public void setStok(long stok) {
        this.stok = stok;
    }
}
