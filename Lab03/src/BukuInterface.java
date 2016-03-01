/**
 * Created by haluan on 3/1/16.
 */
public interface BukuInterface {
    public String addBuku(Buku buku);
    public String addStockBuku(String id, long stock);
    public Buku searchBuku(String id);
    public String pinjamBuku(String npm, String id);
    public String searchKategoriBuku(String kategori);
}
