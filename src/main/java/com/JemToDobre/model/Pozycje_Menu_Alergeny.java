package com.JemToDobre.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name="pozycje_menu_alergeny")
public class Pozycje_Menu_Alergeny {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pozycja_menu", referencedColumnName = "ID_Pozycja_Menu")
    private Pozycje_Menu pozycjaMenu;

    @ManyToOne
    @JoinColumn(name = "id_alergen", referencedColumnName = "ID_Alergen")
    private Alergeny alergen;
}
