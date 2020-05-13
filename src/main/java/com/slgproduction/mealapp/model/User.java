package com.slgproduction.mealapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "mealdb")
@Data
public class User {

}
