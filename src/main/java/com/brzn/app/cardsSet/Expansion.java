package com.brzn.app.cardsSet;

enum Expansion {
    CORE("core"),
    EXPANSION("expansion"),
    REPRINT("reprint"),
    BOX("box"),
    UN("un"),
    FROM_THE_VAULT("from the vault"),
    PREMIUM_DECK("premium deck"),
    DUEL_DECK("duel deck"),
    STARTER("starter"),
    COMMANDER("commander"),
    PLANECHASE("planechase"),
    ARCHENEMY("archenemy"),
    PROMO("promo"),
    VANGUARD("vanguard"),
    MASTERS("masters");

    private String typeName;

    Expansion(String typeName) {
        this.typeName = typeName;
    }

    private String getExpansionName(){
        return this.typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
