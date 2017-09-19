package com.lameguard;

import com.lameguard.session.ClientSession;
import com.lameguard.session.IpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LameGuard {
    private static final LameGuard _instance = new LameGuard();
    private final Map<String, IpSession> ipSessions = new HashMap(1000);

    public static final LameGuard getInstance() {
        return _instance;
    }

    private LameGuard() {
    }

    public boolean checkVersion(int version, int patch) {
        return false;
    }

    public boolean checkData(byte[] data, byte[] check) {
        return false;
    }

    public ClientSession checkClient(String ip, byte[] data) throws Exception {
        return null;
    }

    public byte[] assembleAnswer(ClientSession clientSession, byte[] key) throws Exception {
        return null;
    }

    public static void main(String[] args) throws Throwable {
        if (args.length == 0) {
            Log.error("Main class not specified!");
            return;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(args[0]);
        } catch (Exception e) {
        }
        if (clazz == null) {
            Log.error("Main class not found : " + args[0] + "!");
            return;
        }
        clazz.getDeclaredMethod("main", new Class[]{String[].class}).invoke(null, new Object[]{(String[]) Arrays.copyOfRange(args, 1, args.length)});
    }
}
