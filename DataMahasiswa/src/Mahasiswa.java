import java.util.ArrayList;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private ArrayList<String> Element;

    public Mahasiswa(String nim, String nama, String jenisKelamin, ArrayList<String> Element) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.Element = Element;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public ArrayList<String> getElement() {
        return Element;
    }

    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public void setElement(ArrayList<String> Element) {
        this.Element = Element;
    }

    public String getElementAsString() {
        return String.join(", ", Element);
    }
}
