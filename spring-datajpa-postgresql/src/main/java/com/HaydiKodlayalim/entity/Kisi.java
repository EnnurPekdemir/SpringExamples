package com.HaydiKodlayalim.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="kisi")
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@EqualsAndHashCode(of={"id"})
@ToString   
public class Kisi implements Serializable {
    
    @Id
    @SequenceGenerator(name="seq_kisi",allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi",strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "ad",length = 100)
    private String ad;
    
    @Column(name = "soyad",length = 100)
    private String soyad;

    @OneToMany 
    @JoinColumn(name="kisi_adres_id")
    private List<Adres> adresler;
}
