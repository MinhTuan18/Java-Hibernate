package pojo;
// Generated Jun 12, 2020 11:24:48 AM by Hibernate Tools 4.3.1



/**
 * Bangdiem generated by hbm2java
 */
public class Bangdiem  implements java.io.Serializable {


     private BangdiemId id;
     private String hoten;
     private Double giuaki;
     private Double cuoiki;
     private Double khac;
     private Double diemtong;

    public Bangdiem() {
    }

	
    public Bangdiem(BangdiemId id) {
        this.id = id;
    }

    public Bangdiem(BangdiemId id, String hoten, Double giuaki, Double cuoiki, Double khac, Double diemtong) {
        this.id = id;
        this.hoten = hoten;
        this.giuaki = giuaki;
        this.cuoiki = cuoiki;
        this.khac = khac;
        this.diemtong = diemtong;
    }

    public BangdiemId getId() {
        return id;
    }

    public void setId(BangdiemId id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Double getGiuaki() {
        return giuaki;
    }

    public void setGiuaki(Double giuaki) {
        this.giuaki = giuaki;
    }

    public Double getCuoiki() {
        return cuoiki;
    }

    public void setCuoiki(Double cuoiki) {
        this.cuoiki = cuoiki;
    }

    public Double getKhac() {
        return khac;
    }

    public void setKhac(Double khac) {
        this.khac = khac;
    }

    public Double getDiemtong() {
        return diemtong;
    }

    public void setDiemtong(Double diemtong) {
        this.diemtong = diemtong;
    }
    


}


