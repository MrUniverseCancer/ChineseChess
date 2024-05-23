package rules;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AccountManager {
    private static final String ACCOUNT_FILE_PATH = "accounts.txt"; //存储账户信息的文件
    // 存储账户信息到文件
    public static int storeAccount(String username, String password) {
        if(username.isEmpty())
        {
            return -1;  //用户名不能为空
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE_PATH, true))) {
            writer.write(username + "," + password + ", 0" );   //创建用户时默认积分为0
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error storing account: " + e.getMessage());
            return -2;  //创建用户失败
        }
        return 0;
    }

    // 核验登录是否存在该账户和对应的密码是否正确
    public static int verifyAccount(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {    //查询每个注册了的账户
                String[] parts = line.split(",");   //parts[1]为账户名,parts[2]为账户密码
                if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
                    return 0;   //验证成功
                }
                else if(parts.length == 2 && parts[0].equals(username) && !parts[1].equals(password)) {
                    return -2;  //有该用户，但是密码不正确
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error verifying account: " + e.getMessage());
            return -1;  //读取账户信息错误
        }
        return -3;  //未查询到该用户的账户
    }

    // 核验是否该用户已注册
    public static int verifyIfRegistered(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {      //查询每个注册了的账户
                String[] parts = line.split(",");       //parts[1]为账户名,parts[2]为账户密码
                if (parts.length == 3 && parts[0].equals(username)) {
                    return 1;   //该用户已注册
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error checking account: " + e.getMessage());
            return -1;  //读取账户信息错误
        }
        return 0;  //未查询到该用户的账户
    }

    // 对已注册用户进行前100名的排序
    public static List<String> getTop100Users() {
        List<User> users = new ArrayList<>();

        // 读取账户文件
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    int score = Integer.parseInt(parts[2].trim());
                    users.add(new User(username, score));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts: " + e.getMessage());
        }

        // 对用户按积分进行排序
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getScore(), u1.getScore());
            }
        });

        // 提取前100名用户
        List<String> topUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(100, users.size()); i++) {
            User user = users.get(i);
            topUsers.add(user.getUsername() + ", " + user.getScore());
        }

        return topUsers;
    }


}

// 用户类用于存储用户名和积分
class User {
    private String username;
    private int score;

    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}