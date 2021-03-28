package fr.pulsedev.bootstrap.enums;

public enum StateText {
    CONNECTION(0, "Connection in progress ..."),
    ERROR_CONNECTION(1, "Error while connecting !"),
    SUCCESS_CONNECTION(2, "Connection success !"),
    DOWNLOADING(3, "Download in progress ..."),
    SUCCESS_DOWNLOAD(4, "Successful download !");

    final int id;
    final String text;

    StateText(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public static StateText getById(int id) {
        for (StateText stateText : StateText.values()) {
            if (stateText.getId() == id) {
                return stateText;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

}
