package com.outsider.masterofprediction.dto;


import java.sql.Timestamp;

public class ActiveDTO {
    private String name;
    private long amount;
    private String choice;
    private java.sql.Timestamp activeTimestamp;

    public ActiveDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Timestamp getActiveTimestamp() {
        return activeTimestamp;
    }

    public void setActiveTimestamp(Timestamp activeTimestamp) {
        this.activeTimestamp = activeTimestamp;
    }

    @Override
    public String toString() {
        return "ActiveDTO{" +
                "Name='" + name + '\'' +
                ", amount=" + amount +
                ", choice='" + choice + '\'' +
                ", inquiryTimestamp=" + activeTimestamp +
                '}';
    }
}
