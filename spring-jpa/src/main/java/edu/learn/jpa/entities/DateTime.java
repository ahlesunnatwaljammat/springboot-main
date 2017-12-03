package edu.learn.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table( name = "Date_Time_Demo")
public class DateTime implements Serializable {
    private @Id @GeneratedValue Long id;
    private @Column LocalDate date;
    private @Column LocalDateTime dateTime;
    private @Column Duration duration;

    public DateTime(){
    }

    public DateTime(LocalDate date, LocalDateTime dateTime, Duration duration) {
        this.date = date;
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DateTime{" +
                "id=" + id +
                ", date=" + date +
                ", dateTime=" + dateTime +
                ", duration=" + duration +
                '}';
    }
}
