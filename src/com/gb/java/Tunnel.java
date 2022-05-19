package com.gb.java;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore countTogetherThrowStageSemaphore;
    public Tunnel(int countTogetherThrowStageSemaphore) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.countTogetherThrowStageSemaphore = new Semaphore(countTogetherThrowStageSemaphore);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                countTogetherThrowStageSemaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                countTogetherThrowStageSemaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
