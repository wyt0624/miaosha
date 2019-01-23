package com.surfilter.timer;

import java.util.concurrent.ArrayBlockingQueue;

public class TaskArrayQueue<T> {
    private final static int MAX_LIST = 3000;
    private ArrayBlockingQueue<T> list_m = new ArrayBlockingQueue<T>(
            MAX_LIST);

    public synchronized void push(Object ip){
        if (list_m != null){
            list_m.offer(((T) ip));
        }
    }

    public synchronized T pop(){
        T task = null;
        if (list_m != null){
            if (list_m.size() > 0){
                task = list_m.poll();
            }
        }
        return task;
    }

    public int getSize() {
        return list_m.size();
    }
}
