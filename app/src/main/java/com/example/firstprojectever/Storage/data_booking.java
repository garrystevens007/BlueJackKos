package com.example.firstprojectever.Storage;

public class data_booking  {

    public String userId;
    public String bookingId;
    public String kosname;
    public String username;
    public String bookdate;
    public String facility;
    public String kosprice;
    public String kosaddress;
    public String koslong;
    public String koslat;

    public data_booking() {
    }

    public data_booking(String userId, String bookingId, String kosname, String username, String bookdate, String facility, String kosprice, String kosaddress, String koslong, String koslat) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.kosname = kosname;
        this.username = username;
        this.bookdate = bookdate;
        this.facility = facility;
        this.kosprice = kosprice;
        this.kosaddress = kosaddress;
        this.koslong = koslong;
        this.koslat = koslat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getKosname() {
        return kosname;
    }

    public void setKosname(String kosname) {
        this.kosname = kosname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookdate() {
        return bookdate;
    }

    public void setBookdate(String bookdate) {
        this.bookdate = bookdate;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getKosprice() {
        return kosprice;
    }

    public void setKosprice(String kosprice) {
        this.kosprice = kosprice;
    }

    public String getKosaddress() {
        return kosaddress;
    }

    public void setKosaddress(String kosaddress) {
        this.kosaddress = kosaddress;
    }

    public String getKoslong() {
        return koslong;
    }

    public void setKoslong(String koslong) {
        this.koslong = koslong;
    }

    public String getKoslat() {
        return koslat;
    }

    public void setKoslat(String koslat) {
        this.koslat = koslat;
    }
}
