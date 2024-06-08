package GUI;

public class dataField
{
    private int direction; //在setup设置
    private String account; //账户名，用于显示
    private int accountManager; //账户管理，用于排行榜维护1->修改红色 0->修改黑色
    private Clock clock;

    public dataField(Clock  clock)
    {
        this.direction = 1;
        this.account = "游客";
        this.accountManager = 1;
        this.clock = clock;
    }

    public void setTime(int choose)
    {
        int Time = 0;
        if(choose == 0)
        {
            // 300s
            Time = 300;
        }
        else
        {
            // 900s
            Time = 900;
        }
        clock.setClk_reset_value(Time);
    }

    public int getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(int accountManager) {
        this.accountManager = accountManager;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getDirection() {
        return direction;
    }


    public String getAccount() {
        return account;
    }
}
