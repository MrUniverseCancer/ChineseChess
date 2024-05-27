package rules;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RankingListGet
{
//    public static ArrayList<ArrayList<Object>> getRankingList()
//    {
//        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
//        ArrayList<Object> temp = new ArrayList<Object>();
//        temp.add("张三");
//        temp.add(100);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("李四");
//        temp.add(90);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("王五");
//        temp.add(80);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("赵六");
//        temp.add(70);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("钱七");
//        temp.add(60);
//        data.add(temp);
//        // 给出更多例子
//        temp = new ArrayList<Object>();
//        temp.add("孙八");
//        temp.add(50);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("周九");
//        temp.add(40);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("吴十");
//        temp = new ArrayList<Object>();
//        temp.add("孙八");
//        temp.add(50);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("周九");
//        temp.add(40);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("吴十");
//        temp.add(30);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("孙八");
//        temp.add(50);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("周九");
//        temp.add(40);
//        data.add(temp);
//        temp = new ArrayList<Object>();
//        temp.add("吴十");
//        temp.add(30);
//        data.add(temp);
//
//        return data;
//    }

    public static ArrayList<ArrayList<Object>> getRankingList() {
        ArrayList<ArrayList<Object>> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    int score = Integer.parseInt(parts[2].trim());
                    ArrayList<Object> userDetails = new ArrayList<>();
                    userDetails.add(username);
                    userDetails.add(score);
                    users.add(userDetails);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts: " + e.getMessage());
        }

        users.sort((u1, u2) -> Integer.compare((int) u2.get(1), (int) u1.get(1)));
        return users;
    }
}

