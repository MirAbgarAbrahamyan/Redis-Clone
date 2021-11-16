package server;

import database.Commands;
import database.DataBase;
import helper.DataBaseAdder;
import helper.DataBaseDeleter;
import utils.Utility;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ServerTask implements Runnable {
    private final Socket connection;

    public ServerTask(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            InputStream in = new BufferedInputStream(connection.getInputStream());
            StringBuilder req = new StringBuilder(80);
            while (true) {
                int ch = in.read();
                if (ch == '\r' || ch == '\n' || ch == -1) {
                    break;
                }
                req.append((char) ch);
            }


            String[] reqStrArr = req.toString().split(" ");

            if (reqStrArr.length == 3) {
                out.write(Objects.requireNonNull(DataBaseAdder.add(reqStrArr[0], reqStrArr[1], reqStrArr[2]))
                        .getBytes(StandardCharsets.UTF_8));
            } else if (reqStrArr.length == 1 && reqStrArr[0].equals(Commands.SHOW_DB.value)) {
                out.write((DataBase.getDB() + "\n").getBytes(StandardCharsets.UTF_8));
            } else if (reqStrArr.length == 4 && reqStrArr[0].equals(Commands.ADD_MAP.value)) {
                String key = reqStrArr[1];
                String innerMapKey = reqStrArr[2];
                String innerMapValue = reqStrArr[3];
                if (Utility.isMap(key)) {
                    if (DataBase.db.containsKey(key)) {
                        DataBase.updateMap(key, innerMapKey, innerMapValue);
                        out.write("Updated an existing key-value pair for hash\n".getBytes(StandardCharsets.UTF_8));
                    } else {
                        DataBase.addMap(key, innerMapKey, innerMapValue);
                        out.write("Updated a new key-value pair for hash\n".getBytes(StandardCharsets.UTF_8));
                    }
                } else {
                    out.write("Cannot change the structure\n".getBytes(StandardCharsets.UTF_8));
                }
            } else if (reqStrArr.length == 2) {
                if (Utility.isShowCommand(reqStrArr[0])) {
                    out.write((DataBase.showKey(reqStrArr[1]) + "\n").getBytes(StandardCharsets.UTF_8));
                } else {
                    out.write(Objects.requireNonNull(DataBaseDeleter.delete(reqStrArr[0], reqStrArr[1]))
                            .getBytes(StandardCharsets.UTF_8));
                }
            } else {
                out.write("Wrong Input format\n".getBytes(StandardCharsets.UTF_8));
            }


            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException ignored) {
            }
        }
    }
}
