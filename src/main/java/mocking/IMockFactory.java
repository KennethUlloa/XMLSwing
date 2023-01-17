package mocking;

import org.w3c.dom.Node;

import java.util.HashMap;

public interface IMockFactory<T>{
    T buildComponent(MockNode node);
}
