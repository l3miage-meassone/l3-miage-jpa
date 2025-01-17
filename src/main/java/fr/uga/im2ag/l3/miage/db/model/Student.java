package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.*;

import org.hibernate.annotations.ManyToAny;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
@NamedQuery(name="StudentHavingGradeAverageAbove",
            query="select s from Student s join s.grades g group by (s) having ((AVG(g.value*g.weight)/SUM(g.weight)) > :minAverage)")

@Entity
public class Student extends Person {

    @ManyToOne
    private GraduationClass belongTo;
    @OneToMany
    private List<Grade> grades;

    public GraduationClass getBelongTo() {
        return belongTo;
    }

    public Student setBelongTo(GraduationClass belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public Student setGrades(List<Grade> grades) {
        this.grades = grades;
        return this;
    }

    public Float moyenneNotes()
    {
        float div = 0;
        float moyenne = 0;
        for (Grade grade : grades) {
            div += grade.getWeight();
            moyenne += grade.getValue() * grade.getWeight();
        }
        return moyenne/div;
    }
}
