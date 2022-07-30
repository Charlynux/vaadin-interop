package com.example.application;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import com.vaadin.flow.component.Component;

import java.util.concurrent.Callable;

@org.springframework.stereotype.Component
public class ClojureAccess {
    public Component createClojureTitle()
    {
        try {
            return (Component) callClojure("todoapp.components", "create-title");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object callClojure(String ns, String fn) throws Exception {
        // load Clojure lib. See https://clojure.github.io/clojure/javadoc/clojure/java/api/Clojure.html
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read(ns));

        return ((Callable) Clojure.var(ns, fn)).call();
    }
}
