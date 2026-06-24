package com.logan.winterheim.temperature;

public class TemperatureData {
    private float temperature;
    private int hurtTimer;

    public TemperatureData() {
        this.temperature = 0f;
        this.hurtTimer = 0;
    }

    public float getTemperature(){
        return temperature;
    }
    public void setTemperature(float temp) {
        this.temperature = temp;
    }
    public int getHurtTimer() { return hurtTimer; }
    public void setHurtTimer(int timer) { this.hurtTimer = timer; }
}
