package com.zain.idcardtester;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import wangpos.sdk4.libbasebinder.BankCard;
import wangpos.sdk4.libbasebinder.HEX;
import wangpos.sdk4.libbasebinder.IDCard;
import wangpos.sdk4.libbasebinder.RspCode;



import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Button readcard;
private TextView tt;
private BankCard mBankcard;
private IDCard mIdCard;
    private static final String TAG = "IDCardTester";
    private static final String PKGNAME = "com.zain.idcardtester";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readcard = (Button) findViewById(R.id.read_id);
        readcard.setOnClickListener(this);
        new Thread() {
            @Override
            public void run() {
                mIdCard = new IDCard(getApplicationContext());
                mBankcard = new BankCard(getApplicationContext());
            }
        }.start();
        tt= findViewById(R.id.contents);

    }

    @Override
    public void onClick(View view) {
        //TextView tv = (TextView)findViewById(R.id.contents);
        tt.setText("Welcome to android\nWelcome to android\nWelcome to android\n");
        getCardDetails();
    }
    public void getCardDetails() {
        byte[] respdata = new byte[1];
        int[] resplen = new int[1];
        int retvalue = 0;
        int retyrtime = 0;
        int retvalueoc = 0;

        try {
            retvalueoc = mIdCard.openCloseIDCardReader(BankCard.CARD_NMODE_ICC, BankCard.CARD_READ_OPEN);
            Log.v(TAG, "SDK_OpenCloseCardReader:" + retvalueoc);

            if (retvalueoc == RspCode.OK) {
                do {
                    retyrtime++;

                   // retvalue = mIdCard.iDCardReaderDetact(BankCard.CARD_NMODE_ICC, BankCard.CARD_MODE_ICC, IDCard.CARD_TYPE_IC, respdata, resplen, "app1");
                    retvalue = mIdCard.readCard(BankCard.CARD_TYPE_NORMAL,  BankCard.CARD_MODE_ICC,10,respdata, resplen, PKGNAME);

                    Log.v(TAG, "SDK_CardReaderDetact:" + retvalue);
                    SystemClock.sleep(100);
                } while ((retvalue != 7) && (retyrtime < 2));
            }
 PACIObject paciobj = new PACIObject(mIdCard);
            paciobj.setPaci_fields();

            Log.d(TAG, paciobj.toString());
       /*     byte[] sendapdu ;
            //
java.util.ArrayList<String> pdu_command= new ArrayList<>();


            pdu_command.add("00A404000CA00000001840000001634200");
            pdu_command.add("00A408000402000202");
            pdu_command.add("00B00000FC");
            pdu_command.add("00B000FCFC");
            pdu_command.add("00B001F863");
            pdu_command.add("00A40400");
            pdu_command.add("00CA01010D");
            byte[] outdata = new byte[255];
            int[] outlen = new int[1];
            int retapdu = -1;
            int retapdu1 = -1;
            int retapdu2 = -1;
            int retapdu3 = -1;
            String[] v1 = new String[7];
            v1[0]= "00A4040010A0000000183003010000000000000000";
            v1[1]= "00A4000C023F00";
            v1[2]= "00A4010C020200";
            v1[3]= "00A40200020202";
            v1[4]= "00B00000FC";
            v1[5]= "00B0003FFC";
            v1[6]= "00B0007E47";
            for (int i = 0; i <pdu_command.size(); i++) {

                byte[] cmd = HEX.hexToBytes(pdu_command.get(i));

                retapdu2 = mIdCard.sendApdu(IDCard.CARD_TYPE_IC, cmd, cmd.length, outdata, outlen);
                Log.v(TAG, "-------" + outlen[0]);

                Log.v(TAG, i+"CARD_TYPE_IC readcard:[" + keyrandom.bytesToHexString(outdata) + "]");
                Log.v(TAG, i+"CARD_TYPE_IC string readcard:[" + new String(outdata, StandardCharsets.UTF_8) + "]");
            }

            retvalue = mIdCard.openCloseIDCardReader(IDCard.CARD_TYPE_IC,IDCard.CARD_OPER_OFF);
*/
        } catch (Exception e) {
            Log.v(TAG, "SDK_OpenCloseCardReader Exception:" + e.getMessage()); ;
        }
    }
public void getCardDetails2(){
        int retvalue =-1;
        byte[] output= new byte[256];
        int[] olen = new int[5];
    try {

        mBankcard.openCloseCardReader(BankCard.CARD_TYPE_NORMAL, BankCard.CARD_READ_OPEN);
        retvalue = mBankcard.readCard(BankCard.CARD_TYPE_NORMAL,BankCard.CARD_MODE_ICC,10, output , olen,PKGNAME);
        Log.d(TAG , "retvalue = "+retvalue);
        Log.v(TAG, "getCardDetails2 readcard:[" + keyrandom.bytesToHexString(output) + "]");
        byte[] sendapdu ;
        byte[] outdata = new byte[255];
        int[] outlen = new int[1];
        int retapdu = -1;
        java.util.ArrayList<String> pdu_command= new ArrayList<>();

        pdu_command.add("00A404000CA00000001840000001634200");
        pdu_command.add("00A408000402000202");
        pdu_command.add("00B00000FC");
        pdu_command.add("00B000FCFC");
        pdu_command.add("00B001F863");
        pdu_command.add("00A40400");
        pdu_command.add("00CA01010D");


        for (int i = 0; i <pdu_command.size(); i++) {

            byte[] cmd = HEX.hexToBytes(pdu_command.get(i));
            retapdu = mBankcard.sendAPDU(1,cmd,cmd.length,outdata,outlen);

            Log.v(TAG, "-------" + outlen[0]);

            Log.v(TAG, i+"CARD_TYPE_IC readcard:[" + keyrandom.bytesToHexString(outdata) + "]");
            Log.v(TAG, i+"CARD_TYPE_IC string readcard:[" + new String(outdata, StandardCharsets.UTF_8) + "]");

        }

    } catch (RemoteException e) {
        throw new RuntimeException(e);
    }
}

}