package glory.sicekatan.Kelas;

/**
 * Created by Glory on 03/10/2018.
 */

public class UserModel {
    public String uid;
    public String displayName;
    public String token;
    public String last_login;
    public String check;
    public String umur;
    public String diagnosaPenyakit;
    public String keadaanAwalJantung;
    public String keadaanAwalDarah;
    public String keadaanAwalSuhu;
    public String alamat;
    public String pekerjaan;

    public UserModel(String uid, String displayName, String token, String last_login, String check, String umur, String diagnosaPenyakit, String keadaanAwalJantung, String keadaanAwalDarah,
                     String keadaanAwalSuhu,String alamat,String pekerjaan) {
        this.uid = uid;
        this.displayName = displayName;
        this.token = token;
        this.last_login = last_login;
        this.check = check;
        this.umur = umur;
        this.diagnosaPenyakit = diagnosaPenyakit;
        this.keadaanAwalJantung = keadaanAwalJantung;
        this.keadaanAwalDarah = keadaanAwalDarah;
        this.keadaanAwalSuhu = keadaanAwalSuhu;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
    }
}
