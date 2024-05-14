package rules;


import java.io.*;

public class AccountManager {
    private static final String ACCOUNT_FILE_PATH = "accounts.txt"; //存储账户信息的文件
    // 存储账户信息到文件
    public static int storeAccount(String username, String password) {
        if(username.isEmpty())
        {
            return -1;  //用户名不能为空
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE_PATH, true))) {
            writer.write(username + "," + password);
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
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return 0;   //验证成功
                }
                else
                {
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
}