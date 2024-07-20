package com.outsider.masterofprediction.dto;

import lombok.*;

public class ProfitLossDTO {
    private double totalProfitPoints;
    private double totalLossPoints;
    private double netProfitPoints;

    public ProfitLossDTO(double totalProfitPoints, double totalLossPoints, double netProfitPoints) {
        this.totalProfitPoints = totalProfitPoints;
        this.totalLossPoints = totalLossPoints;
        this.netProfitPoints = netProfitPoints;
    }

    public double getTotalProfitPoints() {
        return totalProfitPoints;
    }

    public void setTotalProfitPoints(double totalProfitPoints) {
        this.totalProfitPoints = totalProfitPoints;
    }

    public double getTotalLossPoints() {
        return totalLossPoints;
    }

    public void setTotalLossPoints(double totalLossPoints) {
        this.totalLossPoints = totalLossPoints;
    }

    public double getNetProfitPoints() {
        return netProfitPoints;
    }

    public void setNetProfitPoints(double netProfitPoints) {
        this.netProfitPoints = netProfitPoints;
    }

    @Override
    public String toString() {
        return "ProfitLossDTO{" +
                "totalProfitPoints=" + totalProfitPoints +
                ", totalLossPoints=" + totalLossPoints +
                ", netProfitPoints=" + netProfitPoints +
                '}';
    }
}
