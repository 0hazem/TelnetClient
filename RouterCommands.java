import java.net.SocketException;

public class RouterCommands {

    private TelnetClient telnet;

    private String ip;
    private String username;
    private String password;
    public RouterCommands(String ip, String username, String password) {

        this.ip = ip;
        this.username = username;
        this.password = password;

        reconnect();
    }

    public void reconnect() {
        telnet = new TelnetClient(ip, username, password);
    }

    public boolean ping(String ip) {
        String result = telnet.sendCommand("ping " + ip);

        // parse result
        if(result.contains("Success"))
            return true;

        return false;
    }
    public boolean reboot() {

        try {
        String result = telnet.sendCommand("reboot");
        if (result.contains("Fail"))
            return false;
        } catch(Exception e) {}
        return true;
    }
    public boolean restore() {
        try {
            String result = telnet.sendCommand("restore");
            if (result.contains("Fail"))
                return false;
        }
        catch (Exception e) {}
        return true;
    }
     public boolean set(String index, String ssid, String channel, String beacon, String auth, String key, Boolean en){
        String result;
        if (en)
        result = telnet.sendCommand("set wlan " + index + " ssid " + ssid + " channel " + channel + " beacontype " + beacon + " auth " + auth + " keys " + key + " enabled");
        else result = telnet.sendCommand("set wlan " + index + " ssid " + ssid + " channel " + channel + " beacontype " + beacon + " auth " + auth + " keys " + key +  " disabled");
         // parse result
         if(result.contains("Success"))
             return true;

         return false;
        }


    public static void main(String[] args) {
        try {

            RouterCommands router = new RouterCommands("192.168.1.1", "!!Huawei", "@HuaweiHgw");

            boolean result = router.ping("192.168.1.1");

            System.out.println("this is router output: " + result);

            boolean result2 = router.set("1","woody", "1", "wpa2", "psk", "woody12345", true);
            System.out.println("this is router output (true): " + result2);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
