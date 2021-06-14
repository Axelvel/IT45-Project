package models;
import tabu.*;
import java.util.List;


/**
 * This class demonstrates a schedule by a list of formation
 * It allows the use of multiple methods useful for the
 * tabu search
 */
public class Schedule {
    public List<Formation> schedule;
    private final Interface i;
    private final Solution currentSolution;

    public Schedule(Interface i, Solution currentSol){
        this.i = i;
        this.schedule = currentSol.generateSchedule(i);
        this.currentSolution = currentSol;
    }

    public List<Formation> getSchedule(){return schedule;}

    /**
     * Check if a formation can be added in a schedule
     * @param f : formation
     * @return true if it fits, false if not
     */
    public boolean fitInSchedule(Formation f){
        if(f.getSkill() != i.getSkill() && i.getSkill() != 2){ return false;}

        Solution solutionCopy = new Solution(currentSolution);
        solutionCopy.setAssignation(f.getId(),i.getId());

        List<Formation> scheduleCopy = List.copyOf(schedule);
        Schedule testSchedule = new Schedule(i,solutionCopy);
        schedule = testSchedule.getSchedule();
        if(isScheduleValid()){
            schedule = scheduleCopy;
            return true;
        }
        schedule = scheduleCopy;
        return false;
    }

    /**
     * Check if a formation is already in the schedule
     * @param f : formation
     * @return : true if yes, false if not
     */
    public boolean contains(Formation f){ return schedule.contains(f); }

    /**
     * Check if a formation is in the last time slot of the day
     * @param f : formation
     * @return true if yes, false if not
     */
    public boolean isLastFormationOfTheDay(Formation f){
        int day = f.getDay().getId();
        for(Formation formation: schedule){
            if(formation.getDay().getId() == day){
                if(f.getEndHour() <= formation.getStartHour())return false;
            }
        }
        return true;
    }


    /**
     * Checks if the generated schedule for the interface is valid
     */
    public boolean isScheduleValid() {

        if(schedule.size() == 0){
            return true;
        }

        int hours = 0;
        int startday = schedule.get(0).getStartHour();
        int endDay;


        for (int j = 0; j < schedule.size(); j++) {

            if (schedule.get(j).getSkill() != i.getSkill() && i.getSkill() != 2) {
                return false;
            }

            hours += (schedule.get(j).getEndHour() - schedule.get(j).getStartHour());

            if (j != 0) {

                if (schedule.get(j).getDay() == schedule.get(j-1).getDay()) {
                    if (schedule.get(j-1).getEndHour() > schedule.get(j).getStartHour()) {
                        return false;
                    } else {
                        endDay = schedule.get(j).getEndHour();

                        if (endDay - startday >= 12) {
                            return false;
                        }

                        if (hours >= 35) {
                            return false;
                        }

                        if (schedule.get(j).getStartHour() > 12 && (schedule.get(j).getStartHour() - schedule.get(j-1).getEndHour()) < 1) {

                            return false;
                        }

                    }
                } else {
                    startday = schedule.get(j).getStartHour();
                }
            }

        }
        return true;
    }

    public String toString(){
        String str = "Interface "+i.getId()+":";
        for (Formation formation : schedule) {
            str += "\n" + formation.getDay() + " : F" + formation.getId() + "[" +
                    formation.getStartHour() + "h-" + formation.getEndHour() + "h]";
        }
        return str;
    }
}
