package com.example.firstprojectever.Storage;


import android.os.Parcel;
import android.os.Parcelable;

public class data_kos implements Parcelable {

    public String kosId;
    public String userId;
    public String namakos;
    public String fasilitas;
    public String harga;
    public int images;
    public String latitude;
    public String longitude;
    public String description;

    public data_kos( String kosId, String namakos, String fasilitas, String harga, int images, String latitude, String longitude, String description) {
        this.kosId = kosId;
        this.namakos = namakos;
        this.fasilitas = fasilitas;
        this.harga = harga;
        this.images = images;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }


    public data_kos(Parcel in) {
        kosId = in.readString();
        namakos = in.readString();
        fasilitas = in.readString();
        harga = in.readString();
        images = in.readInt();
        latitude = in.readString();
        longitude = in.readString();
        description = in.readString();
    }

    public static final Creator<data_kos> CREATOR = new Creator<data_kos>() {
        @Override
        public data_kos createFromParcel(Parcel in) {
            return new data_kos(in);
        }

        @Override
        public data_kos[] newArray(int size) {
            return new data_kos[size];
        }
    };

    public String getKosId() {
        return kosId;
    }

    public void setKosId(String kosId) {
        this.kosId = kosId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNamakos() {
        return namakos;
    }

    public void setNamakos(String namakos) {
        this.namakos = namakos;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(kosId);
        parcel.writeString(namakos);
        parcel.writeString(fasilitas);
        parcel.writeString(harga);
        parcel.writeInt(images);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(description);
    }

}
