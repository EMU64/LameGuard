package com.lameguard;

import com.lameguard.utils.Utils;

public final class HWID {
    public static int HWID_BIOS = 4;
    public static int HWID_CPU = 8;
    public static int HWID_HDD = 2;
    public static int HWID_MAC = 1;

    private HWID() {
    }

    public static final String toString(byte[] hwid) {
        return Utils.asHex(hwid);
    }

    @Deprecated
    public static final boolean isEquals(String hwid1, String hwid2, int mask) {
        return equals(hwid1, hwid2, mask);
    }

    @Deprecated
    public static final boolean isEquals(byte[] hwid1, byte[] hwid2, int mask) {
        return equals(hwid1, hwid2, mask);
    }

    public static final boolean equals(String hwid1, String hwid2, int mask) {
        return equals(Utils.asByteArray(hwid1), Utils.asByteArray(hwid2), mask);
    }

    public static final boolean equals(byte[] hwid1, byte[] hwid2, int mask) {
        if (mask == 0) {
            return false;
        }
        int i;
        if ((HWID_CPU & mask) == HWID_CPU) {
            for (i = 0; i < 2; i++) {
                if (hwid1[i] != hwid2[i]) {
                    return false;
                }
            }
        }
        if ((HWID_BIOS & mask) == HWID_BIOS) {
            for (i = 2; i < 6; i++) {
                if (hwid1[i] != hwid2[i]) {
                    return false;
                }
            }
        }
        if ((HWID_HDD & mask) == HWID_HDD) {
            for (i = 6; i < 10; i++) {
                if (hwid1[i] != hwid2[i]) {
                    return false;
                }
            }
        }
        if ((HWID_MAC & mask) == HWID_MAC) {
            for (i = 10; i < 14; i++) {
                if (hwid1[i] != hwid2[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
