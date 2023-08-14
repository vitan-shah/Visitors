package visitor;
import java.util.*;

class Visitor {
    private int id;
    private String name;
    private String contactNumber;
    private int houseNo;
    private long entryTime;
    private long exitTime;
    private boolean approved;

    public Visitor(int id, String name, String contactNumber, int houseNo) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.houseNo = houseNo;
        this.entryTime = System.currentTimeMillis();
        this.approved = false;
    }

    public Integer getId() {
        return id;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public boolean isApproved() {
        return approved;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long time) {
        this.exitTime = time;
    }

    // Other getters and setters

    @Override
    public String toString() {
        return "Visitor [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", houseNo=" + houseNo
                + ", entryTime=" + new Date(entryTime) + ", exitTime=" + new Date(exitTime) + ", approved=" + approved
                + "]";
    }
}
