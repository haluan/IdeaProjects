import java.util.ArrayList;

/**
 * Created by haluan on 2/20/16.
 */
public class Nasabah implements Comparable<Nasabah>{
    private String namaPemilik;
    private String nomorRekening;
    private Double jumlahUang;
    private ArrayList<Nasabah> nasabahs;
    public Nasabah(ArrayList<Nasabah> nasabahs) {
        this.nasabahs = nasabahs;
    }

    public Nasabah findByName(String nama){
        for(Nasabah n : nasabahs){
            if(n.getNamaPemilik().equalsIgnoreCase(nama)){
                return n;
            }
        }
        return null;
    }

    public Nasabah findByRekening(String rekening){
        for(Nasabah n : nasabahs){
            if(n.getNomorRekening().equalsIgnoreCase(rekening)){
                return n;
            }
        }
        return null;
    }

    public ArrayList<Nasabah> find(Double money){
        ArrayList<Nasabah> nasabahs = new ArrayList<Nasabah>();
        for(Nasabah n : nasabahs){
            if(n.getJumlahUang() > money){
                nasabahs.add(n);
            }
        }
        return nasabahs;
    }

    public double mean(){
        double mean=0 ;
        for(Nasabah n : nasabahs){
            mean += n.getJumlahUang();
        }
        return Math.floor(divide(mean, nasabahs.size()));
    }

    public double divide(double a, int b){
        if (a < b){
            return 0;
        }else{
            return 1 + divide(a-b, b);
        }
    }

    public boolean deposit(String nomorRekening, Double money){
        Nasabah nasabah = this.findByRekening(nomorRekening);
        if (nasabah != null) {
            nasabah.jumlahUang += money;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(Nasabah o) {
        return o.getJumlahUang().compareTo(this.getJumlahUang()) ;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public Double getJumlahUang() {
        return jumlahUang;
    }

    public void setJumlahUang(Double jumlahUang) {
        this.jumlahUang = jumlahUang;
    }
}
