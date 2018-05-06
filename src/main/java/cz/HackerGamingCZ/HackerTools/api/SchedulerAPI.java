package cz.HackerGamingCZ.HackerTools.api;

import org.bukkit.Bukkit;

import java.util.HashMap;

public class SchedulerAPI {

    //Name >> Scheduler
    private HashMap<String, Integer> schedulers = new HashMap<>();

    public void addScheduler(String name, int scheduler){
        schedulers.put(name, scheduler);
    }

    public int getScheduler(String name){
        return schedulers.getOrDefault(name, null);
    }

    public void stopSchedulder(String name){
        int i = schedulers.getOrDefault(name, -1);
        if(i == -1){
            return;
        }
        Bukkit.getScheduler().cancelTask(i);
        schedulers.remove(name);
    }

    public void addScheduler(SchedulerType type, int scheduler){
        schedulers.put(type.getName(), scheduler);
    }

    public int getScheduler(SchedulerType type){
        return schedulers.getOrDefault(type.getName(), -1);
}

    public void stopSchedulder(SchedulerType type){
        int i = schedulers.getOrDefault(type.getName(), -1);
        if(i == -1){
            return;
        }
        Bukkit.getScheduler().cancelTask(i);
        schedulers.remove(type.getName());
    }


    public HashMap<String, Integer> getSchedulers() {
        return schedulers;
    }

    public enum SchedulerType{

        LOBBY("lobbycountdown"),
        INGAME("ingamecountdown"),
        RESET("resetcountdown");

        private String name;

        SchedulerType(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}