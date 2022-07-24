package com.example.application.views.main

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.html.H1

class RedTitle : H1 {
    constructor(): super() {}
    constructor(text : String): super(text) {
        style.set("color", "red");
    }
    constructor(vararg components : Component): super(*components) {}
}