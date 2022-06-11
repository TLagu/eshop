package com.lagu.shop.module.user.entity;

import com.lagu.shop.module.product.entity.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@SQLDelete(sql = "UPDATE \"user\" SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "status", columnDefinition = "varchar(25) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "uuid")
    private String uuid;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "post")
    private String post;

    @Column(name = "street")
    private String street;

    @Column(name = "contact")
    @Enumerated(EnumType.STRING)
    private ContactType contact;

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public Status getStatus() {
        return status;
    }

    public UserEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public UserEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public UserEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public UserEntity setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public UserEntity setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public UserEntity setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getPost() {
        return post;
    }

    public UserEntity setPost(String post) {
        this.post = post;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public ContactType getContact() {
        return contact;
    }

    public UserEntity setContact(ContactType contact) {
        this.contact = contact;
        return this;
    }

}