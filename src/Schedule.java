import java.util.List;

public class Schedule {
    public List<Formation> schedule;
    private Interface i;
    private Solution currentSolution;
    public Schedule(Interface i, Solution currentSol){
        this.i = i;
        this.schedule = currentSol.generateSchedule(i);
        this.currentSolution = currentSol;
    }

    public List<Formation> getSchedule(){return schedule;}
    public Interface getScheduleInterface(){return i;}

    public boolean fitInSchedule(Formation f){
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

    public boolean contains(Formation f){ return schedule.contains(f); }

    public boolean isLastFormationOfTheDay(Formation f){
        int day = f.getDay().getId();
        for(Formation formation: schedule){
            if(formation.getDay().getId() == day){
                if(f.getEndHour() <= formation.getStartHour()){
                    return false;
                }
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
        for(int j = 0; j<schedule.size();j++){
            str += "\n" + schedule.get(j).getDay() + " : F"+ schedule.get(j).getId()+ "["+schedule.get(j).getStartHour()+"h-"+schedule.get(j).getEndHour()+"h]";
        }
        return str;
    }
}
