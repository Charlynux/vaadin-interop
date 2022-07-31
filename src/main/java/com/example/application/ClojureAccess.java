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

    public String fizzbuzz(Integer n)
    {
        return (String) getFn("todoapp.services", "fizzbuzz").invoke(n);
    }

    public Object callClojure(String ns, String fn) throws Exception {
        return ((Callable) getFn(ns, fn)).call();
    }

    private IFn getFn(String ns, String fn) {
        // load Clojure lib. See https://clojure.github.io/clojure/javadoc/clojure/java/api/Clojure.html
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read(ns));

        return Clojure.var(ns, fn);
    }
}
