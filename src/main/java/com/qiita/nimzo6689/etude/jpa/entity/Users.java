package com.qiita.nimzo6689.etude.jpa.entity;

import com.qiita.nimzo6689.etude.jpa.code.AuthorityType;
import com.qiita.nimzo6689.etude.jpa.converter.AuthorityTypeToStringConverter;
import com.qiita.nimzo6689.etude.jpa.converter.BooleanToStringConverter;
import com.qiita.nimzo6689.etude.jpa.converter.LocalDateTimeToTimestampConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Users entity.
 *
 * @author nimzo6689
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = -4218487541153130194L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LOGIN_ID")
    private String loginId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_NAME")
    private String userName;
    @Size(max = 8)
    @Column(name = "BIRTHDAY")
    private String birthday;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 200)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 16)
    @Column(name = "CREDIT_CARD_NUMBER")
    private String creditCardNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUTHORITY_TYPE")
    @Convert(converter = AuthorityTypeToStringConverter.class)
    private AuthorityType authorityType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_DELETED")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    private LocalDateTime createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATED_AT")
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    private LocalDateTime updatedAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UPDATED_BY")
    private String updatedBy;

}
