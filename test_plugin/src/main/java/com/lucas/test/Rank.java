package com.lucas.test;

public enum Rank {

    OWNER("&c&lOwner", 'a'),
    HELPER("&a&lHelper", 'b'),
    MEMBER("&e&lMember", 'c');

    private String display;
    private char orderSymbol;
    Rank(String display, char orderSymbol)
    {
        this.display = display;
        this.orderSymbol = orderSymbol;
    }

    public String getDisplay() {return display;}
    public char getOrderSymbol() {return  orderSymbol;}
}
