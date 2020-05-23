package com.example.firstprojectever.Storage;



public class data_user {

    public static String userId;
    public static String username;
    public static String password;
    public static String pnumber;
    public static String bdate;
    public static String gender;

    public data_user(String userId, String username, String password, String pnumber, String bdate, String gender) {
        this.username = username;
        this.password = password;
        this.pnumber = pnumber;
        this.bdate = bdate;
        this.gender = gender;
        this.userId = userId;
    }

    public data_user(String userId,String username,String password){
        this.username = username;
        this.userId = userId;
        this.password = password;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        data_user.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
