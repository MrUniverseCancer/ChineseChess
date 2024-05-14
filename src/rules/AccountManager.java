package rules;


import java.io.*;

public class AccountManager {
    private static final String ACCOUNT_FILE_PATH = "accounts.txt"; //存储账户信息的文件
    // 存储账户信息到文件
    public static void storeAccount(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE_PATH, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error storing account: " + e.getMessage());
        }
    }

    // 核验登录是否存在该账户和对应的密码是否正确
    public static boolean verifyAccount(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");   //parts[1]为账户名,parts[2]为账户密码
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error verifying account: " + e.getMessage());
        }
        return false;
    }
}