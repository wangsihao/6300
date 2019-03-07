package edu.gatech.seclass.sdpcipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class SDPCipherActivity extends AppCompatActivity {

    private RadioButton encodeOption;
    private RadioButton decodeOption;
    private EditText inputMessage;
    private EditText shiftNumber;
    private EditText resultMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdpcipher);

        encodeOption = (RadioButton)findViewById(R.id.encodeOption);
        decodeOption = (RadioButton)findViewById(R.id.decodeOption);
        inputMessage = (EditText)findViewById(R.id.inputMessage);
        shiftNumber = (EditText)findViewById(R.id.shiftNumber);
        resultMessage = (EditText)findViewById(R.id.resultMessage);

    }




    public void handleClick(View view){

        String messageFrom = null;
        int sn = 0;
        switch (view.getId()){
            case R.id.buttonCalculate:

                String rawmessage = inputMessage.getText().toString();
                String checkrawmessage = rawmessage.replaceAll("[^a-zA-Z]", "");

                if(checkrawmessage == null || checkrawmessage.isEmpty()){
                    inputMessage.setError("Invalid Message");
                }else{
                    inputMessage.setError(null);
                }

                String shiftnumber = shiftNumber.getText().toString();

                if(shiftnumber.isEmpty()){
                    shiftNumber.setError("Invalid Shift Number");
                }else{
                    sn = Integer.parseInt(shiftnumber);
                    shiftNumber.setError(null);
                }

                if (encodeOption.isChecked()){
                    messageFrom = "encode";
                    decodeOption.setError(null);
                }else if (decodeOption.isChecked()){
                    messageFrom = "decode";
                    decodeOption.setError(null);
                }else{
                    decodeOption.setError("Missing Encoding Option");
                }


                if (sn >=1 && sn <=25){
                    if (messageFrom == "encode"){
                        resultMessage.setText(encryptString(rawmessage, sn));
                        shiftNumber.setError(null);
                    }else if(messageFrom == "decode"){
                        resultMessage.setText(decryptString(rawmessage, sn));
                        shiftNumber.setError(null);
                    }
                }
                else {
                    shiftNumber.setError("Invalid Shift Number");
                }





                break;
        }

    }


    private static String encryptString(String plainText, int sn){

        char[] _plainText = plainText.toCharArray();
        int ptLength = plainText.length();
        int[] tmp = new int[ptLength];
        char[] cipherText = new char[ptLength];

        for(int i = 0; i<ptLength;i++)
        {
            tmp[i] = (int)_plainText[i];
        }
        for(int i = 0; i<ptLength;i++)
        {
            int letter = tmp[i] ;
            if( letter >= 'A' && letter <= 'Z')
            {
                letter = letter + sn;
                if(letter > 'Z')
                {
                    letter = letter -26;
                }
                cipherText[i] = (char)letter;
            }
            else if( letter >= 'a' && letter <= 'z')
            {
                letter = letter + sn;
                if(letter > 'z')
                {
                    letter = letter - 26;
                }
                cipherText[i] = (char)letter;

            }

            cipherText[i] = (char)letter;
        }

        return String.valueOf(cipherText);
    }

    private static String decryptString(String plainText, int sn){

        char[] _plainText = plainText.toCharArray();
        int ptLength = plainText.length();
        int[] tmp = new int[ptLength];
        char[] cipherText = new char[ptLength];

        for(int i = 0; i<ptLength;i++)
        {
            tmp[i] = (int)_plainText[i];
        }

        for(int i = 0; i<ptLength;i++)
        {
            int letter = tmp[i] ;
            if( letter >= 'A' && letter <= 'Z')
            {
                letter = letter - sn;
                if(letter < 'A')
                {
                    letter = letter + 26;
                }
                cipherText[i] = (char)letter;
            }
            else if( letter >= 'a' && letter <= 'z')
            {
                letter = letter - sn;
                if(letter < 'a')
                {
                    letter = letter + 26;
                }
                cipherText[i] = (char)letter;

            }

            cipherText[i] = (char)letter;
        }

        return String.valueOf(cipherText);
    }


}
