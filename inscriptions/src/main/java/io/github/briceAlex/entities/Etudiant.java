
/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/

package io.github.briceAlex.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Etudiant")
public class Etudiant 
{

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Integer ID;
    
    @Column(name = "nom")
    public String nom;
    
    @Column(name = "prenom")
    public String prenom;

    @Column(name = "date_naissance")
    public LocalDate date_naissance;

    @Column(name = "telephone")
    private String telephone;


    public Etudiant(String nom, String prenom, LocalDate date_naissance, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.telephone = telephone;
    }
    public Etudiant(){

    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s ID",ID,nom,prenom);
    }

    
    public int getID (){return ID;}
    public void setID (int ID){
        this.ID = ID;
    }

    public String getTelephone (){return telephone;}
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    private static final String persistenceUnitName="mabase-unit" ;
    private static EntityManagerFactory factory;

    public static void main( final String[] args)
    {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em =factory.createEntityManager();

        em.getTransaction().begin();
        Etudiant etu = new Etudiant();
        etu.nom ="toto";
        etu.prenom = "jena";
        etu.date_naissance = LocalDate.of(2002,9,30);
        etu.telephone = "0596000000";
        em.persist(etu);
        em.getTransaction().commit();
        
        List<Etudiant> list_etu = em.createQuery("select e from Etudiant e",Etudiant.class).getResultList();
    
        for (Etudiant e : list_etu){
            System.out.println(e);
        }

        em.close();
    }
}
