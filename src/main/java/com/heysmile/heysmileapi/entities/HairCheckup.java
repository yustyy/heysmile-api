package com.heysmile.heysmileapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hair_checkups")
public class HairCheckup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_notes", columnDefinition = "TEXT")
    private String userNotes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_front_id", referencedColumnName = "id")
    private Image imageFront;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_back_id", referencedColumnName = "id")
    private Image imageBack;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_left_id", referencedColumnName = "id")
    private Image imageLeft;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_right_id", referencedColumnName = "id")
    private Image imageRight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_top_id", referencedColumnName = "id")
    private Image imageTop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @Column(name = "doctor_comment", columnDefinition = "TEXT")
    private String doctorComment;

    @Column(name = "graft")
    private int graft;



}
