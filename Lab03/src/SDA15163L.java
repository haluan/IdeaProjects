import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by haluan on 3/1/16.
 */
public class SDA15163L {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readString = "";
        String firstCommandOne = "", firstCommand = "";
        Library library = new Library();
        Mahasiswa mahasiswa = new Mahasiswa();
        Buku buku = new Buku();
        StringTokenizer token;

        while(null != (readString = reader.readLine())){
            token = new StringTokenizer(readString, ";");
            firstCommandOne = token.nextToken();
            firstCommand = token.nextToken();
            if(("add".equals(firstCommandOne))){
                if(firstCommand.equals("buku")){
                    buku = new Buku();
                    buku.setId(token.nextToken());
                    buku.setNama(token.nextToken());
                    buku.setKategori(token.nextToken());
                    buku.setStok(Long.parseLong(token.nextToken()));
                    System.out.println(library.addBuku(buku));

                }else if(firstCommand.equals("mhs")){
                    mahasiswa = new Mahasiswa();
                    mahasiswa.setId(Long.parseLong(token.nextToken()));
                    mahasiswa.setNama(token.nextToken());
                    System.out.println(library.addMahasiswa(mahasiswa));
                }else if(firstCommand.equals("buku")){
                    buku = library.searchBuku(token.nextToken());
                    if(buku != null){
                        System.out.println(buku.getId()+";"+buku.getNama()+";"+buku.getKategori()+";"+buku.getStok());
                    }
                }else if("stok".equals(firstCommand)){
                    System.out.println(library.addStockBuku(token.nextToken(), Long.parseLong(token.nextToken())));
                }
            }else if("cek".equals(firstCommandOne)) {
                if("buku".equals(firstCommand)){
                    buku = library.searchBuku(token.nextToken());
                    if(buku != null){
                        System.out.println(buku.getId()+";"+buku.getNama()+";"+buku.getKategori()+";"+buku.getStok());
                    }
                }else if("mhs".equals(firstCommand)){
                    library.cekMahasiswa(Long.parseLong(token.nextToken()));
                }

            }else if("pinjam".equals(firstCommandOne)){
                System.out.println(library.pinjamBuku(Long.parseLong(firstCommand), token.nextToken()));
            }else if("stok".equals(firstCommandOne)){
                System.out.println(library.searchKategoriBuku(token.nextToken()));
            }
        }

    }

    public static class Library implements BukuInterface, MahasiswaInterface {
        private HashSet<Mahasiswa> mahasiwas;
        private HashSet<Buku> bukus;
        private HashMap<Long, ArrayList<String>> peminjaman;

        public Library(){
            setMahasiwas(new HashSet<Mahasiswa>());
            setBukus(new HashSet<Buku>());
            peminjaman = new HashMap<Long, ArrayList<String>>();
        }



        @Override
        public String addBuku(Buku buku) {
            Buku b = searchBuku(buku.getId());
            if (b == null){
                bukus.add(buku);
                return "buku "+buku.getId()+" dimasukkan";
            }
            return "buku sudah ada";
        }

        @Override
        public String addStockBuku(String id, long stock) {
            Buku b = searchBuku(id);
            if(b != null){
                b.setStok(stock+b.getStok());
                return "stok "+b.getId()+" menjadi "+b.getStok();
            }
            return  "";
        }

        @Override
        public Buku searchBuku(String id) {
            for(Buku b : bukus){
                if(id.equals(b.getId())){
                    return b;
                }
            }
            return null;
        }

        public String searchKategoriBuku(String kategori){
            long jumlahStokKategori = 0;
            boolean found = false;
            for(Buku b : bukus){
                if(kategori.equals(b.getKategori())){
                    found = true;
                    jumlahStokKategori += b.getStok();
                }
            }
            if(found) {
                return "stok " + kategori+ " = " + jumlahStokKategori;
            }else{
                return "kategori tidak ada";
            }
        }

        @Override
        public String pinjamBuku(long npm, String id) {
            Mahasiswa m = searchMahasiswa(npm);
            Buku b = searchBuku(id);
            if(b != null && b.getStok() > 0){
                b.setStok(b.getStok()-1);
                ArrayList<String> listJudulPinjam = peminjaman.get(npm);
                if(listJudulPinjam == null){
                    listJudulPinjam = new ArrayList<String>();
                }
                listJudulPinjam.add(b.getNama());
                peminjaman.put(npm, listJudulPinjam);
                return "mahasiswa "+npm+" meminjam "+id;
            }
            return "buku habis";
        }


        @Override
        public String addMahasiswa(Mahasiswa mahasiswa) {
            Mahasiswa m = searchMahasiswa(mahasiswa.getId());
            if(m == null){
                mahasiwas.add(mahasiswa);
                return "mahasiswa "+mahasiswa.getId()+" dimasukkan";
            }
            return "mahasiswa sudah ada";
        }

        @Override
        public void cekMahasiswa(long npm) {
            Mahasiswa mahasiswa = searchMahasiswa(npm);
            ArrayList<String> listBuku = peminjaman.get(npm);
            String juduls = "";
            int counter = 0;
            if(listBuku != null){
                for(String judul : listBuku){
                    if(counter < listBuku.size()-1){
                        juduls += judul+";";
                    }else{
                        juduls+=judul;
                    }
                    counter++;
                }
                System.out.println(mahasiswa.getId()+";"+mahasiswa.getNama()+";"+juduls);
            }else{
                System.out.println(mahasiswa.getId()+";"+mahasiswa.getNama());
            }
        }

        @Override
        public Mahasiswa searchMahasiswa(long npm) {
            for(Mahasiswa m : mahasiwas){
                if(m.getId() == npm){
                    return m;
                }
            }
            return null;
        }

        public HashSet<Mahasiswa> getMahasiwas() {
            return mahasiwas;
        }

        public void setMahasiwas(HashSet<Mahasiswa> mahasiwas) {
            this.mahasiwas = mahasiwas;
        }

        public HashSet<Buku> getBukus() {
            return bukus;
        }

        public void setBukus(HashSet<Buku> bukus) {
            this.bukus = bukus;
        }
    }

    public interface MahasiswaInterface {
        public String addMahasiswa(Mahasiswa mahasiswa);
        public void cekMahasiswa(long npm);
        public Mahasiswa searchMahasiswa(long npm);
    }

    public interface BukuInterface {
        public String addBuku(Buku buku);
        public String addStockBuku(String id, long stock);
        public Buku searchBuku(String id);
        public String pinjamBuku(long npm, String id);
        public String searchKategoriBuku(String kategori);
    }

    public static class Mahasiswa {
        private long id;
        private String nama;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }
    }

    public static class Buku {
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


}
