/**
 * Created by haluan on 3/1/16.
 */
public class Main {
    public static void main(String args[]){
        Library library = new Library();
        Mahasiswa mahasiswa;
        Buku buku;
        String message = "";

        //1. Add buku
        buku = new Buku();
        buku.setId("B01");
        buku.setNama("Buku FHM");
        buku.setKategori("Majalah");
        buku.setStok(90);
        message = library.addBuku(buku);
        System.out.println(message);


        buku = new Buku();
        buku.setId("B02");
        buku.setNama("Buku PTGX");
        buku.setKategori("Majalah");
        buku.setStok(9);
        message = library.addBuku(buku);
        System.out.println(message);

        //2. Add mahasiswa
        mahasiswa = new Mahasiswa();
        mahasiswa.setId("9090");
        mahasiswa.setNama("Jono");
        message = library.addMahasiswa(mahasiswa);
        System.out.println(message);

        //3. Add stock
        message = library.addStockBuku("B01", 100);
        System.out.println(message);

        //4. Cek Buku
        buku = library.searchBuku("B01");
        System.out.println(buku.getId()+";"+buku.getNama()+";"+buku.getKategori()+";"+buku.getStok());
        //5. Cek Mahasiswa
        mahasiswa = library.searchMahasiswa("9090");
        System.out.println(mahasiswa.getId()+" "+mahasiswa.getNama());

        //6. Pinjam Buku
        message = library.pinjamBuku("9090", "B01");
        System.out.println(message);

        message = library.pinjamBuku("9090", "B02");
        System.out.println(message);

        //7. Stok per Kategori
        message = library.searchKategoriBuku("Majalah");
        System.out.println(message);

        //8. Cek Mahasiswa
        library.cekMahasiswa("9090");





    }

}
