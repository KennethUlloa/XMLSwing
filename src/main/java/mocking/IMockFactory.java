package mocking;

public interface IMockFactory<T>{
    T buildComponent(MockNode node);
    T buildComponent(MockNode node, MockFactory mockMaker);
}
