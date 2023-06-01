public interface NetConnectionListener {
    void onConnectionReady(NetConnection netConnection);
    void onReceiveString(NetConnection netConnection, String value);
    void onDisconnect(NetConnection netConnection);
    void onException(NetConnection netConnection, Exception e);
}// class
