package com.train.schedule.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;

@Getter
@Setter
@Entity
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "train_id")
    private  Long trainId;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "stations")
    private LinkedHashSet<String> stations;

}
