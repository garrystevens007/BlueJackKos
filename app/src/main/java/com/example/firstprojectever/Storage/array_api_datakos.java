package com.example.firstprojectever.Storage;

public class array_api_datakos {
    private int idkos;
    private String namakos;
    private String price;
    private String fasilitaskos;
    private String imagekos;
    private String addresskos;
    private String latkos;
    private String lngkos;

    public array_api_datakos() {

    }

    public array_api_datakos(int idkos, String namakos, String price, String fasilitaskos, String imagekos, String addresskos, String latkos, String lngkos) {
        this.idkos = idkos;
        this.namakos = namakos;
        this.price = price;
        this.fasilitaskos = fasilitaskos;
        this.imagekos = imagekos;
        this.addresskos = addresskos;
        this.latkos = latkos;
        this.lngkos = lngkos;
    }

    public int getIdkos() {
        return idkos;
    }

    public void setIdkos(int idkos) {
        this.idkos = idkos;
    }

    public String getNamakos() {
        return namakos;
    }

    public void setNamakos(String namakos) {
        this.namakos = namakos;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFasilitaskos() {
        return fasilitaskos;
    }

    public void setFasilitaskos(String fasilitaskos) {
        this.fasilitaskos = fasilitaskos;
    }

    public String getImagekos() {
        return imagekos;
    }

    public void setImagekos(String imagekos) {
        this.imagekos = imagekos;
    }

    public String getAddresskos() {
        return addresskos;
    }

    public void setAddresskos(String addresskos) {
        this.addresskos = addresskos;
    }

    public String getLatkos() {
        return latkos;
    }

    public void setLatkos(String latkos) {
        this.latkos = latkos;
    }

    public String getLngkos() {
        return lngkos;
    }

    public void setLngkos(String lngkos) {
        this.lngkos = lngkos;
    }
}
