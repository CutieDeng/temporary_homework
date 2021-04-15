package CPC3;

abstract public class LeaveNode implements TreePoint{

    private String name;
    private Formula father;

    @Override
    public Formula leftSon() {
        return null;
    }

    @Override
    public Formula rightSon() {
        return null;
    }

    @Override
    public Formula father() {
        return null;
    }
}
