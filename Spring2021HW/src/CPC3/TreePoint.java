package CPC3;

@SuppressWarnings("unused")
public interface TreePoint {
    Formula leftSon();
    Formula rightSon();
    Formula father();
    boolean isExternal();
}
