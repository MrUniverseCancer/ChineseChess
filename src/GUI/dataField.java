package GUI;

public class dataField
{
    private int direction; //在setup设置
    private String account; //账户名，用于显示

    public dataField()
    {
        this.direction = 1;
        this.account = "游客";
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
