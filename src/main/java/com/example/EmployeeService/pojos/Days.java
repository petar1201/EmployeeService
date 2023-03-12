package com.example.EmployeeService.pojos;

public class Days {
    private int totalDays;
    private int usedDays;
    private int availableDays;

    public Days(int totalDays, int usedDays, int availableDays) {
        this.totalDays = totalDays;
        this.usedDays = usedDays;
        this.availableDays = availableDays;
    }

    public Days() {
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getUsedDays() {
        return usedDays;
    }

    public void setUsedDays(int usedDays) {
        this.usedDays = usedDays;
    }

    public int getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(int availableDays) {
        this.availableDays = availableDays;
    }
}
