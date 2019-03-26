package com.brzn.cardsSet;

import java.time.LocalDate;

class CardsSet {
    private final String code;
    private final String name;
    private final String type;
    private LocalDate releaseDate;
    private final Enum expansion;
    private final String block;

    public CardsSet(String code, String name, String type, Enum expansion, String block) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.expansion = expansion;
        this.block = block;
    }

    @Override
    public String toString() {
        return "CardsSet{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", releaseDate=" + releaseDate +
                ", expansion=" + expansion +
                ", block='" + block + '\'' +
                '}';
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Enum getExpansion() {
        return expansion;
    }

    public String getBlock() {
        return block;
    }
}
