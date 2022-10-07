package com.ivoair.quarkus.prototype.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Flight extends PanacheEntityBase {

	@Id
    private String id;

	private String origin;

	private String destination;

}
