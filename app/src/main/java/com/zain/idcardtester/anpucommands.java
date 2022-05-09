package com.zain.idcardtester;

import java.util.ArrayList;

public class anpucommands {
    public static byte[][] getCommands(int coomand_set) {
        byte[] MAV1_GEM_AID = new byte[]{0, -92, 4, 0, 12, -96, 0, 0, 0, 24, 12, 0, 0, 1, 99, 66, 0};
        byte[] MAV1_AID = new byte[]{0, -92, 4, 0, 16, -96, 0, 0, 0, 24, 48, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] MAV3_GEM_AID = new byte[]{0, -92, 4, 0, 12, -96, 0, 0, 0, 24, 64, 0, 0, 1, 99, 66, 0};
        byte[] MAV3_AID = new byte[]{0, -92, 4, 0, 12, -96, 0, 0, 0, 24, 64, 0, 0, 1, 99, 66, 0};
        byte[] MAV3_GEM_AID_CONTACTLESS = new byte[0];
        byte[] MAV3_AID_CONTACTLESS = new byte[]{-96, 0, 0, 3, -105, 67, 73, 68, 95, 1, 0};
        byte[] MAV4_GEM_AID = new byte[]{0, -92, 4, 0, 12, -96, 0, 0, 0, 24, 64, 0, 0, 1, 99, 66, 0};
        byte[] MAV4_AID = new byte[]{0, -92, 4, 0, 12, -96, 0, 0, 0, 24, 64, 0, 0, 1, 99, 66, 0};
        byte[] MAV4_GEM_AID_CONTACTLESS = new byte[0];
        byte[] MAV4_AID_CONTACTLESS = new byte[]{-96, 0, 0, 3, -105, 67, 73, 68, 95, 1, 0};
        byte[] GET_EF_LENGTH_COMMAND_DATA = new byte[]{0, -64, 0, 0};
        byte[] nashmi0 = new byte[]{(byte) 0xa0, 0x00, 0x00, 0x00, 0x18, 0x40, 0x00, 0x00, 0x01, 0x63, 0x42, 0x00};
        byte[] nashmi1 = new byte[]{0x00, (byte) 0xa4, 0x08, 0x00, 0x02, 0x02, 0x00};
        byte[] nashmi2 = new byte[]{0x00, (byte) 0xa4, 0x02, 0x00, 0x02, 0x02, 0x01};
        byte[] nashmi3 = new byte[]{0x00, (byte) 0xb0, 0x00, 0x00, 0x09};
        byte[] nashmi4 = new byte[]{0x00, (byte) 0xa4, 0x02, 0x00, 0x02, 0x02, 0x02};
        byte[] nashmi5 = new byte[]{0x00, (byte) 0xb0, 0x00, 0x02, 0x02, 0x02};
        byte[] nashmi6 = new byte[]{0x00, (byte) 0xb0, 0x00, 0x00, (byte) 0xfc};
        byte[][] commands;
        if (coomand_set == 1) {
            commands = new byte[][]{nashmi0, nashmi1, nashmi2, nashmi3, nashmi4, nashmi5, nashmi6};
        } else {
            commands = new byte[][]{MAV1_GEM_AID, MAV1_AID, MAV3_GEM_AID, MAV3_AID, MAV3_GEM_AID_CONTACTLESS, MAV3_AID_CONTACTLESS, MAV4_GEM_AID, MAV4_AID, MAV4_GEM_AID_CONTACTLESS, MAV4_AID_CONTACTLESS, GET_EF_LENGTH_COMMAND_DATA};
        }
        return commands;


    }
    public static ArrayList<String> get_cmd(int version){
        ArrayList<String> pdu_command= new ArrayList<>();
        if (version ==1) {
            pdu_command.add("00A4040010A0000000183003010000000000000000");
            pdu_command.add("00A4000C023F00");
            pdu_command.add("00A4010C020200");
            pdu_command.add("00A40200020202");
            pdu_command.add("00B00000FC");
            pdu_command.add("00B0003FFC");
            pdu_command.add("00B0007E47");
            pdu_command.add("00A40400");
            pdu_command.add("00CA01010D");

        }
        else{
            pdu_command.add("00A404000CA00000001840000001634200");
            pdu_command.add("00A408000402000202");
            pdu_command.add("00B00000FC");
            pdu_command.add("00B000FCFC");
            pdu_command.add("00B001F847");
            pdu_command.add("00A40400");
            pdu_command.add("00CA01010D");

        }
        return pdu_command;

    }

}
