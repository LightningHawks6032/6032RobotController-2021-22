package org.firstinspires.ftc.teamcode6032.debug.comms.data;

/** Utility class for converting character type codes for packets into enums. */
public class TypeCharConverter {
    /** Convert packet param type enum value to char. */
    public static char paramTypeChar(PacketParam.Type type) {
        switch (type) {
            case FLOAT: return 'f';
            case INT: return 'i';
            case STRING: return 's';
            default: return 0;
        }
    }
    /** Convert packet type enum value to char. */
    public static char packetTypeChar(Packet.Type type) {
        switch (type) {
            case REQUEST: return '>';
            case RESPONSE: return '<';
            default: return 0;
        }
    }

    /** Convert packet param type char to enum value. */
    public static PacketParam.Type paramTypeEnum(char c) {
        switch (c) {
            case 'f': return PacketParam.Type.FLOAT;
            case 'i': return PacketParam.Type.INT;
            case 's': return PacketParam.Type.STRING;
            default: return null;
        }
    }
    /** Convert packet type char to enum value. */
    public static Packet.Type packetTypeEnum(char c) {
        switch (c) {
            case '>': return Packet.Type.REQUEST;
            case '<': return Packet.Type.RESPONSE;
            default: return null;
        }
    }
}
