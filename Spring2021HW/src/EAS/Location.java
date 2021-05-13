public enum Location {
    LycheePark("LP"),
    TeachingBuilding("TB");

    public final String simpleName;
    Location(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getSimpleName(){
        return simpleName;
    }
}
