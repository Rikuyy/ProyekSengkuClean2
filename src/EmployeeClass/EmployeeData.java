package EmployeeClass;

public class EmployeeData {
    private String id_karyawan;
    private String nama;
    private int usia;
    private String alamat;
    private String telepon;
    private String email;
    private byte[] foto; 

    // Konstruktor lengkap (biasanya untuk data yang sudah ada di DB)
    public EmployeeData(String id_karyawan, String nama, int usia, String alamat, String telepon, String email, byte[] foto) {
        this.id_karyawan = id_karyawan;
        this.nama = nama;
        this.usia = usia;
        this.alamat = alamat;
        this.telepon = telepon;
        this.email = email;
        this.foto = foto;
    }

    // Konstruktor untuk data baru tanpa id (id akan di-generate di DB)
    public EmployeeData(String nama, int usia, String alamat, String telepon, String email, byte[] foto) {
        this.id_karyawan = null; // atau kosong ""
        this.nama = nama;
        this.usia = usia;
        this.alamat = alamat;
        this.telepon = telepon;
        this.email = email;
        this.foto = foto;
    }

    // Getter dan setter
    public String getId() {
        return id_karyawan;
    }
    public void setId(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUsia() {
        return usia;
    }
    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}

