package rules;


import java.io.*;
import java.nio.Buffer;

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
                if (parts.length == 2 && parts[0].equals(username)) {
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

    public static void changeAccountScore(String username, int score) {
        File inputFile = new File(ACCOUNT_FILE_PATH);
        File tempFile = new File("accounts_temp.txt");

        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username)) {
                    int currentScore = Integer.parseInt(parts[2]);
                    long newScore = (long) currentScore + score;  // 使用long避免溢出
                    if (newScore >= 0 && newScore <= Integer.MAX_VALUE) {
                        parts[2] = String.valueOf((int) newScore);
                        userFound = true;
                    } else {
                        System.err.println("Score update out of valid range for user: " + username);
                        writer.write(line);  // 将原始行写回
                        writer.newLine();
                        continue;
                    }
                }
                writer.write(String.join(",", parts));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error processing accounts: " + e.getMessage());
            return;
        }

        if (!userFound) {
            System.err.println("User not found: " + username);
            tempFile.delete();  // 删除临时文件
            return;
        }

        // 覆盖原始文件
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing accounts: " + e.getMessage());
            return;
        }

        // 删除临时文件
        if (!tempFile.delete()) {
            System.err.println("Could not delete temporary file");
        }
    }

}
