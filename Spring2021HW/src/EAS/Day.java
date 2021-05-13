public enum Day {
    Monday(0),
    Tuesday(1),
    Wednesday(2),
    Thursday(3),
    Friday(4),
    Saturday(5),
    Sunday(6);

    /**
     * The order number of a day in a week.
     */
    public final int value;
    
    Day(int value){
        this.value = value;
    }

    /**
     * This method would get the instance using the index of a week.
     * <p>
     *
     * For example, if you put 1 as the args, you would get Monday, 2 for Tuesday and etc.
     * @param index the ordered number of the day in a week.
     * @return The Day instance.
     */
    public static Day getInstance(int index){
        return Day.values()[index];
    }
}
