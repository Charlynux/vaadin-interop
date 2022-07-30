package com.example.application;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClojureRepl implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("clojure.core.server"));
        IFn start = Clojure.var("clojure.core.server", "start-server");
        start.invoke(Clojure.read("{:port 5555 :accept clojure.core.server/repl :name repl :server-daemon false}"));
        System.out.println("Clojure REPL server started on port 5555");
    }
}