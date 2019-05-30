package usersData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersData {
    public static class User
    {
        public String Login = null;
        public String Password = null;
        public int Q1 = 0;
        public int Q2 = 0;
        public int Q3 = 0;
    }

    private final String FILE_NAME = "user_data.data";
    public List<User> usersList;

    public UsersData() {
        usersList = new ArrayList<>();
    }

    public void saveData() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for (User user : usersList) {
                writer.write(user.Login + " " + user.Password + " "
                        + user.Q1 + " " + user.Q2 + " " + user.Q3 + "\r\n");
                //System.out.println(String.format("Row saved login: %1$s", user.Login));
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> loadData () {
        if (usersList != null) {
            usersList = new ArrayList<>();
        }
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Scanner in = new Scanner(file);
                String line;

                while (in.hasNextLine()) {
                    User user = new User();

                    line = in.nextLine();

                    user.Login = line.split(" ")[0];
                    user.Password = line.split(" ")[1];
                    user.Q1 = Integer.parseInt(line.split(" ")[2]);
                    user.Q2 = Integer.parseInt(line.split(" ")[3]);
                    user.Q3 = Integer.parseInt(line.split(" ")[4]);

                    //System.out.println(user.Login);
                    usersList.add(user);
                }
                in.close();

                return usersList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
