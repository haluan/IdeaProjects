import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by haluan on 3/1/16.
 */
public class Library implements BukuInterface, MahasiswaInterface {
    private ArrayList<Mahasiswa> mahasiwas;
    private ArrayList<Buku> bukus;
    private HashMap<String, ArrayList<String>> peminjaman;

    public Library(){
        setMahasiwas(new ArrayList<Mahasiswa>());
        setBukus(new ArrayList<Buku>());
        peminjaman = new HashMap<String, ArrayList<String>>();
    }



    @Override
    public String addBuku(Buku buku) {
        Buku b = searchBuku(buku.getId());
        if (b == null){
            bukus.add(buku);
            return "buku "+buku.getId()+" dimasukkan";
        }
        return "buku "+buku.getId()+" gagal dimasukkan";
    }

    @Override
    public String addStockBuku(String id, long stock) {
        Buku b = searchBuku(id);
        if(b != null){
            b.setStok(stock+b.getStok());
            return "stok "+b.getId()+" = "+b.getStok();
        }
        return  "buku tidak ditemukan";
    }

    @Override
    public Buku searchBuku(String id) {
        for(Buku b : bukus){
            if(b.getId().equals(id)){
                return b;
            }
        }
        return null;
    }

    public String searchKategoriBuku(String kategori){
        long jumlahStokKategori = 0;
        boolean found = false;
        for(Buku b : bukus){
            if(b.getKategori().equalsIgnoreCase(kategori)){
                found = true;
                jumlahStokKategori += b.getStok();
            }
        }
        if(found) {
            return "stok " + kategori+ " " + jumlahStokKategori;
        }else{
            return "kategori tidak ada";
        }
    }

    @Override
    public String pinjamBuku(String npm, String id) {
        Mahasiswa m = searchMahasiswa(npm);
        Buku b = searchBuku(id);
        if(b.getStok() > 0){
            b.setStok(b.getStok()-1);
            ArrayList<String> listJudulPinjam = peminjaman.get(npm);
            if(listJudulPinjam == null){
                listJudulPinjam = new ArrayList<String>();
            }
            listJudulPinjam.add(b.getNama());
            peminjaman.put(npm, listJudulPinjam);
            return "mahasiswa "+npm+" meminjam "+id;
        }
        return "peminjaman gagal";
    }


    @Override
    public String addMahasiswa(Mahasiswa mahasiswa) {
        Mahasiswa m = searchMahasiswa(mahasiswa.getId());
        if(m == null){
            mahasiwas.add(mahasiswa);
            return "mahasiswa "+mahasiswa.getId()+" dimasukkan";
        }
        return "mahasiswa gagal dimasukkan";
    }

    @Override
    public void cekMahasiswa(String npm) {
        Mahasiswa mahasiswa = searchMahasiswa(npm);
        ArrayList<String> listBuku = peminjaman.get(npm);
        String juduls = "";
        int counter = 0;
        for(String judul : listBuku){
           if(counter < listBuku.size()-1){
               juduls += judul+";";
           }else{
               juduls+=judul;
           }
           counter++;
        }
        System.out.println(mahasiswa.getId()+";"+mahasiswa.getNama()+";"+juduls);
    }

    @Override
    public Mahasiswa searchMahasiswa(String npm) {
        for(Mahasiswa m : mahasiwas){
            if(m.getId().equals(npm)){
                return m;
            }
        }
        return null;
    }

    public ArrayList<Mahasiswa> getMahasiwas() {
        return mahasiwas;
    }

    public void setMahasiwas(ArrayList<Mahasiswa> mahasiwas) {
        this.mahasiwas = mahasiwas;
    }

    public ArrayList<Buku> getBukus() {
        return bukus;
    }

    public void setBukus(ArrayList<Buku> bukus) {
        this.bukus = bukus;
    }
}
