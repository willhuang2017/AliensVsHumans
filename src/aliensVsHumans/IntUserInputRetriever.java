package aliensVsHumans;

public interface IntUserInputRetriever<T>{
abstract T produceOutputOnIntUserInput(int selection) throws IllegalArgumentException;
}
