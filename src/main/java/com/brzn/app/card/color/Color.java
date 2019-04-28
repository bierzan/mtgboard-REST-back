package com.brzn.app.card.color;


import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String colorName;

    public Color(){}
}
