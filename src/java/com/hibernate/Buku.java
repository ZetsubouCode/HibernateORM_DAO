package com.hibernate;

public class Buku  implements java.io.Serializable {

     private Integer idBuku;
     private String title;
     private String terjemahan;
     private int edisi;
     private int halaman;
     private long isbn;
     private int eksemplar;

    public Buku() {
        this.idBuku = 0;
        this.title = "";
        this.terjemahan = "";
    }

    public Buku(String title, String terjemahan, int edisi, int halaman, long isbn, int eksemplar) {
       this.title = title;
       this.terjemahan = terjemahan;
       this.edisi = edisi;
       this.halaman = halaman;
       this.isbn = isbn;
       this.eksemplar = eksemplar;
    }
   
    public Integer getIdBuku() {
        return this.idBuku;
    }
    
    public void setIdBuku(Integer idBuku) {
        this.idBuku = idBuku;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTerjemahan() {
        return this.terjemahan;
    }
    
    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }
    public int getEdisi() {
        return this.edisi;
    }
    
    public void setEdisi(int edisi) {
        this.edisi = edisi;
    }
    public int getHalaman() {
        return this.halaman;
    }
    
    public void setHalaman(int halaman) {
        this.halaman = halaman;
    }
    public long getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    public int getEksemplar() {
        return this.eksemplar;
    }
    
    public void setEksemplar(int eksemplar) {
        this.eksemplar = eksemplar;
    }




}


