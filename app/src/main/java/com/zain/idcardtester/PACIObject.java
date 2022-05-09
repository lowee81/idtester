package com.zain.idcardtester;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import wangpos.sdk4.libbasebinder.BankCard;
import wangpos.sdk4.libbasebinder.IDCard;
import wangpos.sdk4.libbasebinder.RspCode;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import wangpos.sdk4.libbasebinder.HEX;
public class PACIObject {
    private String Last_name;
    private String Last_name_Ar;
    private String First_name,First_name_Ar;
    private String Second_name,Second_name_Ar;
    private String Third_name,Third_name_Ar;
    private String Civil_Id , Card_Expiry,Date_of_Birth;
    private String nationality,nationality_ar;
    private String paci_ref , street , area , house;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex_ar() {
        return sex_ar;
    }

    public void setSex_ar(String sex_ar) {
        this.sex_ar = sex_ar;
    }

    private String sex, sex_ar;
    private IDCard Paci_IdCard;
    private static final String TAG = "IDCardTester.PaciObject";
    private static final String PKGNAME = "com.zain.idcardtester";
    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getLast_name_Ar() {
        return Last_name_Ar;
    }

    public void setLast_name_Ar(String last_name_Ar) {
        Last_name_Ar = last_name_Ar;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getFirst_name_Ar() {
        return First_name_Ar;
    }

    public void setFirst_name_Ar(String first_name_Ar) {
        First_name_Ar = first_name_Ar;
    }

    public String getSecond_name() {
        return Second_name;
    }

    public void setSecond_name(String second_name) {
        Second_name = second_name;
    }

    public String getSecond_name_Ar() {
        return Second_name_Ar;
    }

    public void setSecond_name_Ar(String second_name_Ar) {
        Second_name_Ar = second_name_Ar;
    }

    public String getThird_name() {
        return Third_name;
    }

    public void setThird_name(String third_name) {
        Third_name = third_name;
    }

    public String getThird_name_Ar() {
        return Third_name_Ar;
    }

    public void setThird_name_Ar(String third_name_Ar) {
        Third_name_Ar = third_name_Ar;
    }

    public String getCivil_Id() {
        return Civil_Id;
    }

    public void setCivil_Id(String civil_Id) {
        Civil_Id = civil_Id;
    }

    public String getCard_Expiry() {
        return Card_Expiry;
    }

    public void setCard_Expiry(String card_Expiry) {
        Card_Expiry = card_Expiry;
    }

    public String getDate_of_Birth() {
        return Date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        Date_of_Birth = date_of_Birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality_ar() {
        return nationality_ar;
    }

    public void setNationality_ar(String nationality_ar) {
        this.nationality_ar = nationality_ar;
    }

    public String getPaci_ref() {
        return paci_ref;
    }

    public void setPaci_ref(String paci_ref) {
        this.paci_ref = paci_ref;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

public PACIObject(IDCard mIdCard){
        this.Paci_IdCard = mIdCard;
}
public void setPaci_fields() throws Exception{

    byte[] outdata = new byte[255];
    int[] outlen = new int[1];
    byte[] cmd;
    int retapdu2 = -1;
    int cmd_start_value =1;
    java.util.ArrayList<String> pdu_command=anpucommands.get_cmd(3);
    int iter = 0;
    cmd = HEX.hexToBytes(pdu_command.get(0));
    retapdu2 = Paci_IdCard.sendApdu(IDCard.CARD_TYPE_IC, cmd, cmd.length, outdata, outlen);
   // Log.v(TAG,keyrandom.bytesToHexString(outdata));
    if ( !keyrandom.bytesToHexString(outdata).startsWith("9")){
        pdu_command = anpucommands.get_cmd(1);
        cmd_start_value=0;
    }
    for (int i = cmd_start_value; i <pdu_command.size(); i++) {

        cmd = HEX.hexToBytes(pdu_command.get(i));




        retapdu2 = Paci_IdCard.sendApdu(IDCard.CARD_TYPE_IC, cmd, cmd.length, outdata, outlen);
      //  Log.v(TAG, "output length" + outlen[0] +" retvale ="+retapdu2);
        String temp = new String(outdata, StandardCharsets.UTF_8).replace(Character.toString((char) 0 ).charAt(0), ',');
        String[] tt = temp.split(",");
        ArrayList<String> tt2 = new ArrayList<>();
        for (int j = 0; j < tt.length; j++) {
            if(tt[j].length() >0){
                tt2.add(tt[j].trim());
            }
        }
if (outlen[0] ==255 ) {
    iter++;


//    for (int j = 0; j <tt2.size() ; j++) {
//        Log.d(TAG,j+ " cuurent string :"+tt2.get(j) + " size of the current iteration is :" +tt2.get(j).trim().length());
//
//    }
    if (iter==1){
        setCivil_Id(tt2.get(0));
        setFirst_name_Ar(tt2.get(1));
        setSecond_name_Ar(tt2.get(2));
        setThird_name_Ar(tt2.get(3));
        setLast_name_Ar(tt2.get(4));
        setFirst_name(tt2.get(5));
        setSecond_name( tt2.get(6));
        setThird_name(tt2.get(7));
    }else{
        setLast_name(tt2.get(0));

        setStreet(tt2.get(tt2.size()-2));
        setArea(tt2.get(tt2.size()-3));
        setPaci_ref(tt2.get(tt2.size()-4).substring(0,7));
        setArea(tt2.get(tt2.size()-4).substring(8,tt2.get(tt2.size()-4).length()-1));
        if (tt2.get(1).startsWith("ذكر")){
            setSex_ar("ذكر");
            setSex("Male");
            setNationality_ar(tt2.get(2).substring(1,tt2.get(2).length()-1));
            setNationality(tt2.get(3).substring(0,3));
            setDate_of_Birth(tt2.get(3).substring(3,11));
            setCard_Expiry(tt2.get(3).substring(19,27));
            Log.d(TAG,tt2.get(3));
        }else{
            setSex_ar("انثى");
            setSex("Female");
            setNationality_ar(tt2.get(1).substring(6,tt2.get(1).length()-1));
            setNationality(tt2.get(2).substring(0,3));
            setDate_of_Birth(tt2.get(2).substring(3,11));
            setCard_Expiry(tt2.get(2).substring(19,27));
            Log.d(TAG,tt2.get(2));
        }


    }

   //     Log.d(TAG, "array size :"+tt.length);
  //  Log.d(TAG, "arraylist size :"+tt2.size());
  // Log.v(TAG, i + "CARD_TYPE_IC temp:[" + temp+ "]");
  //    Log.v(TAG, i + "CARD_TYPE_IC readcard:[" + keyrandom.bytesToHexString(outdata) + "]");
//    Log.v(TAG, i + "CARD_TYPE_IC string readcard:[" + new String(outdata, StandardCharsets.UTF_8) + "]");
}else if (outlen[0] ==74||outlen[0] ==102 ){
    setHouse(tt2.get(0));

}
    }




    }

    @NonNull
    @Override
    public String toString() {
        String output="";
        output+=getFirst_name()+",";
        output+= getSecond_name()+",";
        output+=getThird_name()+",";
        output+=getLast_name()+",";
        output+=getFirst_name_Ar()+",";
        output+=getSecond_name_Ar()+",";
        output+=getThird_name_Ar()+",";
        output+=getLast_name_Ar()+",";
        output+=getArea()+",";
        output+=getStreet()+",";
        output+=getHouse()+",";
        output+=getNationality()+",";
        output+=getNationality_ar()+",";
        output+=getSex()+",";
        output+=getSex_ar()+",";
        output+=getPaci_ref()+",";
        output+=getCard_Expiry()+",";
        output+=getDate_of_Birth();

        return output;
    }
}
