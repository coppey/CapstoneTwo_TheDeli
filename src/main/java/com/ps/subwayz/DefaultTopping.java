package com.ps.subwayz;

public class DefaultTopping extends Topping{

    public DefaultTopping(String name) {
        super(name, 0);
    }

    @Override
    public String getType() {
        return "Default";
    }
}
