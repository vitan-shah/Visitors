package visitor;

class House {
    private int houseNo;
    private String contactName;
    private String contactNumber;

    public House(int houseNo, String contactName, String contactNumber) {
        this.houseNo = houseNo;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "House [houseNo=" + houseNo + ", contactName=" + contactName + ", contactNumber=" + contactNumber + "]";
    }
}
