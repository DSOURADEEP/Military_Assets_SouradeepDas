package com.example.militaryassets.dto;

public class DashboardResponse {
    private String assetName;
    private String assetType;
    private int openingBalance;
    private int purchases;
    private int transferIn;
    private int transferOut;
    private int assignments;
    private int expenditures;
    private int closingBalance;

    // Constructors
    public DashboardResponse() {}

    public DashboardResponse(String assetName, String assetType, int openingBalance, int purchases,
                             int transferIn, int transferOut, int assignments, int expenditures, int closingBalance) {
        this.assetName = assetName;
        this.assetType = assetType;
        this.openingBalance = openingBalance;
        this.purchases = purchases;
        this.transferIn = transferIn;
        this.transferOut = transferOut;
        this.assignments = assignments;
        this.expenditures = expenditures;
        this.closingBalance = closingBalance;
    }

    // Getters and Setters
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getAssetType() { return assetType; }
    public void setAssetType(String assetType) { this.assetType = assetType; }

    public int getOpeningBalance() { return openingBalance; }
    public void setOpeningBalance(int openingBalance) { this.openingBalance = openingBalance; }

    public int getPurchases() { return purchases; }
    public void setPurchases(int purchases) { this.purchases = purchases; }

    public int getTransferIn() { return transferIn; }
    public void setTransferIn(int transferIn) { this.transferIn = transferIn; }

    public int getTransferOut() { return transferOut; }
    public void setTransferOut(int transferOut) { this.transferOut = transferOut; }

    public int getAssignments() { return assignments; }
    public void setAssignments(int assignments) { this.assignments = assignments; }

    public int getExpenditures() { return expenditures; }
    public void setExpenditures(int expenditures) { this.expenditures = expenditures; }

    public int getClosingBalance() { return closingBalance; }
    public void setClosingBalance(int closingBalance) { this.closingBalance = closingBalance; }
}
